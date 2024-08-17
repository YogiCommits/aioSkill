package org.data;

import java.util.List;

import net.runelite.api.coords.WorldPoint;
import simple.hooks.wrappers.SimpleNpc;
import simple.robot.api.ClientContext;

public class CraftingData {
    private static final ClientContext ctx = ClientContext.instance();

    private Task currentTask = null;
    public static WorldPoint ignoreFishingSpot = new WorldPoint(2261, 3377, 0);
    public static WorldPoint ignoreFishingSpot2 = new WorldPoint(2262, 3378, 0);
    public static WorldPoint walkFishingSpot = new WorldPoint(2262, 3380, 0);

    public static class Task {
        private final String teleportSection;
        private final String teleportLocation;
        private final String npcName;
        private final String taskName;
        private final int requiredLevel;

        public Task(String teleportSection, String teleportLocation, String npcName, String taskName,
                int requiredLevel) {
            this.teleportSection = teleportSection;
            this.teleportLocation = teleportLocation;
            this.npcName = npcName;
            this.taskName = taskName;
            this.requiredLevel = requiredLevel;
        }

        public String getTeleportSection() {
            return teleportSection;
        }

        public String getTeleportLocation() {
            return teleportLocation;
        }

        public String getNpcName() {
            return npcName;
        }

        public String getTaskName() {
            return taskName;
        }

        public int getRequiredLevel() {
            return requiredLevel;
        }

        public boolean isTaskfirstOptionPresent() {
            return ctx.objects.populate().filter(taskName).isEmpty();
        }

        public SimpleNpc getTaskNPC() {
            return ctx.npcs.populate().filter(npcName).nextNearest();
        }

        public void teleportfirstOption() {
            if (taskName == null || "".equals(getTeleportSection())) {
                System.out.println("Unknown task. Cannot teleport.");
            } else {
                ctx.magic.castHomeTeleport();
                ctx.sleep(1000, 3000);
                ctx.teleporter.open();
                ctx.teleporter.teleportStringPath(getTeleportSection(), getTeleportLocation());
            }
        }
    }

    private static final List<Task> PREMADE_TASKS = List.of(
            new Task("", "", "", "Uncut Sapphire", 20),
            new Task("", "", "", "Uncut Emerald", 27),
            new Task("", "", "", "Uncut Ruby", 34),
            new Task("", "", "", "Uncut Diamond", 43),
            new Task("", "", "", "Uncut Dragonstone", 55),
            new Task("", "", "", "Uncut Zenyte", 70),
            new Task("", "", "", "Coal", 200), new Task("", "", "", "Luminite Ore", 200));

    public String getTaskName() {
        return currentTask != null ? currentTask.getTaskName() : "No task set";
    }

    public Task fromTaskName(String taskName) {
        return PREMADE_TASKS.stream()
                .filter(task -> task.getTaskName().equals(taskName))
                .findFirst()
                .orElse(new Task("Unknown", "Unknown", "Unknown", "Unknown", 0));
    }

    public Task fromNpcName(String npcName) {
        return PREMADE_TASKS.stream()
                .filter(task -> task.getNpcName().equalsIgnoreCase(npcName))
                .findFirst()
                .orElse(new Task("Unknown", "Unknown", "Unknown", "Unknown", 0));
    }

    public Task getBestTaskForLevel(int level) {
        return PREMADE_TASKS.stream()
                .filter(task -> task.getRequiredLevel() <= level)
                .max((task1, task2) -> Integer.compare(task1.getRequiredLevel(), task2.getRequiredLevel()))
                .orElse(new Task("Unknown", "Unknown", "Unknown", "Unknown", 0));
    }

    public void setTaskByName(String taskName) {
        this.currentTask = fromTaskName(taskName);
    }

    public void setTask(Task task) {
        this.currentTask = task;
    }

    public Task getCurrentTask() {
        return this.currentTask;
    }
}
