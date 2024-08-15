package org.scripts.august.scripts.tasks;

import org.data.FishingData;
import org.data.LocationsData;
import org.data.handler.RandomEventsHandler;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleObject;
import simple.hooks.wrappers.SimpleNpc;
import simple.robot.utils.WorldArea;

public class Bank extends Task {

    private SimpleObject bank;
    private WorldArea reachableBankArea;

    @Override
    public void run() {
        if (RandomEventsHandler.needsAction() || aioSkill.skill == null) {
            return;
        }
        if (aioSkill.skill.equals("Magic")) {
            aioSkill.getScriptController().setTask("Magic");
            return;
        }

        handleSkillSpecificTasks();

        if (c.inventory.populate().isEmpty()) {
            setSkillTask();
        }

        if (aioSkill.skill != null) {
            setReachableBankArea();
        }

        if (!c.bank.bankOpen()) {
            locateAndOpenBank();
        }

        if (shouldTransport()) {
            aioSkill.getScriptController().setTask("Transport");
        }
        if (aioSkill.skill.equals("Slayer")) {
            aioSkill.getScriptController().setTask("SlayerBank");
            return;
        }
    }

    private void handleSkillSpecificTasks() {
        switch (aioSkill.skill) {
            case "Runecrafting":
                aioSkill.getScriptController().setTask("RunecraftBank");
                break;
            case "Fishing":
                if (c.inventory.getFreeSlots() >= 10) {
                    aioSkill.getScriptController().setTask("Fish");
                }
                break;
            case "Crafting":
                if (c.inventory.inventoryFull()
                        && c.inventory.populate().filterContains(aioSkill.craftingData.getTaskName()).next() != null) {
                    aioSkill.getScriptController().setTask("Craft");
                }
                break;
        }
    }

    private void setSkillTask() {
        switch (aioSkill.skill) {
            case "Woodcutting":
                aioSkill.getScriptController().setTask("Woodcut");
                break;
            case "Thieving":
                aioSkill.getScriptController().setTask("Thieve");
                break;
            case "Mining":
                if (aioSkill.miningData.getTaskName().contains("rune essence")) {
                    aioSkill.getScriptController().setTask("EssenceMine");
                } else {
                    aioSkill.getScriptController().setTask("Mine");
                }
                break;
            case "Slayer":
                aioSkill.getScriptController().setTask("SlayerTree");
                break;
            case "Runecrafting":
            case "Crafting":
                aioSkill.getScriptController().setTask(aioSkill.skill);
                break;
        }
    }

    private void setReachableBankArea() {
        switch (aioSkill.skill) {
            case "Woodcutting":
            case "Fishing":
            case "Cooking":
            case "Fletching":
                reachableBankArea = LocationsData.WOODCUTTING.getWorldArea();
                break;
            case "Thieving":
                reachableBankArea = LocationsData.THIEVE.getWorldArea();
                break;
            case "Mining":
                switch (aioSkill.miningData.getTaskName()) {
                    case "Oxi Ore":
                    case "Rune Essence":
                        reachableBankArea = LocationsData.HOME.getWorldArea();
                        break;
                    default:
                        reachableBankArea = LocationsData.HOME_MINING.getWorldArea();
                        break;
                }
                break;
            case "Runecrafting":
            case "Crafting":
                reachableBankArea = LocationsData.HOME.getWorldArea();
                break;
            default:
                reachableBankArea = null;
                break;
        }
    }

    private void locateAndOpenBank() {
        aioSkill.status = "Locating Bank";
        if (p.within(LocationsData.HOME.getWorldArea())) {
            bank = c.objects.populate().filterHasAction("Bank").nextNearest();
        }
        if (reachableBankArea != null) {
            bank = c.objects.populate().filter(reachableBankArea).filterHasAction("Bank").nextNearest();
        }
        if (bank != null) {
            openBank(bank);
        }
    }

    private void openBank(SimpleObject bank) {
        bank.menuAction("Bank");
        if (c.onCondition(() -> c.bank.bankOpen(), 600, 10)) {
            handleBankOperations();
        } else {
            SimpleNpc banker = c.npcs.populate().filterHasAction("Bank").nextNearest();
            if (banker != null) {
                banker.menuAction("Bank");
                c.onCondition(() -> c.bank.bankOpen(), 600, 10);
                if (c.bank.bankOpen()) {
                    handleBankOperations();
                }
            }
        }
    }

    private void handleBankOperations() {
        aioSkill.status = "Bank Opened";

        switch (aioSkill.skill) {
            case "Fishing":
                depositFishingItems();
                break;
            case "Crafting":
                bankCraftItems();
                break;
            default:
                if (!c.inventory.populate().isEmpty()) {
                    c.bank.depositInventory();
                    c.menuActions.sendAction(57, -1, 786474, 1, "Deposit Inventory", "");
                    c.sleepCondition(() -> !c.inventory.inventoryFull());
                }
                break;
        }

        aioSkill.status = "Closing the Bank";
        c.bank.closeBank();
    }

    private void depositFishingItems() {
        for (String item : FishingData.ITEMS_T0_BANK) {
            SimpleItem bankItem = c.inventory.populate().filterContains(item).next();
            if (bankItem != null && !bankItem.getName().equals("Lobster pot")) {
                c.menuActions.interact(bankItem, 6);
                c.sleep(600, 1200);
            }
        }
    }

    private void bankCraftItems() {
        SimpleItem chisel = c.inventory.populate().filterContains("Chisel").next();
        if (chisel == null) {
            c.bank.withdraw("Chisel", 1);
            c.onCondition(() -> !c.inventory.populate().filter("Chisel").isEmpty(), 600, 10);
        }
        String[] options = { "Ruby", "Emerald", "Sapphire", "Diamond", "Dragonstone", "Onyx", "Zenyte", "Best" };

        for (String item : options) {
            SimpleItem bankItem = c.inventory.populate().filterContains(item).next();
            if (bankItem != null) {
                c.menuActions.interact(bankItem, 6);
                c.sleep(600, 1200);
            }
        }
        c.bank.withdraw(aioSkill.craftingData.getTaskName(), 27);
    }

    private boolean shouldTransport() {
        return aioSkill.skill.equals("Slayer") && !p.within(LocationsData.HOME.getWorldArea()) &&
                aioSkill.slayerTask.getKillAmount() != 0;
    }

    @Override
    public String DebugTaskDescription() {
        return "Bank";
    }
}
