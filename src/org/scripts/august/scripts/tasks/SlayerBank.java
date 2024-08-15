package org.scripts.august.scripts.tasks;

import org.data.LocationsData;
import org.data.handler.RandomEventsHandler;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

import simple.hooks.wrappers.SimpleObject;
import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleNpc;
import simple.robot.utils.WorldArea;

public class SlayerBank extends Task {

    private SimpleObject bank;
    private SimpleItem item;
    WorldArea reachableBankArea = LocationsData.HOME.getWorldArea();

    @Override
    public void run() {
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
            pool.click("Drink");
            c.sleep(3000, 4000);
            c.keyboard.clickKey(49);
        }
    }

    private boolean shouldSetSlayerTreeTask() {
        return p.within(LocationsData.HOME.getWorldArea()) &&
                (aioSkill.health.contains("None") || (aioSkill.health.contains("Pray")
                        && !c.inventory.populate().filterContains("prayer").isEmpty()));
    }

    private void locateAndOpenBank() {
        aioSkill.status = "Locating Bank";
        if (p.within(LocationsData.HOME.getWorldArea())) {
            bank = c.objects.populate().filterHasAction("Bank").nextNearest();
        }

        if (bank != null && tryOpenBank(bank)) {
            handleBankOperations();
        }
    }

    private boolean tryOpenBank(SimpleObject bank2) {
        bank2.menuAction("Bank");
        return c.onCondition(() -> c.bank.bankOpen(), 600, 10);
    }

    private void handleBankOperations() {
        aioSkill.status = "Bank Opened";
        handleSlayerBanking();
        aioSkill.status = "Closing the Bank";
        c.bank.closeBank();
    }

    private void depositInventory() {
        c.bank.depositInventory();
        c.menuActions.sendAction(57, -1, 786474, 1, "Deposit Inventory", "");
        c.sleepCondition(() -> !c.inventory.inventoryFull());
    }

    private void handleSlayerBanking() {
        aioSkill.status = "Banking for Supplies";
        String itemToWithdraw = aioSkill.health.equals("Prayer") ? "Prayer Potion(4)" : aioSkill.foodString;
        int quantityToWithdraw = 10;

        if (!isItemAvailableInBank(itemToWithdraw)) {
            c.stopScript();
            return;
        }

        depositInventory();

        withdrawEssentialItems();
        c.bank.withdraw("Wealth collector", 1);
        c.bank.withdraw("Bonecrusher", 1);
        withdrawItem(itemToWithdraw, quantityToWithdraw);
        waitForItemInInventory(itemToWithdraw);

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

    private boolean isItemAvailableInBank(String itemName) {
        return !c.bank.populate().filterContains(itemName).isEmpty();
    }

    private void withdrawEssentialItems() {
        withdrawItem("Wealth collector", 1);
        withdrawItem("Bonecrusher", 1);
        withdrawItem(aioSkill.secondaryWeaponString, 1);
    }

    private void withdrawItem(String itemName, int quantity) {
        if (isItemAvailableInBank(itemName)) {
            c.bank.withdraw(c.bank.populate().filterContains(itemName).next().getId(), quantity);
            c.sleep(600, 1000);
        }
    }

    private void waitForItemInInventory(String itemName) {
        c.onCondition(() -> !c.inventory.populate().filterContains(itemName).isEmpty(), 300, 10);
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
