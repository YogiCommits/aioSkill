package org.data.database.minigames;

import net.runelite.api.coords.WorldPoint;
import net.runelite.api.widgets.Widget;
import simple.hooks.wrappers.SimpleWidget;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

public class Barrows {

    public enum Npcs {
        TORAG(1676),
        AHRIM(1672),
        KARIL(1675),
        DHAROK(1673),
        GUTHAN(1674),
        VERAC(1677);

        int id;
        Npcs(int ID) {
            id = ID;
        }

        public int getID() {
            return id;
        }
    }

    public enum Areas {
        OVERWORLD(new WorldArea(new WorldPoint(3546, 3268, 0), new WorldPoint(3584, 3312, 0))),
        TORAG(new WorldArea(new WorldPoint(3564, 9682, 3), new WorldPoint(3576, 9692, 3))),
        GUTHAN(new WorldArea(new WorldPoint(3533, 9699, 3), new WorldPoint(3545, 9708, 3))),
        DHAROK(new WorldArea(new WorldPoint(3548, 9710, 3), new WorldPoint(3560, 9719, 3))),
        VERAC(new WorldArea(new WorldPoint(3567, 9703, 3), new WorldPoint(3580, 9712, 3))),
        AHRIM(new WorldArea(new WorldPoint(3550, 9694, 3), new WorldPoint(3562, 9706, 3))),
        KARIL(new WorldArea(new WorldPoint(3544, 9678, 3), new WorldPoint(3558, 9690, 3))),
        REWARDS(new WorldArea(new WorldPoint(3544, 9688, 0), new WorldPoint(3558, 9701, 0)));

        WorldArea area;
        Areas(WorldArea Area) {
            area = Area;
        }
        public WorldArea getArea() {
            return area;
        }

    }

    public enum Objects {
        TORAGS_STAIRS(20671),
        TORAGS_COFFIN(20721),

        GUTHANS_STAIRS(20669),
        GUTHANS_COFFIN(20722),

        DHAROK_STAIRS(20668),
        DHAROK_COFFIN(20720),

        VERAC_STAIRS(20672),
        VERAC_COFFIN(20772),

        AHRIM_STAIRS(20667),
        AHRIM_COFFIN(20770),

        KARIL_STAIRS(20670),
        KARIL_COFFIN(20771),

        REWARDS_CHEST(20973);

        int id;
        Objects(int ID) {
            id = ID;
        }

        public int getID() {
            return id;
        }

    }

    public enum Widgets {
        TUNNEL(219, 1),
        //[1] = Ahrim Complete
        //[3] = Dharok Complete
        //[5] = Guthan Complete
        //[7] = Karil Complete
        //[9] = Torag Complete
        //[11] = Verac Complete
        BARROWS_BROTHERS(24, 4);

        int mast, sub;
        Widgets(int Master, int Subject) {
            mast = Master;
            sub = Subject;
        }

        public int[] getWidgetID() {
            return new int[]{mast, sub};
        }
        public SimpleWidget getWidget() {
            try {
                return ClientContext.instance().widgets.getWidget(mast, sub);
            } catch (NullPointerException e) {
                return null;
            }
        }
    }
}
