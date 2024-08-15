package org.scripts.august.scripts.tasks;

import org.data.LocationsData;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

import simple.hooks.wrappers.SimpleObject;

public class Runecraft extends Task {

    private SimpleObject altar;

    @Override
    public void run() {
        boolean isAtHome = p.within(LocationsData.HOME.getWorldArea());
        altar = c.objects.populate().filter(29631).next();
        boolean hasEssence = !c.inventory.populate().filterContains("essence").isEmpty();

        if (!hasEssence && altar != null) {
            aioSkill.getScriptController().setTask("Transport");
            return;
        }

        if (hasEssence) {
            if (altar == null && isAtHome) {
                aioSkill.getScriptController().setTask("Transport");
            } else if (altar != null) {
                altar.menuAction("Bind-random");
                c.sleepCondition(() -> !c.inventory.inventoryFull());
            }
        }
    }

    @Override
    public String DebugTaskDescription() {
        return "RunecraftBank";
    }

}
