package org.data;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.data.handler.TeleporterHandler;
import simple.robot.api.ClientContext;

public class SlayerData {
    private static final ClientContext ctx = ClientContext.instance();

    public static class Task {
        private final String teleportSection;
        private final String npcName;
        private final String taskName;
        private final String superiorTaskName;

        public Task(String teleportSection, String npcName, String taskName, String superiorTaskName) {
            this.teleportSection = teleportSection;
            this.npcName = npcName;
            this.taskName = taskName;
            this.superiorTaskName = superiorTaskName;
        }

        public String getTeleportSection() {
            return teleportSection;
        }

        public String getNpcName() {
            return npcName;
        }

        public String getTaskName() {
            return taskName;
        }

        public String getSuperiorTaskName() {
            return superiorTaskName;
        }
    }

    private static final List<Task> TASKS = List.of(
            new Task("", "", "", ""),
            new Task("Training", "Chicken", "Chickens", ""),
            new Task("Training", "Cow", "Cows", ""),
            new Task("Training", "Rock Crab", "Rock Crabs", ""),
            new Task("Training", "Hobgoblin", "Hobgoblins", ""),
            new Task("Training", "Black Knight", "Black Knights", ""),
            new Task("Slayer", "Chaos Druid", "Chaos Druids", ""),
            new Task("Slayer", "Skeleton", "Skeletons", ""),
            new Task("Slayer", "Giant", "Giants", ""),
            new Task("Slayer", "Ghost", "Ghosts", ""),
            new Task("Slayer", "Cave Crawler", "Cave Crawlers", "Chasm Crawler"),
            new Task("Slayer", "Rockslug", "Rockslugs", ""),
            new Task("Slayer", "Cockatrice", "Cockatrices", ""),
            new Task("Slayer", "Pyrefiend", "Pyrefiends", "Flaming pyrelord"),
            new Task("Slayer", "Basilisk", "Basilisks", "Monstrous Basilisk"),
            new Task("Slayer", "TzHaar-Ket", "Tzhaar", ""),
            new Task("Slayer", "Cave horror", "Cave horrors", "Cave Abomination"),
            new Task("Slayer", "Lesser Demon", "Lesser Demons", ""),
            new Task("Slayer", "Greater Demon", "Greater Demons", ""),
            new Task("Slayer", "Green Dragon", "Green Dragons", ""),
            new Task("Slayer", "Blue Dragon", "Blue Dragons", ""),
            new Task("Slayer", "Red Dragon", "Red Dragons", ""),
            new Task("Slayer", "Black Demon", "Black Demons", ""),
            new Task("Slayer", "Black Dragon", "Black Dragons", ""),
            new Task("Slayer", "Hellhound", "Hellhounds", "Hellhound Champion"),
            new Task("Slayer", "Bloodveld", "Bloodvelds", "Insatiable Bloodveld"),
            new Task("Slayer", "Turoth", "Turoths", "Spiked Turoth"),
            new Task("Slayer", "Jelly", "Jellies", "Vitreous Jelly"),
            new Task("Slayer", "Dust Devil", "Dust Devils", "Choke Devil"),
            new Task("Slayer", "Kurask", "Kurasks", "King Kurask"),
            new Task("Slayer", "Nechryael", "Nechryael", "Nechryarch"),
            new Task("Slayer", "Dharok the Wretched", "Ethereal Beings", ""),
            new Task("Slayer", "Abyssal Demon", "Abyssal Demons", "Greater abyssal demon"),
            new Task("Slayer", "Justiciar Warrior", "Justiciar", "Superior Justiciar"),
            new Task("Slayer", "Cave Kraken", "Cave kraken", ""),
            new Task("Slayer", "Sapphire Beast", "Sapphire Beasts", "Superior Sapphire Beasts"),
            new Task("Slayer", "Smoke Devil", "Smoke devils", "Nuclear smoke devil "),
            new Task("Slayer", "Emerald Beast", "Emerald Beasts", "Superior Emerald Beasts"),
            new Task("Slayer", "Ruby Beast", "Ruby Beasts", "Superior Ruby Beasts"),
            new Task("Slayer", "Diamond Beast", "Diamond Beasts", "Superior Diamond Beasts"),
            new Task("Slayer", "Dragonstone Drake", "Dragonstone Drakes", "Superior Dragonstone Drakes"),
            new Task("Unknown", "Unknown", "Unknown", ""));

    public static class SlayerMaster {
        private final String npcName;
        private final String requiredLevel;

        public SlayerMaster(String npcName, String requiredLevel) {
            this.npcName = npcName;
            this.requiredLevel = requiredLevel;
        }

        public String getNpcName() {
            return npcName;
        }

        public String getRequiredLevel() {
            return requiredLevel;
        }
    }

    private final Task task;
    public int killAmount;

    public SlayerData(String taskName, int killAmount) {
        this.task = fromTask(taskName);
        this.killAmount = killAmount;
    }

    private static final List<SlayerMaster> SLAYER_MASTERS = List.of(
            new SlayerMaster("Nixite", "80"),
            new SlayerMaster("Nieve", "65"),
            new SlayerMaster("Vannaka", "45"),
            new SlayerMaster("Turael", "1"));

    public static final String[] LOOT = {
            "ethreal", "monster part ", "slayer", "saradomin",
            "shard", "holy", "justiciar", "rapier", "abyssal", "heart", "gem", "mask", "dark", "barrows", "rock cake",
            "sapphire", "ruby", "emerald", "onyx", "zenyte", "visage",
            "dharok", "tz", "obsid", "key", "restore", "occult", "kraken", "trident", "dragon axe",
            "dragon metal",
            "dragon pickaxe", "dragon harpoon", "$", "bond", "imbued", "bonecrusher", "wealth", "eternal", "feast",
            "lamp", "luck" };

    public static String getBestTaskForLevel(int level) {
        return SLAYER_MASTERS.stream()
                .filter(master -> Integer.parseInt(master.getRequiredLevel()) <= level)
                .max((master1, master2) -> Integer.compare(
                        Integer.parseInt(master1.getRequiredLevel()),
                        Integer.parseInt(master2.getRequiredLevel())))
                .map(SlayerMaster::getNpcName)
                .orElse("Unknown");
    }

    public String getTaskName() {
        return task.getNpcName();
    }

    public int getKillAmount() {
        return killAmount;
    }

    public String getSuperiorTaskNpcName() {
        return task.getSuperiorTaskName();
    }

    public void resetKillCount() {
        this.killAmount = 0;
    }

    public void teleportToTask() {
        if ("Unknown".equals(task.getTeleportSection())) {
            System.out.println("Unknown task. Cannot teleport.");
        } else {
            TeleporterHandler.teleport(task.getTeleportSection(), task.getTaskName());
        }
    }

    public static Task fromTask(String taskName) {
        return TASKS.stream()
                .filter(task -> task.getTaskName().equalsIgnoreCase(taskName.trim()) ||
                        task.getTaskName().toLowerCase().contains(taskName.toLowerCase()))
                .findFirst()
                .orElse(new Task("Unknown", "Unknown", "Unknown", "Unknown"));
    }

    public static SlayerData fromDialogue(String slayerTaskDialogue) {
        if (slayerTaskDialogue == null) {
            return new SlayerData("Unknown", 0);
        }

        String regex = "\\b(\\d+)\\s*x?\\s*([A-Za-z\\s]+)\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(slayerTaskDialogue);

        if (matcher.find()) {
            int killAmount = Integer.parseInt(matcher.group(1));
            String mobName = capitalizeWords(matcher.group(2).trim());
            ctx.log(mobName);
            return new SlayerData(mobName, killAmount);
        } else {
            System.out.println("Invalid slayer task dialogue format.");
            return new SlayerData("Unknown", 0);
        }
    }

    private static String capitalizeWords(String str) {
        String[] words = str.split("\\s+");
        StringBuilder capitalizedWords = new StringBuilder();
        for (String word : words) {
            capitalizedWords.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1).toLowerCase())
                    .append(" ");
        }
        return capitalizedWords.toString().trim();
    }
}
