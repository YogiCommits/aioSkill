package org.data.database;

import simple.hooks.queries.SimpleEntityQuery;
import simple.hooks.queries.SimpleItemQuery;
import simple.hooks.wrappers.SimpleGroundItem;
import simple.hooks.wrappers.SimpleItem;
import simple.robot.api.ClientContext;

public enum Potions {
    PRAYER_POTION(new int[]{143, 141, 139, 2434}),
    RESTORE_POTION(new int[]{131, 129, 127, 2430}),
    SUPER_RESTORE_POTION(new int[]{3030, 3028, 3026, 3024}),
    ENERGY_POTION(new int[]{3014, 3012, 3010, 3008}),
    SUPER_ENERGY_POTION(new int[]{3022, 3020, 3018, 3016}),
    STAMINA_POTION(new int[]{12631, 12629, 12627, 12625}),

    ATTACK_POTION(new int[]{125, 123, 121, 2428}),
    STRENGTH_POTION(new int[]{119, 117, 115, 113}),
    DEFENCE_POTION(new int[]{137, 135, 133, 2432}),
    RANGING_POTION(new int[]{169, 171, 173, 2444}),
    MAGIC_POTION(new int[]{3046, 3044, 3042, 3040}),
    COMBAT_POTION(new int[]{9745, 9743, 9741, 9739}),

    SUPER_ATTACK_POTION(new int[]{149, 147, 145, 2436}),
    SUPER_STRENGTH_POTION(new int[]{161, 159, 157, 2440}),
    SUPER_DEFENCE_POTION(new int[]{167, 165, 163, 2442}),
    SUPER_COMBAT_POTION(new int[]{12701, 12699, 12697, 12695}),

    ANTIFIRE_POTION(new int[]{2458, 2456, 2454, 2452}),
    EXTENDED_ANTIFIRE_POTION(new int[]{11957, 11955, 11953, 11951}),
    SUPER_ANTIFIRE_POTION(new int[]{21987, 21984, 21981, 21978}),
    EXTENDED_SUPER_ANTIFIRE_POTION(new int[]{22218, 22215, 22212, 22209}),

    SARADOMIN_BREW(new int[]{6691, 6689, 6687, 6685}),

    DIVINE_SUPER_ATTACK_POTION(new int[]{23706, 23703, 23700, 23697}),
    DIVINE_SUPER_STRENGTH_POTION(new int[]{23718, 23715, 23712, 23709}),
    DIVINE_SUPER_DEFENCE_POTION(new int[]{23730, 23727, 23724, 23721}),
    DIVINE_SUPER_COMBAT_POTION(new int[]{23694, 23691, 23688, 23685}),

    ANTIPOISON(new int[]{179, 177, 175, 2446}),
    SUPER_ANTIPOISON(new int[]{185, 183, 181, 2448}),
    ANTIDOTEx(new int[]{5949, 5947, 5945, 5943}),
    ANTIDOTExx(new int[]{5958, 5956, 5954, 5952}),
    ANTI_VENOM(new int[]{12911, 12909, 12907, 12905}),
    ANTI_VENOMx(new int[]{12919, 12917, 12915, 12913}),
    ZAMORAK_BREW(new int[] {193, 191, 189, 2450});

    private final int[] doses;
    ClientContext c = ClientContext.instance();

    Potions(int[] doses) {
        this.doses = doses;
    }

    public int[] getAllIDs() {
        return doses;
    }

    public int getFour() {
        return doses[3];
    }

    public int getThree() {
        return doses[2];
    }

    public int getTwo() {
        return doses[1];
    }

    public int getOne() {
        return doses[0];
    }

    public SimpleItemQuery<SimpleItem> getInvQuery() {
        return c.inventory.populate().filter(doses);
    }
    public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
        return c.groundItems.populate().filter(doses);
    }

    public void drink() {
        if (!c.inventory.populate().filter(getOne()).isEmpty()) {
            c.inventory.next().menuAction("Drink");
        } else if (!c.inventory.populate().filter(getTwo()).isEmpty()) {
            c.inventory.next().menuAction("Drink");
        } else if (!c.inventory.populate().filter(getThree()).isEmpty()) {
            c.inventory.next().menuAction("Drink");
        } else if (!c.inventory.populate().filter(getFour()).isEmpty()) {
            c.inventory.next().menuAction("Drink");
        } else {
            System.out.println("Cant drink " + this.name() + "... There's none in our inventory!");
        }
    }
}

