package org.scripts.august.scripts.tasks;

import org.data.LocationsData;
import org.data.handler.RandomEventsHandler;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleObject;

public class SmithnSmelt extends Task {

    private SimpleItem smithItem;
    private SimpleItem pAnvil;
    private SimpleItem pflame;
    private SimpleObject bank;

    @Override
    public void run() {
        if (RandomEventsHandler.needsAction() || aioSkill.skill == null) {
            return;
        }
        locateAndOpenBank();

        switch (aioSkill.firstOption) {
            case "Smelt":
                smelt();
                break;
            case "Smith":
                smith();
                break;
        }
    }

    private void smith() {
        pAnvil = c.inventory.populate().filterContains("Personal Anvil").next();
        pAnvil.menuAction("Smith");
        c.sleep(800, 1200);

        selectSmithingItem("Runite Platelegs", "Runite", "Runite platelegs");
        selectSmithingItem("Dragon Platelegs", "Dragon", "Dragon platelegs");

        c.onCondition(() -> c.inventory.getFreeSlots() >= 14, 600, 40);
    }

    private void selectSmithingItem(String option, String material, String item) {
        if (aioSkill.secondOption.equals(option)) {
            c.menuActions.sendAction(57, getMaterialIndex(material), 131465240, 1, "Select", material);
            c.sleep(800, 1200);
            c.menuActions.sendAction(57, getItemIndex(item), 131465243, 1, "Select", item);
            c.sleep(800, 1200);
            c.menuActions.sendAction(57, -1, 131465249, 1, "Create All", "");
            c.sleep(800, 1200);
        }
    }

    private int getMaterialIndex(String material) {
        switch (material) {
            case "Runite":
                return 6;
            case "Dragon":
                return 8;
            default:
                return -1;
        }
    }

    private int getItemIndex(String item) {
        switch (item) {
            case "Runite platelegs":
                return 17;
            case "Dragon platelegs":
                return 6;
            default:
                return -1;
        }
    }

    private void smelt() {
        pflame = c.inventory.populate().filterContains("Pocket flame").next();
        pflame.menuAction("Smelt");
        c.sleep(800, 1200);

        selectSmeltingItem("Runite Ore", "Runite", "Runite bar");
        selectSmeltingItem("Dragon Ore", "Dragon", "Dragon bar");

        c.onCondition(() -> c.inventory.populate().filterContains(aioSkill.secondOption).isEmpty(), 600, 40);
    }

    private void selectSmeltingItem(String oreOption, String material, String bar) {
        if (aioSkill.secondOption.equals(oreOption)) {
            c.menuActions.sendAction(57, getMaterialIndex(material), 131465240, 1, "Select", material);
            c.sleep(800, 1200);
            c.menuActions.sendAction(57, getItemIndex(bar), 131465243, 1, "Select", bar);
            c.sleep(800, 1200);
            c.menuActions.sendAction(57, -1, 131465249, 1, "Create All", "");
            c.sleep(800, 1200);
        }
    }

    private void locateAndOpenBank() {
        aioSkill.status = "Locating Bank";
        if (p.within(LocationsData.HOME.getWorldArea())) {
            bank = c.objects.populate().filterHasAction("Bank").nextNearest();
        }
        if (bank != null && tryOpenBank(bank)) {
            aioSkill.status = "Bank Opened";
            handleBankOperations();
        }
    }

    private void handleBankOperations() {
        aioSkill.status = "Banking for Supplies";

        if (!c.inventory.populate().isEmpty()) {
            depositInventory();
        }

        withdrawEssentialItems();
        c.bank.closeBank();
    }

    private void withdrawEssentialItems() {
        switch (aioSkill.firstOption) {
            case "Smelt":
                withdrawItem("Pocket flame", 1);

                if (aioSkill.secondOption.equalsIgnoreCase("Dragon Ore")) {
                    withdrawItem("Luminite flux", 24);

                } else if (aioSkill.secondOption.equalsIgnoreCase("Runite Ore")) {
                    withdrawItem("Coal dust", 24);

                }
                withdrawItem(aioSkill.secondOption, 24);
                break;
            case "Smith":
                withdrawItem("Personal Anvil", 1);
                withdrawItem("Hammer", 1);

                if (aioSkill.secondOption.equalsIgnoreCase("Dragon Platelegs")) {
                    withdrawItem("Luminite flux", 24);
                    withdrawItem("Dragon bar", 24);
                } else if (aioSkill.secondOption.equalsIgnoreCase("Rune Platelegs")) {
                    withdrawItem("Coal dust", 24);
                    withdrawItem("Runite bar", 24);
                }
                break;
        }
    }

    private void withdrawItem(String itemName, int quantity) {
        if (isItemAvailableInBank(itemName)) {
            SimpleItem item = c.bank.populate().filterContains(itemName).next();
            if (item != null) {
                if (item.getQuantity() >= quantity) {
                    item.menuAction(getWithdrawAction(quantity));
                    waitForItemInInventory(item.getName());
                } else {
                    c.stopScript();
                }
            }
        }
    }

    private String getWithdrawAction(int quantity) {
        switch (quantity) {
            case 1:
                return "Withdraw-1";
            case 10:
                return "Withdraw-10";
            case 24:
                return "Withdraw-All";
            default:
                return "Withdraw-1";
        }
    }

    private void waitForItemInInventory(String itemName) {
        c.onCondition(() -> !c.inventory.populate().filterContains(itemName).isEmpty(), 300, 5);
        if (c.inventory.populate().filterContains(itemName).isEmpty()) {
            c.stopScript();
        }
    }

    private boolean isItemAvailableInBank(String itemName) {
        return !c.bank.populate().filterContains(itemName).isEmpty();
    }

    private void depositInventory() {
        if (!c.inventory.isEmpty()) {
            c.menuActions.sendAction(57, -1, 786474, 1, "Deposit Inventory", "");
            c.sleepCondition(() -> !c.inventory.inventoryFull());
        }
    }

    private boolean tryOpenBank(SimpleObject bank2) {
        bank2.menuAction("Bank");
        return c.onCondition(() -> c.bank.bankOpen(), 500, 10);
    }

    @Override
    public String DebugTaskDescription() {
        return "SmithnSmeltTask";
    }
}
