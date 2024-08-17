package org.scripts.august.scripts.tasks;

import org.data.LocationsData;
import org.data.handler.RandomEventsHandler;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

import simple.hooks.wrappers.SimpleObject;
import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleNpc;
import simple.robot.utils.WorldArea;

public class MineBank extends Task {

    private SimpleObject bank;
    private WorldArea reachableBankArea;

    @Override
    public void run() {
        if (RandomEventsHandler.needsAction() || aioSkill.skill == null || !aioSkill.skill.equals("Mining")) {
            return;
        }
        if ((aioSkill.miningData.getTaskName()
                .equalsIgnoreCase("Luminite Ore") || aioSkill.miningData.getTaskName().equalsIgnoreCase("Coal"))
                && c.inventory.populate().filterContains("Hammer").isEmpty()) {
            withdrawHammer();
            aioSkill.getScriptController().setTask("Transport");
            return;
        }

        if (c.inventory.getFreeSlots() > 20) {
            aioSkill.getScriptController().setTask("Mine");
            return;
        }

        setReachableBankArea();
        if (!c.bank.bankOpen()) {
            locateAndOpenBank();
            c.bank.closeBank();
        }
    }

    private void withdrawHammer() {
        aioSkill.status = "Withdrawing Hammer";
        if (!c.bank.bankOpen()) {
            locateAndOpenBank();
        }
        if (c.bank.bankOpen()) {
            withdrawItem("Hammer", 1);
            c.sleepCondition(() -> c.inventory.populate().filter("Hammer").population() > 0);
        }
    }

    private void setReachableBankArea() {
        if (aioSkill.secondOption.equalsIgnoreCase("Donator Zone")) {
            reachableBankArea = LocationsData.MINING_DONATOR_ZONE.getWorldArea();
            return;
        }
        switch (aioSkill.miningData.getTaskName()) {
            case "Luminite Ore":
            case "Oxi Ore":
                reachableBankArea = LocationsData.HOME.getWorldArea();
                break;
            default:
                reachableBankArea = LocationsData.HOME_MINING.getWorldArea();
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
        if (!c.inventory.populate().isEmpty()) {
            depositInventory();
        }
        aioSkill.status = "Closing the Bank";
    }

    private void depositInventory() {
        if (!c.inventory.populate().isEmpty()) {
            c.menuActions.sendAction(57, -1, 786474, 1, "Deposit Inventory", "");
            c.sleepCondition(() -> c.inventory.getFreeSlots() == 28);
        }
    }

    private void withdrawItem(String itemName, int quantity) {
        SimpleItem item = c.bank.populate().filterContains(itemName).next();
        if (item != null) {
            String action = "Withdraw-1";
            item.menuAction(action);
            waitForItemInInventory(itemName);
        }
    }

    private void waitForItemInInventory(String itemName) {
        c.onCondition(() -> !c.inventory.populate().filterContains(itemName).isEmpty(), 300, 5);
    }

    @Override
    public String DebugTaskDescription() {
        return "MineBank";
    }
}
