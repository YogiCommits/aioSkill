package org.data;

import java.util.List;

import org.data.handler.TeleporterHandler;

import simple.hooks.wrappers.SimpleNpc;
import simple.robot.api.ClientContext;

public class ThievingData {

    private static final ClientContext ctx = ClientContext.instance();

    private Task currentTask = null;

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

        public boolean isTaskTargetPresent() {
            return ctx.objects.populate().filter(taskName).isEmpty();
        }

        public SimpleNpc getTaskNPC() {
            return ctx.npcs.populate().filter(npcName).nextNearest();
        }

        public void teleportTarget() {
            if (taskName == null || "Unknown".equals(getTeleportSection())) {
                System.out.println("Unknown task. Cannot teleport.");
            } else {
                TeleporterHandler.teleport("Modern", "Lumbridge Home Teleport");
                ctx.sleep(1000, 3000);
                TeleporterHandler.teleport(getTeleportSection(), getTeleportLocation());
            }
        }
    }

    private static final List<Task> PREMADE_TASKS = List.of(
            new Task("Skilling", "Thieving", "Silk merchant", "Silk stall", 20),
            new Task("Skilling", "Thieving", "Spice seller", "Spice stall", 1),
            new Task("Skilling", "Thieving", "Baker", "Baker's stall", 1),
            new Task("Skilling", "Thieving", "", "Coin stall", 65),
            new Task("Skilling", "Thieving", "", "Silver Stall", 45),
            new Task("Skilling", "Thieving", "", "Upgrade gem stall", 65),
            new Task("Skilling", "Thieving", "Fur trader", "Fur stall", 35),
            new Task("Skilling", "Thieving", "", "Sapphire stall", 75),
            new Task("Skilling", "Thieving", "", "Emerald stall", 80),
            new Task("Skilling", "Fishing", "", "Honey", 80),
            new Task("Skilling", "Thieving", "", "Ruby Stall", 85),
            new Task("Unknown", "Unknown", "Unknown", "Unknown", 0));

    public static List<String> ITEMS_T0_SELL = List.of(
            "Silver ore", "Silver bar", "Silk", "Thread", "Fine cloth", "Mystic thread", "Spice", "Bread", "Cake",
            "Chocolate slice", "Chocolate bar", "Fur");

    public String getTaskName() {
        return currentTask != null ? currentTask.getTaskName() : "No task set";
    }

    public Task fromTaskName(String taskName) {
        return PREMADE_TASKS.stream()
                .filter(task -> task.getTaskName().equals(taskName))
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
