package org.scripts.august.scripts.tasks;

import org.data.LocationsData;
import org.data.WoodcuttingData;
import org.data.handler.RandomEventsHandler;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

import simple.hooks.filters.SimpleSkills.Skills;
import simple.hooks.wrappers.SimpleObject;

public class Woodcut extends Task {

    @Override
    public void run() {
        if (aioSkill.best) {
            aioSkill.woodcuttingData = new WoodcuttingData()
                    .getBestTaskForLevel(c.skills.realLevel(Skills.WOODCUTTING));
        }
        if (RandomEventsHandler.needsAction()) {
            return;
        }
        if (!p.within(LocationsData.WOODCUTTING.getWorldArea())) {
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

        SimpleObject tree = c.objects.populate().filter(LocationsData.WOODCUTTING.getWorldArea())
                .filter(aioSkill.woodcuttingData.getTaskName())
                .nextNearest();

        if (tree != null) {
            tree.menuAction("Chop down");
            c.sleepCondition(c.players.getLocal()::isAnimating);
            aioSkill.status = "Cutting down " + tree.getName();
            c.onCondition(() -> !c.players.getLocal().isAnimating(), 600, 10);
        } else {
            c.sleep(600, 1800);
        }
    }

    @Override
    public String DebugTaskDescription() {
        return "WoodCut";
    }

}
