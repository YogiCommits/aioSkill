package org.data;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import simple.robot.api.ClientContext;

public class SlayerData {
    private static final ClientContext ctx = ClientContext.instance();

    public static class Task {
        private final String teleportSection;
        private final String npcName;
        private final String taskName;
        private final String superiorTaskName;
        private final String pray;

        public Task(String teleportSection, String npcName, String taskName, String superiorTaskName, String pray) {
            this.teleportSection = teleportSection;
            this.npcName = npcName;
            this.taskName = taskName;
            this.superiorTaskName = superiorTaskName;
            this.pray = pray;
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

        public String getPray() {
            return pray;
        }
    }

    private static final List<Task> TASKS = List.of(
            new Task("", "", "", "", "no"),
            new Task("Training", "Chicken", "Chickens", "", "no"),
            new Task("Training", "Cow", "Cows", "", "no"),
            new Task("Training", "Rock Crab", "Rock Crabs", "", "no"),
            new Task("Training", "Hobgoblin", "Hobgoblins", "", "no"),
            new Task("Training", "Black Knight", "Black knights", "", "no"),
            new Task("Slayer", "Chaos Druid", "Chaos Druids", "", "no"),
            new Task("Slayer", "Skeleton", "Skeletons", "", "no"),
            new Task("Slayer", "Giant", "Giants", "", ""),
            new Task("Slayer", "Ghost", "Ghosts", "", "no"),
            new Task("Slayer", "Cave Crawler", "Cave crawlers", "Chasm Crawler", "no"),
            new Task("Slayer", "Rockslug", "Rockslugs", "", "no"),
            new Task("Slayer", "Cockatrice", "Cockatrices", "", ""),
            new Task("Slayer", "Pyrefiend", "Pyrefiends", "Flaming pyrelord", ""),
            new Task("Slayer", "Basilisk", "Basilisks", "Monstrous Basilisk", ""),
            new Task("Slayer", "TzHaar-Ket", "Tzhaar", "", ""),
            new Task("Slayer", "Cave horror", "Cave horrors", "Cave Abomination", ""),
            new Task("Slayer", "Lesser Demon", "Lesser Demons", "", ""),
            new Task("Slayer", "Greater Demon", "Greater Demons", "", ""),
            new Task("Slayer", "Green Dragon", "Green Dragons", "", ""),
            new Task("Slayer", "Blue Dragon", "Blue Dragons", "", ""),
            new Task("Slayer", "Red Dragon", "Red Dragons", "", ""),
            new Task("Slayer", "Black Demon", "Black Demons", "", ""),
            new Task("Slayer", "Black Dragon", "Black Dragons", "", ""),
            new Task("Slayer", "Hellhound", "Hellhounds", "Hellhound Champion", ""),
            new Task("Slayer", "Bloodveld", "Bloodvelds", "Insatiable Bloodveld", ""),
            new Task("Slayer", "Turoth", "Turoths", "Spiked Turoth", ""),
            new Task("Slayer", "Jelly", "Jellies", "Vitreous Jelly", ""),
            new Task("Slayer", "Dust Devil", "Dust Devils", "Choke Devil", ""),
            new Task("Slayer", "Kurask", "Kurasks", "King Kurask", ""),
            new Task("Slayer", "Nechryael", "Nechryael", "Nechryarch", ""),
            new Task("Slayer", "Dharok the Wretched", "Ethereal Beings", "", ""),
            new Task("Slayer", "Abyssal Demon", "Abyssal Demons", "Greater abyssal demon", ""),
            new Task("Slayer", "Justiciar Warrior", "Justiciar", "Superior", ""),
            new Task("Slayer", "Cave Kraken", "Cave kraken", "", ""),
            new Task("Slayer", "Sapphire Beast", "Sapphire Beasts", "Superior", ""),
            new Task("Slayer", "Smoke Devil", "Smoke devils", "Nuclear smoke devil ", ""),
            new Task("Slayer", "Emerald Beast", "Emerald Beasts", "Superior", ""),
            new Task("Slayer", "Ruby Beast", "Ruby Beasts", "Superior", ""),
            new Task("Slayer", "Diamond Beast", "Diamond Beasts", "Superior", ""),
            new Task("Slayer", "Dragonstone Drake", "Dragonstone Drakes", "Superior", ""),
            new Task("Unknown", "Unknown", "Unknown", "", ""));

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

            "Ethereal", "Monster part", "Slayer", "Saradomin",
            "Shard", "Holy", "Justiciar", "Rapier", "Abyssal", "Heart", "Gem",
            "Mask", "Dark", "Barrows", "Rock cake", "Sapphire",
            "Zenyte", "Visage", "Dharok", "Tz", "Obsid", "Key", "Restore", "Occult",
            "Kraken", "Trident", "Dragon axe", "Dragon metal",
            "Dragon pickaxe", "Dragon harpoon", "$", "Bond", "Imbued", "Bonecrusher",
            "Wealth", "Eternal", "Feast", "Lamp", "Luck",

            "Sapphire halberd", "Sapphire staff", "Sapphire warhammer", "Sapphire body", "Sapphire legs",
            "Sapphire shield", "Sapphire shortbow", "Sapphire longbow", "Sapphire weapon seed", "Sapphire frame",
            "Sapphire orb", "Sapphire bowstring", "Sapphire spike", "Sapphire armor seed", "Sapphire orb",
            "Sapphire weapon seed", "Sapphire spike",

            "Ruby halberd", "Ruby staff", "Ruby warhammer", "Ruby body", "Ruby legs", "Ruby shield", "Ruby shortbow",
            "Ruby longbow", "Ruby weapon seed", "Ruby frame", "Ruby orb", "Ruby bowstring", "Ruby spike",
            "Ruby armor seed", "Ruby orb", "Ruby weapon seed", "Ruby spike",

            "Emerald halberd", "Emerald staff", "Emerald warhammer", "Emerald body", "Emerald legs", "Emerald shield",
            "Emerald shortbow", "Emerald longbow", "Emerald weapon seed", "Emerald frame", "Emerald orb",
            "Emerald bowstring", "Emerald spike", "Emerald armor seed", "Emerald orb", "Emerald weapon seed",
            "Emerald spike",

            "Onyx halberd", "Onyx staff", "Onyx warhammer", "Onyx body", "Onyx legs", "Onyx shield", "Onyx shortbow",
            "Onyx longbow", "Onyx weapon seed", "Onyx frame", "Onyx orb", "Onyx bowstring", "Onyx spike",
            "Onyx armor seed", "Onyx orb", "Onyx weapon seed", "Onyx spike",

            "Diamond halberd", "Diamond staff", "Diamond warhammer", "Diamond body", "Diamond legs", "Diamond shield",
            "Diamond shortbow", "Diamond longbow", "Diamond weapon seed", "Diamond frame", "Diamond orb",
            "Diamond bowstring", "Diamond spike", "Diamond armor seed", "Diamond orb", "Diamond weapon seed",
            "Diamond spike",

            "Zentye",

            "Dragonstone halberd", "Dragonstone staff", "Dragonstone warhammer", "Dragonstone body", "Dragonstone legs",
            "Dragonstone shield", "Dragonstone shortbow", "Dragonstone longbow", "Dragonstone weapon seed",
            "Dragonstone frame", "Dragonstone orb", "Dragonstone bowstring", "Dragonstone spike",
            "Dragonstone armor seed", "Dragonstone orb", "Dragonstone weapon seed", "Dragonstone spike"

    };

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

    public String getPrayer() {
        return task.getPray();
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
            ctx.sleep(1000, 3000);
            ctx.teleporter.open();
            ctx.teleporter.teleportStringPath(task.getTeleportSection(), task.getTaskName());
        }
    }

    public static Task fromTask(String taskName) {
        return TASKS.stream()
                .filter(task -> task.getTaskName().equalsIgnoreCase(taskName.trim()) ||
                        task.getTaskName().toLowerCase().contains(taskName.toLowerCase()))
                .findFirst()
                .orElse(new Task("Unknown", "Unknown", "Unknown", "Unknown", ""));
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
