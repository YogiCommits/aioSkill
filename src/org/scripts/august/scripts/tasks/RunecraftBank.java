package org.scripts.august.scripts.tasks;

import org.data.LocationsData;
import org.data.handler.RandomEventsHandler;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

import simple.hooks.wrappers.SimpleObject;
import simple.hooks.wrappers.SimpleItem;
import simple.robot.utils.WorldArea;

public class RunecraftBank extends Task {
    private SimpleObject altar;
    private SimpleObject bank;
    private WorldArea homeArea = LocationsData.HOME.getWorldArea();

    @Override
    public void run() {
        if (RandomEventsHandler.needsAction()) {
            return;
        }

        altar = c.objects.populate().filter(29631).next();
        boolean isAtHome = p.within(homeArea);
        boolean hasEssence = hasEssenceInInventory();

        if (isAtHome && !hasEssence) {
            handleBankOperations();
            return;
        }

        if ((!isAtHome && altar == null && !hasEssence) || (isAtHome && hasEssence)) {
            aioSkill.getScriptController().setTask("RunecraftTransport");
            return;
        }
        if (!isAtHome && hasEssence && altar != null) {
            aioSkill.getScriptController().setTask("Runecraft");
            return;
        }
    }

    private boolean hasEssenceInInventory() {
        return !c.inventory.populate().filterContains("essence").isEmpty();
    }

    private void locateAndOpenBank() {
        aioSkill.status = "Locating Bank";
        bank = c.objects.populate().filterHasAction("Bank").nextNearest();

        if (bank != null) {
            bank.menuAction("Bank");
            c.onCondition(c.bank::bankOpen, 600, 5);
        }
    }

    private void handleBankOperations() {
        locateAndOpenBank();
        if (c.bank.bankOpen()) {
            runecraftBanking();
        }
        if (hasEssenceInInventory()) {
            aioSkill.status = "Closing the Bank";
            c.bank.closeBank();
        }
    }

    private void depositInventory() {
        if (!c.inventory.populate().isEmpty()) {
            c.menuActions.sendAction(57, -1, 786474, 1, "Deposit Inventory", "");
            c.sleepCondition(() -> c.inventory.getFreeSlots() == 28);
        }
    }

    private void runecraftBanking() {
        if (c.bank.populate().filterContains("essence block").isEmpty()) {
            c.stopScript();
            return;
        }
        if (c.inventory.getFreeSlots() != 28) {
            depositInventory();
        }
        withdrawEssence();
    }

    private void withdrawEssence() {
        withdrawItem("essence block", 28);
        c.onCondition(this::hasEssenceInInventory, 300, 10);
    }

    private void withdrawItem(String itemName, int quantity) {
        SimpleItem item = c.bank.populate().filterContains(itemName).next();
        if (item != null) {
            String action = quantity == 28 ? "Withdraw-All" : "Withdraw-10";
            item.menuAction(action);
            waitForItemInInventory(itemName);
        }
    }

    private void waitForItemInInventory(String itemName) {
        c.onCondition(() -> !c.inventory.populate().filterContains(itemName).isEmpty(), 300, 5);
    }

    @Override
    public String DebugTaskDescription() {
        return "RunecraftBank";
    }

    private static final String[] runes = {
            "Air rune", "Mind rune", "Water rune", "Earth rune", "Fire rune", "Body rune",
            "Cosmic rune", "Chaos rune", "Nature rune", "Law rune", "Death rune", "Astral rune",
            "Blood rune", "Soul rune", "Wrath rune", "Mist rune", "Dust rune", "Mud rune",
            "Smoke rune", "Steam rune", "Lava rune", "Skilling token"
    };
}
