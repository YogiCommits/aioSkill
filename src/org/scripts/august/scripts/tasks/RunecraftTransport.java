package org.scripts.august.scripts.tasks;

import org.data.LocationsData;
import org.data.handler.RandomEventsHandler;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;
import simple.hooks.wrappers.SimpleObject;

public class RunecraftTransport extends Task {

    private SimpleObject altar;

    @Override
    public void run() {
        if (RandomEventsHandler.needsAction()) {
            return;
        }

        boolean isAtHome = p.within(LocationsData.HOME.getWorldArea());
        boolean hasEssence = !c.inventory.populate().filterContains("essence").isEmpty();

        if (isAtHome) {
            handleAtHomeTransport();
        } else {
            handleAwayFromHomeTransport(hasEssence);
        }

    }

    private void handleAtHomeTransport() {
        c.teleporter.open();
        c.teleporter.teleportStringPath("Skilling", "Runecrafting");
        aioSkill.getScriptController().setTask("Runecraft");
    }

    private void handleAwayFromHomeTransport(boolean hasEssence) {
        altar = c.objects.populate().filter(29631).next();
        if (hasEssence && altar != null) {
            aioSkill.getScriptController().setTask("Runecraft");
        } else if (!hasEssence) {
            c.magic.castHomeTeleport();
            aioSkill.getScriptController().setTask("RunecraftBank");
        }
    }

    @Override
    public String DebugTaskDescription() {
        return "RunecraftTransport";
    }
}
