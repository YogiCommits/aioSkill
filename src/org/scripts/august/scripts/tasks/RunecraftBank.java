package org.scripts.august.scripts.tasks;

import org.data.LocationsData;
import org.data.handler.RandomEventsHandler;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

import net.runelite.api.Point;
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

        if ((!isAtHome && altar == null) || (isAtHome && hasEssence) || (!isAtHome && !hasEssence)) {
            aioSkill.getScriptController().setTask("Transport");
            return;
        }

        if (!isAtHome && hasEssence && altar != null) {
            aioSkill.getScriptController().setTask("Runecraft");
            return;
        }

        if (!c.bank.bankOpen()) {
            locateAndOpenBank();
        } else {
            handleBankOperations();
        }
    }

    private boolean hasEssenceInInventory() {
        return !c.inventory.populate().filterContains("essence").isEmpty();
    }

    private void locateAndOpenBank() {
        aioSkill.status = "Locating Bank";
        bank = p.within(homeArea) ? c.objects.populate().filterHasAction("Bank").nextNearest() : null;

        if (bank == null) {
            bank = c.objects.populate().filter(homeArea).filterHasAction("Bank").nextNearest();
        }

        if (bank != null) {
            bank.menuAction("Bank");
            c.onCondition(c.bank::bankOpen, 600, 5);
        }
    }

    private void handleBankOperations() {
        aioSkill.status = "Bank Opened";
        if (c.bank.bankOpen()) {
            runecraftBanking();
            aioSkill.status = "Closing the Bank";
            if (hasEssenceInInventory()) {
                c.bank.closeBank();
            }
        }
    }

    private void runecraftBanking() {
        if (c.bank.bankOpen()) {
            if (c.bank.populate().filterContains("essence block").isEmpty()) {
                c.stopScript();
            }
            c.bank.depositInventory();
            c.onCondition(() -> c.inventory.populate().filterContains(runes).isEmpty(), 300, 10);
            if (!c.inventory.populate().filterContains(runes).isEmpty()) {
                depositRunesFromBank();
            }
            withdrawEssence();
        }
    }

    private void depositRunesFromBank() {
        for (String rune : runes) {
            SimpleItem bankItem = c.inventory.populate().filterContains(rune).next();
            if (bankItem != null) {
                c.sleep(300, 600);
                Point clickPosition = getClickPosition(bankItem);
                c.mouse.moveMouse(clickPosition.getX(), clickPosition.getY());
                c.mouse.clickPointWithOption(clickPosition, 6);
                c.sleep(300, 600);
            }
        }
    }

    private void withdrawEssence() {
        c.bank.withdraw("Dense essence block", 28);
        c.sleep(1200, 1800);
        c.onCondition(() -> hasEssenceInInventory(), 300, 10);
    }

    private Point getClickPosition(SimpleItem item) {
        return new Point(
                (int) item.getClickBounds().getCenterX(),
                (int) item.getClickBounds().getCenterY());
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
