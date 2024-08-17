package org.data.database;

import simple.robot.api.ClientContext;

public enum Prayer {

    THICK_SKIN(35454985, "Thick Skin"),
    BURST_OF_STRENGTH(35454986, "Burst of Strength"),
    CLARITY_OF_THOUGHT(35454987, "Clarity of Thought"),
    SHARP_EYE(35455003, "Sharp Eye"),
    MYSTIC_WILL(35455006, "Mystic Will"),

    ROCK_SKIN(35454988, "Rock Skin"),
    SUPERHUMAN_STRENGTH(35454989, "Superhuman Strength"),
    IMPROVED_REFLEXES(35454990, "Improved Reflexes"),
    HAWK_EYE(35455004, "Hawk Eye"),
    MYSTIC_LORE(35455007, "Mystic Lore"),

    STEEL_SKIN(35454994, "Steel Skin"),
    ULTIMATE_STRENGTH(35454995, "Ultimate Strength"),
    INCREDIBLE_REFLEXES(35454996, "Incredible Reflexes"),
    EAGLE_EYE(35455005, "Eagle Eye"),
    MYSTIC_MIGHT(35455008, "Mystic Might"),

    RAPID_RESTORE(35454991, "Rapid Restore"),
    RAPID_HEAL(35454992, "Rapid Heal"),

    PROTECT_ITEM(35454993, "Protect Item"),

    CHIVALRY(35455010, "Chivalry"),
    PIETY(35455011, "Piety"),
    RIGOUR(35455009, "Rigour"),
    AUGURY(35455012, "Augury"),

    PROTECT_FROM_MELEE(35454999, "Protect from Melee"),
    PROTECT_FROM_RANGE(35454998, "Protect from Missiles"),
    PROTECT_FROM_MAGIC(35454997, "Protect from Magic");

    int param1;
    String firstOption;

    Prayer(int PARAM1, String firstOption) {
        param1 = PARAM1;
        firstOption = firstOption;
    }

    public void enableP() {
        ClientContext c = ClientContext.instance();
        c.menuActions.sendAction(57, -1, param1, 1, "Activate", "<col=ff9040>" + firstOption + "</col>");
    }

    public void disableP() {
        ClientContext c = ClientContext.instance();
        c.menuActions.sendAction(57, -1, param1, 1, "Deactivate", "<col=ff9040>" + firstOption + "</col>");
    }

}
