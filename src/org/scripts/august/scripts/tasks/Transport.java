package org.scripts.august.scripts.tasks;

import org.data.LocationsData;
import org.data.handler.RandomEventsHandler;
import org.scripter.Task;
import org.scripts.august.scripts.aioSkill;

import simple.hooks.wrappers.SimpleObject;

public class Transport extends Task {

    private SimpleObject altar;

    @Override
    public void run() {
        if (RandomEventsHandler.needsAction()) {
            return;
        }

        boolean isAtHome = p.within(LocationsData.HOME.getWorldArea());
        boolean hasEssence = false;

        String currentSkill = aioSkill.skill;
        if ("Runecrafting".equals(currentSkill)) {
            hasEssence = !c.inventory.populate().filterContains("essence").isEmpty();
            if (!isAtHome && !hasEssence) {
                c.magic.castHomeTeleport();
                aioSkill.getScriptController().setTask("RunecraftBank");
                return;
            }
        }

        if (isAtHome) {
            handleAtHomeTransport(currentSkill);
        } else {
            handleAwayFromHomeTransport(currentSkill, hasEssence);
        }
    }

    private void handleAtHomeTransport(String currentSkill) {
        String teleportSection = null;
        String teleportLocation = null;
        String taskName = null;

        switch (currentSkill) {
            case "Crafting":
                teleportSection = "Modern";
                teleportLocation = "Lumbridge Home Teleport";
                taskName = "Craft";
                break;

            case "Slayer":
                handleSlayerAtHome();
                return;

            case "Runecrafting":
                teleportSection = "Skilling";
                teleportLocation = "Runecrafting";
                taskName = "Runecraft";
                break;

            case "Woodcutting":
                teleportSection = aioSkill.woodcuttingData.getTeleportSection();
                teleportLocation = aioSkill.woodcuttingData.getTeleportLocation();
                taskName = "Woodcut";
                break;

            case "Thieving":
                teleportSection = aioSkill.thieveData.getTeleportSection();
                teleportLocation = aioSkill.thieveData.getTeleportLocation();
                taskName = "Thieve";
                break;

            case "Mining":
                teleportSection = aioSkill.miningData.getTeleportSection();
                teleportLocation = aioSkill.miningData.getTeleportLocation();
                taskName = "MineWalk";
                break;

            case "Fishing":
                teleportSection = aioSkill.fishingData.getTeleportSection();
                teleportLocation = aioSkill.fishingData.getTeleportLocation();
                taskName = "Fish";
                break;

            default:
                return;
        }
        c.teleporter.open();
        c.teleporter.teleportStringPath(teleportSection, teleportLocation);
        aioSkill.getScriptController().setTask(taskName);

    }

    private void handleSlayerAtHome() {
        if (aioSkill.slayerTask.getKillAmount() == 0 || shouldUsePrayer() || shouldUseFood()
                || aioSkill.slayerTask.getTaskName().isEmpty()) {
            aioSkill.getScriptController().setTask("SlayerTree");
        } else {
            aioSkill.slayerTask.teleportToTask();
            c.onCondition(() -> !p.within(LocationsData.HOME.getWorldArea()), 600, 20);
            aioSkill.getScriptController().setTask("SlayerWalk");
        }
    }

    private void handleAwayFromHomeTransport(String currentSkill, boolean hasEssence) {
        switch (currentSkill) {
            case "Runecrafting":
                altar = c.objects.populate().filter(29631).next();
                if (hasEssence && altar != null) {
                    aioSkill.getScriptController().setTask("Runecraft");
                }
                break;

            case "Slayer":
                if (shouldTeleportToSlayerBank()) {
                    c.magic.castHomeTeleport();
                    aioSkill.getScriptController().setTask("SlayerBank");
                }
                break;

            case "Mining":
                if (!c.inventory.inventoryFull()) {
                    aioSkill.getScriptController().setTask("MineWalk");
                } else {
                    c.magic.castHomeTeleport();
                    aioSkill.getScriptController().setTask("MineBank");
                }
                break;

            default:
                break;
        }
    }

    private boolean shouldTeleportToSlayerBank() {
        return aioSkill.slayerTask.getKillAmount() == 0 || shouldUsePrayer() || shouldUseFood()
                || aioSkill.slayerTask.getTaskName().isEmpty();
    }

    private boolean shouldUsePrayer() {
        return c.inventory.populate().filterContains("pray").isEmpty() && "Prayer".equals(aioSkill.secondOption);
    }

    private boolean shouldUseFood() {
        return c.inventory.populate().filterContains(aioSkill.foodString).isEmpty()
                && "Food".equals(aioSkill.secondOption);
    }

    @Override
    public String DebugTaskDescription() {
        return "Transport";
    }
}
