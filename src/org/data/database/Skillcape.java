package org.data.database;

import simple.hooks.queries.SimpleEntityQuery;
import simple.hooks.queries.SimpleItemQuery;
import simple.hooks.wrappers.SimpleGroundItem;
import simple.hooks.wrappers.SimpleItem;
import simple.robot.api.ClientContext;

public enum Skillcape {
    ATTACK(9747, 9748),
    DEFENCE(9753, 9754),
    STRENGTH(9750, 9751),
    HITPOINTS(9768, 9769),
    RANGED(9756, 9757),
    PRAYER(9759, 9760),
    MAGIC(9762, 9763),
    COOKING(9801, 9802),
    WOODCUTTING(9807, 9808),
    FLETCHING(9783, 9784),
    FISHING(9798, 9799),
    FIREMAKING(9804, 9805),
    CRAFTING(9780, 9781),
    SMITHING(9795, 9796),
    MINING(9792, 9793),
    HERBLORE(9774, 9775),
    AGILITY(9771, 9772),
    THIEVING(9777, 9778),
    SLAYER(9786, 9787),
    FARMING(9810, 9811),
    RUNECRAFTING(9765, 9766),
    HUNTER(9948, 9949),
    CONSTRUCTION(9789, 9790),
    QUEST_POINT(9813, 9814);

    private final int[] ids;

    Skillcape(int untrimmedId, int trimmedId) {
        this.ids = new int[]{untrimmedId, trimmedId};
    }

    public int getUntrimmedId() {
        return ids[0];
    }

    public int getTrimmedId() {
        return ids[1];
    }

    public SimpleItemQuery<SimpleItem> getInvQuery() {
        return ClientContext.instance().inventory.populate().filter(ids);
    }

    public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
        return ClientContext.instance().groundItems.populate().filter(ids);
    }

    public void click(String interaction, boolean trimmed) {
        if (getInvQuery().isEmpty()) { return; }
        getInvQuery().next().click(interaction);
    }

    public void click(boolean trimmed) {
        if (getInvQuery().isEmpty()) { return; }
        getInvQuery().next().click(0);
    }
}

