package org.scripts.august.scripts.tasks;

import org.data.LocationsData;
import org.data.handler.RandomEventsHandler;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

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
        if (aioSkill.skill.equals("Runecrafting")) {
            aioSkill.getScriptController().setTask("RunecraftBank");
            return;
        }
        if (aioSkill.skill.equals("Magic")) {
            aioSkill.getScriptController().setTask("Magic");
            return;
        }
        if (aioSkill.skill.equals("Fishing")) {
            aioSkill.getScriptController().setTask("FishBank");
            return;
        }
        if (aioSkill.skill.equals("Slayer")) {
            aioSkill.getScriptController().setTask("SlayerBank");
            return;
        }
        if (aioSkill.skill.equals("Smithing")) {
            aioSkill.getScriptController().setTask("SmithnSmelt");
            return;
        }
        if (aioSkill.skill.equals("Crafting")) {
            aioSkill.getScriptController().setTask("CraftBank");
            return;
        }

        if (aioSkill.firstOption.equalsIgnoreCase("Rune Essence")) {
            aioSkill.getScriptController().setTask("EssenceMine");
            return;
        }

        if (aioSkill.skill.equals("Mining")) {
            aioSkill.getScriptController().setTask("MineBank");
            return;
        }
        if (aioSkill.skill.equals("Fishing")) {
            aioSkill.getScriptController().setTask("FishBank");
            return;
        }

        if (c.inventory.populate().isEmpty()) {
            setSkillTask();
            return;
        }
        if (aioSkill.skill != null) {
            setReachableBankArea();
        }
        if (!c.bank.bankOpen()) {
            locateAndOpenBank();
            c.bank.closeBank();
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
                break;
        }
    }

    private void setReachableBankArea() {
        switch (aioSkill.skill) {
            case "Woodcutting":
                if (!aioSkill.secondOption.equalsIgnoreCase("Donator Zone")) {
                    reachableBankArea = LocationsData.WOODCUTTING.getWorldArea();
                } else {
                    reachableBankArea = LocationsData.WOODCUTTING_DONATOR_ZONE.getWorldArea();
                }
                break;
            case "Fishing":
            case "Cooking":
            case "Fletching":
                reachableBankArea = LocationsData.WOODCUTTING.getWorldArea();
                break;
            case "Thieving":
                reachableBankArea = LocationsData.THIEVE.getWorldArea();
                break;
            case "Mining":
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
            SimpleNpc banker = c.npcs.populate().filterHasAction("Bank").filter(reachableBankArea).nextNearest();
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
            default:
                if (!c.inventory.populate().isEmpty()) {
                    depositInventory();
                }
                break;
        }
        aioSkill.status = "Closing the Bank";
    }

    private void depositInventory() {
        if (!c.inventory.populate().isEmpty()) {
            c.menuActions.sendAction(57, -1, 786474, 1, "Deposit Inventory", "");
            c.sleepCondition(() -> c.inventory.getFreeSlots() == 28);
        }
    }

    @Override
    public String DebugTaskDescription() {
        return "Bank";
    }
}
