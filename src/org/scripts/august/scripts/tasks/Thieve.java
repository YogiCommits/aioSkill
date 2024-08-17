package org.scripts.august.scripts.tasks;

import org.data.LocationsData;
import org.data.ThievingData;
import org.data.handler.RandomEventsHandler;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

import simple.hooks.filters.SimpleSkills.Skills;
import simple.hooks.wrappers.SimpleObject;

public class Thieve extends Task {

    private int lastExpGained = 0;
    private long lastExpCheckTime = 0;
    private final int CHECK_INTERVAL = 5000; // 5 seconds in milliseconds

    @Override
    public void run() {
        if (aioSkill.best) {
            aioSkill.thieveData = new ThievingData().getBestTaskForLevel(c.skills.realLevel(Skills.THIEVING));
        }
        if (RandomEventsHandler.needsAction()) {
            return;
        }
        if (!p.within(LocationsData.THIEVE.getWorldArea())) {
            aioSkill.getScriptController().setTask("Transport");
            return;
        }
        if (c.inventory.inventoryFull()) {
            aioSkill.getScriptController().setTask("Bank");
            return;
        }
        SimpleObject stall = c.objects.populate().filter(LocationsData.THIEVE.getWorldArea())
                .filter(aioSkill.thieveData.getTaskName())
                .nextNearest();

        int currentExp = c.skills.experience(Skills.THIEVING);
        long currentTime = System.currentTimeMillis();

        if (lastExpGained == 0 || (currentExp == lastExpGained && (currentTime - lastExpCheckTime) >= CHECK_INTERVAL)) {
            if (stall != null) {
                aioSkill.status = "Stealing from " + stall.getName();
                stall.menuAction("Steal-from");
                c.onCondition(() -> c.players.getLocal().isAnimating(), 600, 10);
                c.onCondition(() -> !c.players.getLocal().isAnimating(), 600, 10);
            } else {
                aioSkill.status = "No valid stall found. Waiting...";
                c.sleep(600, 1800);
            }
        } else {
            lastExpGained = currentExp;
            lastExpCheckTime = currentTime;
        }
    }

    @Override
    public String DebugTaskDescription() {
        return "Thieve";
    }
}
