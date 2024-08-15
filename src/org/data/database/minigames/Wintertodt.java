package org.data.database.minigames;

import net.runelite.api.coords.WorldPoint;
import simple.hooks.queries.SimpleEntityQuery;
import simple.hooks.queries.SimpleItemQuery;
import simple.hooks.wrappers.*;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

public class Wintertodt {

    private ClientContext c = ClientContext.instance();

    /* items */
    public enum Items {
        BRUMA_ROOT(20695),
        BRUMA_KINDLING(20696),
        REWARDS_CRATE(20703),
        BURNT_PAGES(20718),
        BRUMA_TORCH(20720),

        POT_UNF(20697),
        HERB(20698);

        private final int id;

        Items(int id) {
            this.id = id;
        }

        public int getID() {
            return id;
        }

        public SimpleItem get() {
            return getInvQuery().next();
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return ClientContext.instance().inventory.populate().filter(id);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return ClientContext.instance().groundItems.populate().filter(id);
        }
    }

    public enum Potions {
        REJUV_POTION(20702, 20701, 20700, 20699);

        int[] ids;
        Potions(int... ids) {
            this.ids = ids;
        }

        public int[] getIDs() {
            return ids;
        }

        public SimpleItem get() {
            return getInvQuery().next();
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return ClientContext.instance().inventory.populate().filter(ids);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return ClientContext.instance().groundItems.populate().filter(ids);
        }

    }

    /* objects */
    public enum Objects {
        BANK_CHEST(29321),
        DOOR(29322),
        DEADLY_SNOW(26690),
        BRAZIER_UNLIT(29312),
        BRAZIER_LIT(29314),
        BRAZIER_BROKEN(29313),
        BRUMA_TREE(29311),

        HERB_ROOTS(29315),

        HAMMER_BOX(29316),
        KNIFE_BOX(29317),
        AXE_BOX(29318),
        TINDERBOX_BOX(29319),
        POTION_BOX(29320)
        ;

        private final int id;

        Objects(int id) {
            this.id = id;
        }

        public int getID() {
            return id;
        }

        public SimpleObject get() {
            return getQuery().next();
        }

        public SimpleEntityQuery<SimpleObject> getQuery() {
            return ClientContext.instance().objects.populate().filter(id);
        }
    }

    /* npcs */
    public enum Npcs {
        PYROMANCER(7371),
        DEAD_PYROMANCER(7372);

        private final int id;

        Npcs(int id) {
            this.id = id;
        }

        public int getID() {
            return id;
        }

        public SimpleNpc get() {
            return getQuery().next();
        }

        public SimpleEntityQuery<SimpleNpc> getQuery() {
            return ClientContext.instance().npcs.populate().filter(id);
        }
    }


    /* anims */
    public enum Anims {
        FM_ANIMATION(832);

        private final int id;

        Anims(int id) {
            this.id = id;
        }

        public int getID() {
            return id;
        }
    }



    /* tiles & areas */
    public enum Tiles {
        SAFE_WC_SPOT(new WorldPoint(1622, 3988, 0)),
        WALK_TO_LEAVE(new WorldPoint(1629, 3974, 0)),
        WALK_TO_ENTER(new WorldPoint(1630, 3956, 0)),
        SNOWFALL_BRAZIER_CHECK(new WorldPoint(1621, 3998, 0)),
        PYRO_CHECK(new WorldPoint(1619, 3996, 0)),
        HERB_CHECK(new WorldPoint(1611, 4007, 0));

        private final WorldPoint worldPoint;

        Tiles(WorldPoint worldPoint) {
            this.worldPoint = worldPoint;
        }

        public WorldPoint getWorldPoint() {
            return worldPoint;
        }
    }


    public enum Areas {
        FM_TILES(new WorldPoint[]{
                new WorldPoint(1619, 3999, 0),
                new WorldPoint(1619, 3998, 0),
                new WorldPoint(1619, 3997, 0),
                new WorldPoint(1622, 3996, 0),
                new WorldPoint(1621, 3996, 0),
                new WorldPoint(1620, 3996, 0)
        }),
        BANK_AREA(new WorldArea(
                new WorldPoint(1641, 3942, 0),
                new WorldPoint(1638, 3946, 0)
        )),
        OUTSIDE_AREA(new WorldArea(
                new WorldPoint(1638, 3963, 0),
                new WorldPoint(1624, 3937, 0)
        )),
        LOBBY(new WorldArea(
                new WorldPoint(1636, 3989, 0),
                new WorldPoint(1624, 3967, 0)
        )),
        PRODUCTION(new WorldArea(
                new WorldPoint(1623, 4000, 0),
                new WorldPoint(1608, 3987, 0)
        )),
        BRAZIER_CHECK(new WorldArea(
                new WorldPoint(1619, 3996, 0),
                new WorldPoint(1623, 4000, 0)
        ));

        private final WorldPoint[] worldPoints;
        private final WorldArea worldArea;

        Areas(WorldPoint[] worldPoints) {
            this.worldPoints = worldPoints;
            this.worldArea = null;
        }

        Areas(WorldArea worldArea) {
            this.worldArea = worldArea;
            this.worldPoints = null;
        }

        public WorldPoint[] getWorldPoints() {
            return worldPoints;
        }

        public WorldArea getWorldArea() {
            return worldArea;
        }
    }


    /* widgets */
    public enum Widgets {
        HEALTH_WIDGET(new int[]{396, 21});

        private final int[] widget;

        Widgets(int[] widget) {
            this.widget = widget;
        }

        public SimpleWidget getWidget() {
            try {
                return ClientContext.instance().widgets.getWidget(widget[0], widget[1]);
            } catch (NullPointerException e) {
                throw new NullPointerException("We can't seem to grab that Widget right now!");
            }
        }

        public int[] getIds() {
            return widget;
        }
    }


    public WorldArea getSnowfallBounds() {
        return null;
    }

}
