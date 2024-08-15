package org.scripts.august.scripts.tasks;

import org.data.LocationsData;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

import net.runelite.api.coords.WorldPoint;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.utils.WorldArea;

public class SlayerWalk extends Task {

    private SimpleObject cave;

    @Override
    public void run() {
        String taskName = aioSkill.slayerTask.getTaskName().toLowerCase();

        if (!p.within(LocationsData.SLAYER_BARROWS.getWorldArea()) && taskName.contains("dharok the wretched")) {
            moveToLocation(LocationsData.SLAYER_BARROWS.getWorldArea());
            return;
        }

        if (!p.within(LocationsData.SLAYER_EMERALD.getWorldArea()) && taskName.contains("emerald beast")) {
            moveToLocation(LocationsData.SLAYER_EMERALD.getWorldArea());
            return;
        }

        if (!p.within(LocationsData.SLAYER_KRAKEN.getWorldArea()) && taskName.contains("cave kraken")) {
            moveToLocation(LocationsData.SLAYER_KRAKEN.getWorldArea());
            return;
        }

        if (!p.within(LocationsData.SLAYER_GREEN_DRAGON.getWorldArea()) && taskName.contains("green dragon")) {
            cave = c.objects.populate().filterContains("cave entrance").nextNearest();
            if (cave != null) {
                cave.menuAction("Enter");
                c.sleep(600, 1200);
            }
            moveToLocation(LocationsData.SLAYER_GREEN_DRAGON.getWorldArea());
            return;
        }

        aioSkill.getScriptController().setTask("Combat");
    }

    private void moveToLocation(WorldArea worldArea) {
        WorldPoint stepTile = worldArea.randomTile();
        c.menuActions.step(stepTile);
        c.onCondition(() -> !p.getLocation().equals(stepTile), 600, 20);
    }

    @Override
    public String DebugTaskDescription() {
        return "SlayerWalk";
    }
}
