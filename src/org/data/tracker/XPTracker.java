package org.data.tracker;

import simple.hooks.filters.SimpleSkills;
import simple.robot.api.ClientContext;

public class XPTracker {

    private static SimpleSkills skills = ClientContext.instance().skills;
    private static int[] startXP;
    private static int[] startLvl;

    public void init() {
        System.out.println("-------------------------------------------");
        System.out.println("\t\t\t XP Tracker");
        System.out.println("-------------------------------------------");
        System.out.println("\t\t*** Initializing... ***");
        startXP = new int[Skills.values().length];
        startLvl = new int[Skills.values().length];
        for (int i = 0; i < Skills.values().length; i++) {
            startXP[i] = skills.experience(i);
        }
        for (int i = 0; i < Skills.values().length; i++) {
            startLvl[i] = skills.level(i);
        }
        System.out.println("\t\t** Loaded  XP Values **");
        System.out.println("\t\t*** Written by ûnix ***");
        System.out.println("-------------------------------------------");
    }

    public void end() {
        for (int i = 0; i < Skills.values().length; i++) {
            if (skills.experience(i) == startXP[i])
                return;

            System.out.println(Skills.values()[i].toString() + ": " + Skills.values()[i].getFormattedXp() + " gained!");
        }
        startXP = null;
        startLvl = null;
        skills = null;
    }

    public enum Skills {
        ATTACK(0),
        DEFENCE(1),
        STRENGTH(2),
        HITPOINTS(3),
        RANGE(4),
        PRAYER(5),
        MAGIC(6),
        COOKING(7),
        WOODCUTTING(8),
        FLETCHING(9),
        FISHING(10),
        FIREMAKING(11),
        CRAFTING(12),
        SMITHING(13),
        MINING(14),
        HERBLORE(15),
        AGILITY(16),
        THIEVING(17),
        SLAYER(18),
        FARMING(19),
        RUNECRAFTING(20),
        HUNTER(21),
        CONSTRUCTION(22);

        int id;

        Skills(int Identity) {
            id = Identity;
        }

        public int getXPGained() {
            return skills.experience(id) - startXP[id];
        }

        public int getLvlGained() {
            return skills.level(id) - startLvl[id];
        }

        public String getFormattedXp() {
            int gained = skills.experience(id) - startXP[id];
            return formatGained(gained);
        }

        public String getLvlGainedAsString() {
            return new String("" + (skills.level(id) - startLvl[id]));
        }

        public String formatGained(int gained) {
            String output = "";
            if (gained > 1000 && gained < 10000) {
                output = String.valueOf(gained).substring(0, 1) + "K";
            } else if (gained > 9999 && gained < 100000) {
                output = String.valueOf(gained).substring(0, 2) + "K";
            } else if (gained > 99999 && gained < 1000000) {
                output = String.valueOf(gained).substring(0, 3) + "K";
            } else if (gained > 999999 && gained < 10000000) {
                output = String.valueOf(gained).substring(0, 1) + "M";
            } else if (gained > 9999999) {
                output = String.valueOf(gained).substring(0, 2) + "M";
            } else {
                output = String.valueOf(gained);
            }

            return output;
        }
    }

}
