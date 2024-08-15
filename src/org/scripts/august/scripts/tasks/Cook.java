package org.scripts.august.scripts.tasks;

import org.data.LocationsData;
import org.data.handler.RandomEventsHandler;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

import simple.hooks.wrappers.SimpleObject;

public class Cook extends Task {

    @Override
    public void run() {
        if (RandomEventsHandler.needsAction()) {
            return;
        }
        if (!p.within(LocationsData.THIEVE.getWorldArea())) {
            aioSkill.getScriptController().setTask("Transport");
            return;
        }
        RandomEventsHandler.needsAction();
        if (c.inventory.inventoryFull()) {
            aioSkill.getScriptController().setTask("Bank");
            return;
        }

        if (c.players.getLocal().isAnimating()) {
            return;
        }

        SimpleObject stall = c.objects.populate().filter(LocationsData.THIEVE.getWorldArea())
                .filter(aioSkill.thieveData.getTaskName())
                .nextNearest();

        if (stall != null) {
            stall.menuAction("Steal-from");
            c.sleepCondition(c.players.getLocal()::isAnimating);
            aioSkill.status = "Stealing from " + stall.getName();
            c.onCondition(() -> !c.players.getLocal().isAnimating(), 600, 10);
        } else {
            c.sleep(600, 1800);
        }
    }

    @Override
    public String DebugTaskDescription() {
        return "Cook";
    }

}
