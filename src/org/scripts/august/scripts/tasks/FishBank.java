package org.scripts.august.scripts.tasks;

import org.scripter.Task;
import org.data.LocationsData;
import org.data.handler.RandomEventsHandler;
import org.scripts.august.scripts.aioSkill;

import simple.hooks.wrappers.SimpleObject;
import simple.hooks.wrappers.SimpleItem;
import simple.robot.utils.WorldArea;

public class FishBank extends Task {

    private SimpleObject bank;
    private SimpleItem item;
    WorldArea reachableBankArea = LocationsData.HOME.getWorldArea();

    @Override
    public void run() {
        if (RandomEventsHandler.needsAction()) {
            return;
        }
        if (shouldFish()) {
            aioSkill.getScriptController().setTask("Fish");
            return;
        }
        if (shouldTransportToTask()) {
            aioSkill.getScriptController().setTask("Transport");
            return;
        }

        if (!c.bank.bankOpen()) {
            locateAndOpenBank();
            c.sleep(1200, 1800);
            if (c.bank.bankOpen()) {
                handleBankOperations();
                c.bank.closeBank();
                return;
            }
            return;
        }

    }

    private boolean shouldTransportToTask() {
        return !p.within(LocationsData.WOODCUTTING.getWorldArea());
    }

    private void locateAndOpenBank() {
        aioSkill.status = "Locating Bank";
        if (p.within(LocationsData.WOODCUTTING.getWorldArea())) {
            bank = c.objects.populate().filterHasAction("Bank").nextNearest();
        }

        if (bank != null && tryOpenBank(bank)) {
            aioSkill.status = "Bank Opened";
        }
    }

    private boolean tryOpenBank(SimpleObject bank2) {
        bank2.menuAction("Bank");
        return c.onCondition(() -> c.bank.bankOpen(), 500, 10);
    }

    private void depositInventory() {
        if (!c.inventory.populate().isEmpty()) {
            c.menuActions.sendAction(57, -1, 786474, 1, "Deposit Inventory", "");
            c.sleepCondition(() -> c.inventory.getFreeSlots() == 28);
        }
    }

    private void handleBankOperations() {
        aioSkill.status = "Banking for Supplies";

        if (c.inventory.getFreeSlots() != 28) {
            depositInventory();
        }

        if (!isItemAvailableInBank(aioSkill.fishingData.getRequiredTool())) {
            c.stopScript();
            return;
        }

        withdrawEssentialItems();
    }

    private void withdrawEssentialItems() {
        withdrawItem(aioSkill.fishingData.getRequiredTool(), 1);
        if (aioSkill.fishingData.getRequiredTool().equals("Fly fishing rod")) {
            withdrawItem("Feather", 10);
        }
    }

    private boolean isItemAvailableInBank(String itemName) {
        return !c.bank.populate().filterContains(itemName).isEmpty();
    }

    private void withdrawItem(String itemName, int quantity) {
        if (isItemAvailableInBank(itemName)) {

            SimpleItem item = c.bank.populate().filterContains(itemName).next();
            if (item != null) {
                if (quantity == 1) {
                    item.menuAction("Withdraw-1");
                } else if (quantity == 10) {
                    item.menuAction("Withdraw-10");
                }
                waitForItemInInventory(item.getName());
                if (!c.inventory.populate().filterContains(itemName).isEmpty()) {
                    return;
                }
            }
        }

        c.sleep(200);
        withdrawItem(itemName, quantity);
    }

    private void waitForItemInInventory(String itemName) {
        c.onCondition(() -> !c.inventory.populate().filterContains(itemName).isEmpty(), 300, 5);
    }

    private boolean shouldFish() {
        if (aioSkill.fishingData.getRequiredTool().equals("Fly fishing rod")) {
            boolean hasFishingRod = !c.inventory.populate().filterContains("Fly fishing rod").isEmpty();
            boolean hasFeathers = !c.inventory.populate().filterContains("Feather").isEmpty();
            return hasFishingRod && hasFeathers && c.inventory.getFreeSlots() > 20;
        } else {
            boolean hasRequiredItem = !c.inventory.populate().filterContains(aioSkill.fishingData.getRequiredTool())
                    .isEmpty();
            return hasRequiredItem && c.inventory.getFreeSlots() > 20;
        }
    }

    @Override
    public String DebugTaskDescription() {
        return "FishBank";
    }
}
