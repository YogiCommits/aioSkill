package org.scripts.august.scripts.tasks;

import org.data.FishingData;
import org.data.LocationsData;
import org.data.handler.RandomEventsHandler;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

import simple.hooks.filters.SimpleSkills.Skills;
import simple.hooks.wrappers.SimpleNpc;

public class Fish extends Task {

    @Override
    public void run() {
        if (aioSkill.best) {
            aioSkill.fishingData = new FishingData().getBestTaskForLevel(c.skills.realLevel(Skills.FISHING));
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

        if (aioSkill.fishingData.getTaskName().equalsIgnoreCase("Olympian fishing spot")) {
            c.menuActions.step(FishingData.walkFishingSpot);
            c.onCondition(() -> !p.getLocation().equals(FishingData.walkFishingSpot), 600, 20);
            return;
        }

        SimpleNpc fishingSpot = c.npcs.populate().filter(LocationsData.WOODCUTTING.getWorldArea())
                .filter(npc -> npc.getLocation() != FishingData.ignoreFishingSpot
                        && npc.getLocation() != FishingData.ignoreFishingSpot2)
                .filterHasAction(aioSkill.fishingData.getNpcName())
                .filterContains(aioSkill.fishingData.getTaskName())
                .nextNearest();
        aioSkill.status = "Locating " + fishingSpot.getName();
        fishingSpot.menuAction(aioSkill.fishingData.getNpcName());
        c.sleepCondition(c.players.getLocal()::isAnimating);
        aioSkill.status = "Fishing from " + fishingSpot.getName();
        c.onCondition(() -> !c.players.getLocal().isAnimating(), 600, 10);
    }

    @Override
    public String DebugTaskDescription() {
        return "Fish";
    }

}
