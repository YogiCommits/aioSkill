package org.scripts.august.scripts.tasks;

import org.data.LocationsData;
import org.data.handler.RandomEventsHandler;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

import simple.hooks.wrappers.SimpleObject;
import simple.hooks.wrappers.SimpleItem;
import simple.robot.utils.WorldArea;

public class SlayerBank extends Task {

    private SimpleObject bank;
    private SimpleItem item;
    WorldArea reachableBankArea = LocationsData.HOME.getWorldArea();
    private String itemToWithdraw;

    @Override
    public void run() {
        itemToWithdraw = aioSkill.firstOption.equals("Prayer") ? "Prayer potion(4)" : aioSkill.foodString;

        if (c.prayers.quickPrayers()) {
            c.prayers.quickPrayers(false);
        }
        if (RandomEventsHandler.needsAction()) {
            return;
        }
        if (shouldUsePool()) {
            usePool();
            return;
        }

        if (shouldTransportToTask()) {
            aioSkill.getScriptController().setTask("Transport");
            return;
        }

        if (shouldSetSlayerTreeTask()) {
            aioSkill.getScriptController().setTask("SlayerTree");
            return;
        }

        if (!c.bank.bankOpen()) {
            locateAndOpenBank();
            c.sleep(1200, 1800);
            if (c.bank.bankOpen()) {
                handleBankOperations();
                return;
            }
            return;
        }

        if (shouldTransportAfterBank()) {
            aioSkill.getScriptController().setTask("Transport");
        }
    }

    private boolean shouldTransportToTask() {
        return !p.within(LocationsData.HOME.getWorldArea())
                && (aioSkill.slayerTask.getKillAmount() != 0 || aioSkill.slayerTask.getTaskName().isEmpty());
    }

    private boolean shouldUsePool() {
        return c.combat.healthPercent() <= 75 || c.prayers.prayerPercent() <= 75
                && p.within(LocationsData.HOME.getWorldArea());
    }

    private void usePool() {
        SimpleObject pool = c.objects.populate().filterContains("pool").next();
        if (pool != null) {
            pool.menuAction("Drink");
            c.onCondition(() -> c.dialogue.dialogueOpen(), 500, 10);
        }
        if (c.dialogue.dialogueOpen()) {
            c.dialogue.clickDialogueOption(1);
        }
    }

    private boolean shouldSetSlayerTreeTask() {
        return p.within(LocationsData.HOME.getWorldArea()) &&
                (aioSkill.firstOption.contains("None") || (aioSkill.firstOption.contains("Pray")
                        && !c.inventory.populate().filterContains("prayer").isEmpty()));
    }

    private void locateAndOpenBank() {
        aioSkill.status = "Locating Bank";
        if (p.within(LocationsData.HOME.getWorldArea())) {
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
            c.sleepCondition(() -> !c.inventory.inventoryFull());
        }
    }

    private void handleBankOperations() {
        aioSkill.status = "Banking for Supplies";

        if (c.inventory.getFreeSlots() != 28) {
            depositInventory();
        }

        if (!isItemAvailableInBank(itemToWithdraw)) {
            c.stopScript();
            return;
        }

        withdrawEssentialItems();

    }

    private boolean isItemAvailableInBank(String itemName) {
        return !c.bank.populate().filterContains(itemName).isEmpty();
    }

    private void withdrawEssentialItems() {
        withdrawItem(itemToWithdraw, 10);
        withdrawItem("Wealth collector", 1);
        withdrawItem("Bonecrusher", 1);
        if (needsPrimaryWeapon()) {
            withdrawItem(aioSkill.primaryWeaponString, 1);
        }

        if (needsSecondaryWeapon()) {
            withdrawItem(aioSkill.secondaryWeaponString, 1);
        }

        if (!aioSkill.boost.isEmpty()) {
            withdrawItem(aioSkill.boost, 1);
        }
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

    private boolean needsPrimaryWeapon() {
        return c.equipment.populate().filterContains(aioSkill.primaryWeaponString).isEmpty()
                && c.inventory.populate().filterContains(aioSkill.primaryWeaponString).isEmpty();
    }

    private boolean needsSecondaryWeapon() {
        return c.equipment.populate().filterContains(aioSkill.primaryWeaponString).isEmpty()
                && c.inventory.populate().filterContains(aioSkill.primaryWeaponString).isEmpty();
    }

    private boolean shouldTransportAfterBank() {
        return aioSkill.skill.equals("Slayer") && !p.within(LocationsData.HOME.getWorldArea())
                && aioSkill.slayerTask.getKillAmount() != 0;
    }

    @Override
    public String DebugTaskDescription() {
        return "SlayerBank";
    }
}
