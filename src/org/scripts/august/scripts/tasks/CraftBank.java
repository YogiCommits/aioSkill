package org.scripts.august.scripts.tasks;

import org.data.LocationsData;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleObject;

public class CraftBank extends Task {

    private SimpleObject bank;

    @Override
    public void run() {
        locateAndOpenBank();
        aioSkill.getScriptController().setTask("Craft");
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

    private boolean tryOpenBank(SimpleObject bank2) {
        bank2.menuAction("Bank");
        return c.onCondition(() -> c.bank.bankOpen(), 500, 10);
    }

    private void handleBankOperations() {
        SimpleItem chisel = c.inventory.populate().filterContains("Chisel").next();
        if (chisel == null
                && (!aioSkill.secondOption.equals("Coal") || !aioSkill.secondOption.equals("Luminite Ore"))) {
            c.bank.withdraw("Chisel", 1);
            c.onCondition(() -> !c.inventory.populate().filter("Chisel").isEmpty(), 600, 10);
        }
        SimpleItem hammer = c.inventory.populate().filterContains("Hammer").next();
        if (hammer == null && (aioSkill.secondOption.equals("Coal") || aioSkill.secondOption.equals("Luminite Ore"))) {
            c.bank.withdraw("Hammer", 1);
            c.onCondition(() -> !c.inventory.populate().filter("Hammer").isEmpty(), 600, 10);
        }
        String[] options = { "Ruby", "Emerald", "Sapphire", "Diamond", "Dragonstone", "Onyx", "Zenyte", "Coal",
                "Luminite" };

        for (String item : options) {
            SimpleItem bankItem = c.inventory.populate().filterContains(item).next();
            if (bankItem != null) {
                c.menuActions.interact(bankItem, 6);
                c.sleep(600, 1200);
            }
        }
        c.bank.withdraw(aioSkill.craftingData.getTaskName(), 27);
    }

    @Override
    public String DebugTaskDescription() {
        return "BankCraft";
    }

}
