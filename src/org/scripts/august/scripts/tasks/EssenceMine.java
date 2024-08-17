package org.scripts.august.scripts.tasks;

import org.data.LocationsData;
import org.data.handler.RandomEventsHandler;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

import simple.hooks.wrappers.SimpleNpc;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.utils.WorldArea;

public class EssenceMine extends Task {

    private SimpleNpc aubury;
    private SimpleObject runeEssence;
    private SimpleObject bank;

    @Override
    public void run() {
        boolean atHome = p.within(LocationsData.HOME.getWorldArea());
        aubury = c.npcs.populate().filterContains("Aubury").next();
        runeEssence = c.objects.populate().nextNearest();

        if (RandomEventsHandler.needsAction() || c.players.getLocal().isAnimating()) {
            c.sleep(400, 800);
            return;
        }

        if (aubury != null && atHome) {
            aioSkill.status = "Teleporting to Essence Mine";
            c.sleep(300, 700);
            aubury.menuAction("Teleport");
            c.onCondition(() -> !atHome, 600, 10);
            return;
        }

        if (runeEssence != null && !p.isAnimating()) {
            aioSkill.status = "Preparing to Mine Rune Essence";
            runeEssence = c.objects.populate().filter(34773).nextNearest();

            if (!c.inventory.inventoryFull()) {
                aioSkill.status = "Mining Rune Essence";
                c.sleep(400, 800);
                runeEssence.menuAction("Mine");
                c.sleep(1000, 2000);
            } else {
                if (!c.bank.bankOpen()) {
                    locateAndOpenBank();
                    c.sleep(500, 1000);
                    c.bank.closeBank();
                }
            }
        }
    }

    private void locateAndOpenBank() {
        aioSkill.status = "Locating Bank";
        c.sleep(300, 600);
        bank = c.objects.populate().filterHasAction("Bank").nextNearest();
        if (bank != null) {
            openBank(bank);
        }
    }

    private void openBank(SimpleObject bank) {
        aioSkill.status = "Opening Bank";
        c.sleep(400, 700);
        bank.menuAction("Bank");
        if (c.onCondition(() -> c.bank.bankOpen(), 600, 10)) {
            handleBankOperations();
        } else {
            SimpleNpc banker = c.npcs.populate().filterHasAction("Bank").nextNearest();
            if (banker != null) {
                aioSkill.status = "Attempting to Open Bank via Banker";
                c.sleep(300, 600);
                banker.menuAction("Bank");
                c.onCondition(() -> c.bank.bankOpen(), 600, 10);
                if (c.bank.bankOpen()) {
                    handleBankOperations();
                }
            }
        }
    }

    private void handleBankOperations() {
        aioSkill.status = "Handling Bank Operations";
        c.sleep(300, 600);
        switch (aioSkill.skill) {
            default:
                if (!c.inventory.populate().isEmpty()) {
                    aioSkill.status = "Depositing Inventory";
                    c.sleep(400, 700);
                    c.menuActions.sendAction(57, -1, 786474, 1, "Deposit Inventory", "");
                    c.onCondition(() -> !c.inventory.inventoryFull(), 800, 1200);
                }
                break;
        }
        aioSkill.status = "Closing the Bank";
    }

    @Override
    public String DebugTaskDescription() {
        return "EssenceMine";
    }
}
