package org.data;

import java.util.List;

import net.runelite.api.coords.WorldPoint;
import simple.hooks.wrappers.SimpleNpc;
import simple.robot.api.ClientContext;

public class FishingData {
    private static final ClientContext ctx = ClientContext.instance();

    private Task currentTask = null;
    public static WorldPoint ignoreFishingSpot = new WorldPoint(2261, 3377, 0);
    public static WorldPoint ignoreFishingSpot2 = new WorldPoint(2262, 3378, 0);
    public static WorldPoint walkFishingSpot = new WorldPoint(2262, 3380, 0);

    public static class Task {
        private final String teleportSection;
        private final String teleportLocation;
        private final String action;
        private final String npcName;
        private final int requiredLevel;
        private final String requiredTool;

        public Task(String teleportSection, String teleportLocation, String action, String npcName,
                int requiredLevel, String requiredTool) {
            this.teleportSection = teleportSection;
            this.teleportLocation = teleportLocation;
            this.action = action;
            this.npcName = npcName;
            this.requiredLevel = requiredLevel;
            this.requiredTool = requiredTool;
        }

        public String getTeleportSection() {
            return teleportSection;
        }

        public String getTeleportLocation() {
            return teleportLocation;
        }

        public String getAction() {
            return action;
        }

        public String getNpcName() {
            return npcName;
        }

        public int getRequiredLevel() {
            return requiredLevel;
        }

        public String getRequiredTool() {
            return requiredTool;
        }

        public boolean isActionPresent() {
            return ctx.objects.populate().filter(npcName).isEmpty();
        }

        public SimpleNpc getActionNPC() {
            return ctx.npcs.populate().filter(action).nextNearest();
        }

        public void teleportToAction() {
            if (npcName == null || "Unknown".equals(getTeleportSection())) {
                System.out.println("Unknown NPC. Cannot teleport.");
            } else {
                ctx.magic.castHomeTeleport();
                ctx.sleep(1000, 3000);
                ctx.teleporter.open();
                ctx.teleporter.teleportStringPath(getTeleportSection(), getTeleportLocation());
            }
        }
    }

    public static List<String> ITEMS_T0_BANK = List.of(
            "shark", "tuna", "shrimp", "trout", "lobster", "salmon", "bass", "raw", "sardine", "mackerel", "trout",
            "cod", "casket", "swordfish", "anchovies");

    private static final List<Task> PREMADE_TASKS = List.of(
            new Task("Skilling", "Woodcutting", "Small Net", "Fishing spot", 1, "Small fishing net"),
            new Task("Skilling", "Woodcutting", "Lure", "Rod Fishing spot", 20, "Fly fishing rod"),
            new Task("Skilling", "Woodcutting", "Cage", "Fishing spot", 40, "Lobster pot"),
            new Task("Skilling", "Woodcutting", "Harpoon", "Fishing spot", 76, "Harpoon"),
            new Task("Skilling", "Woodcutting", "Fish", "Olympian fishing spot", 80, "Harpoon"));

    public String getNpcName() {
        return currentTask != null ? currentTask.getNpcName() : "No NPC set";
    }

    public Task fromNpcName(String npcName) {
        return PREMADE_TASKS.stream()
                .filter(task -> task.getNpcName().equals(npcName))
                .findFirst()
                .orElse(new Task("Unknown", "Unknown", "Unknown", "Unknown", 0, "Unknown"));
    }

    public Task fromAction(String action) {
        return PREMADE_TASKS.stream()
                .filter(task -> task.getAction().equalsIgnoreCase(action))
                .findFirst()
                .orElse(new Task("Unknown", "Unknown", "Unknown", "Unknown", 0, "Unknown"));
    }

    public Task getBestTaskForLevel(int level) {
        return PREMADE_TASKS.stream()
                .filter(task -> task.getRequiredLevel() <= level)
                .max((task1, task2) -> Integer.compare(task1.getRequiredLevel(), task2.getRequiredLevel()))
                .orElse(new Task("Unknown", "Unknown", "Unknown", "Unknown", 0, "Unknown"));
    }

    public void setTaskByNpcName(String npcName) {
        this.currentTask = fromNpcName(npcName);
    }

    public void setTask(Task task) {
        this.currentTask = task;
    }

    public Task getCurrentTask() {
        return this.currentTask;
    }
}
