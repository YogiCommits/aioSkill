package org.data.database;

import simple.hooks.queries.SimpleEntityQuery;
import simple.hooks.queries.SimpleItemQuery;
import simple.hooks.wrappers.SimpleGroundItem;
import simple.hooks.wrappers.SimpleItem;
import simple.robot.api.ClientContext;

public enum Food {
    RAW_SHRIMP(317),
    SHRIMP(315),
    RAW_ANCHOVIES(321),
    ANCHOVIES(319),
    RAW_TROUT(335),
    TROUT(333),
    RAW_SALMON(331),
    SALMON(329),
    TUNA(361),
    MONKFISH(7946),
    RAW_LOBSTER(377),
    LOBSTER(379),
    RAW_SHARK(383),
    SHARK(385),
    BURNT_FOOD(323, 324, 343, 344, 357, 358, 367, 368, 369, 370, 375, 376, 381, 382, 387, 388, 393, 394, 399),
    CAKE(-1, 1895, 1893, 1891),
    CHOCOLATE_SLICE(1901),
    BREAD(2309);

    private final int[] itemIds;
    ClientContext c = ClientContext.instance();

    Food(int... itemIds) {
        this.itemIds = itemIds;
    }

    public int getID() {
        return itemIds[0];
    }
    public int[] getIDs() {return itemIds;}

    public int[] getBurnt() {
        return itemIds;
    }

    public SimpleItemQuery<SimpleItem> getInvQuery() {
        return c.inventory.populate().filter(itemIds);
    }
    public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
        return c.groundItems.populate().filter(itemIds);
    }

    public void eat() {
        if (this.name().toLowerCase().contains("raw")) {System.out.println("You can't eat raw fish...");return;}
        if (getInvQuery().isEmpty()) {return;}

        getInvQuery().next().menuAction("Eat");
    }
}
