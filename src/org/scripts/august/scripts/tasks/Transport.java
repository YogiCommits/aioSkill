package org.scripts.august.scripts.tasks;

import org.data.LocationsData;
import org.data.handler.RandomEventsHandler;
import org.data.handler.TeleporterHandler;
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

        boolean hasEssence = hasEssence();
        boolean isAtHome = p.within(LocationsData.HOME.getWorldArea());
        String currentSkill = aioSkill.skill;
        altar = c.objects.populate().filter(29631).next();

        if (isAtHome) {
            handleAtHomeTransport(currentSkill);
        } else {
            handleAwayFromHomeTransport(currentSkill, hasEssence);
        }
    }

    private boolean hasEssence() {
        return !c.inventory.populate().filterContains("essence").isEmpty();
    }

    private void handleAtHomeTransport(String currentSkill) {
        switch (currentSkill) {
            case "Crafting":
                teleportAndSetTask("Modern", "Lumbridge Home Teleport", "Craft");
                break;

            case "Slayer":
                handleSlayerAtHome();
                break;

            case "Runecrafting":
                teleportAndSetTask("Skilling", "Runecrafting", "Runecraft");
                break;

            case "Woodcutting":
                teleportAndSetTask(aioSkill.woodcuttingData.getTeleportSection(),
                        aioSkill.woodcuttingData.getTeleportLocation(), "Woodcut");
                break;

            case "Thieving":
                teleportAndSetTask(aioSkill.thieveData.getTeleportSection(),
                        aioSkill.thieveData.getTeleportLocation(), "Thieve");
                break;

            case "Mining":
                teleportAndSetTask(aioSkill.miningData.getTeleportSection(),
                        aioSkill.miningData.getTeleportLocation(), "MineWalk");
                break;

            case "Fishing":
                teleportAndSetTask(aioSkill.fishingData.getTeleportSection(),
                        aioSkill.fishingData.getTeleportLocation(), "Fish");
                break;

            default:
                break;
        }
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
                if (hasEssence && altar != null) {
                    aioSkill.getScriptController().setTask("Runecraft");
                } else if (!hasEssence) {
                    teleportAndSetTask("Modern", "Lumbridge Home Teleport", "RunecraftBank");
                }
                break;

            case "Slayer":
                if (shouldTeleportToSlayerBank()) {
                    teleportAndSetTask("Modern", "Lumbridge Home Teleport", "SlayerBank");
                }
                break;
            case "Mining":
                if (!c.inventory.inventoryFull()) {
                    aioSkill.getScriptController().setTask("MineWalk");
                } else {
                    teleportAndSetTask("Modern", "Lumbridge Home Teleport", "Bank");
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
        return c.inventory.populate().filterContains("pray").isEmpty() && "Prayer".equals(aioSkill.health);
    }

    private boolean shouldUseFood() {
        return c.inventory.populate().filterContains(aioSkill.foodString).isEmpty() && "Food".equals(aioSkill.health);
    }

    private void teleportAndSetTask(String section, String location, String taskName) {
        TeleporterHandler.teleport(section, location);
        aioSkill.getScriptController().setTask(taskName);
    }

    @Override
    public String DebugTaskDescription() {
        return "Transport";
    }
}
