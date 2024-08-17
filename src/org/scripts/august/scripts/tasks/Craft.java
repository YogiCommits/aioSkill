package org.scripts.august.scripts.tasks;

import org.data.LocationsData;
import org.data.handler.RandomEventsHandler;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

import simple.hooks.wrappers.SimpleItem;

public class Craft extends Task {
    SimpleItem gem;
    private SimpleItem chisel;
    private SimpleItem hammer;

    @Override
    public void run() {
        gem = c.inventory.populate().filterContains(aioSkill.craftingData.getTaskName()).next();
        if (RandomEventsHandler.needsAction()) {
            return;
        }

        if (!p.within(LocationsData.HOME.getWorldArea())) {
            aioSkill.getScriptController().setTask("Transport");
            return;
        }

        if (c.players.getLocal().isAnimating()) {
            return;
        }

        if (gem != null) {
            chisel = c.inventory.populate().filterContains("Chisel").next();
            hammer = c.inventory.populate().filterContains("Hammer").next();
            craft();
        } else {
            aioSkill.getScriptController().setTask("Bank");
        }
    }

    public void craft() {
        gem.menuAction("use");
        if (chisel != null) {
            chisel.getClickBounds();
        }
        if (hammer != null) {
            hammer.getClickBounds();
        }
        double x = chisel.getClickBounds().getCenterX();
        double y = chisel.getClickBounds().getCenterY();
        int intX = (int) x;
        int intY = (int) y;
        c.mouse.moveMouse(intX, intY);
        c.mouse.click(intX, intY, true);
        c.sleep(1200, 1600);
        c.keyboard.clickKey(32);
        c.onCondition(() -> c.inventory.populate().filterContains(aioSkill.craftingData.getTaskName()).isEmpty(), 1000,
                24);
    }

    @Override
    public String DebugTaskDescription() {
        return "Craft";
    }

}
