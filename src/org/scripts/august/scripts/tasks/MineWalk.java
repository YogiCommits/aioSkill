package org.scripts.august.scripts.tasks;

import org.data.LocationsData;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

import net.runelite.api.coords.WorldPoint;
import simple.robot.utils.WorldArea;

public class MineWalk extends Task {

    @Override
    public void run() {
        String taskName = aioSkill.miningData.getTaskName().toLowerCase();
        boolean atHome = p.within(LocationsData.HOME.getWorldArea());

        if (!p.within(LocationsData.BARROWS_MINING.getWorldArea())
                && ((taskName.contains("oxi ore") || taskName.contains("luminite ore")) && !atHome)) {
            moveToLocation(LocationsData.BARROWS_MINING.getWorldArea());
            return;
        }
        if (atHome && !c.inventory.inventoryFull()) {
            aioSkill.getScriptController().setTask("Transport");
            return;
        }

        aioSkill.getScriptController().setTask("Mine");
    }

    private void moveToLocation(WorldArea worldArea) {
        WorldPoint stepTile = worldArea.randomTile();
        c.menuActions.step(stepTile);
        c.onCondition(() -> p.getLocation().equals(stepTile), 600, 20);
    }

    @Override
    public String DebugTaskDescription() {
        return "MineWalk";
    }
}
