package org.data.database;

import net.runelite.api.coords.WorldPoint;
import simple.robot.utils.WorldArea;

public class Areas {

    public enum Home {
        BANK_AREA(new WorldArea(new WorldPoint(1949, 3273, 0), new WorldPoint(1962, 3286, 0))),
        AFK_AREA(new WorldArea(new WorldPoint(1977, 3295, 0), new WorldPoint(1994, 3312, 0))),
        REGEN_AREA(new WorldArea(new WorldPoint(1936, 3278, 0), new WorldPoint(1948, 3293, 0))),
        TASK_AREA(new WorldArea(new WorldPoint(1969, 3303, 0), new WorldPoint(1978, 3313, 0)));

        WorldArea area;

        Home(WorldArea Area) {
            area = Area;
        }

        public WorldArea get() {
            return area;
        }

        public boolean contains(WorldPoint point) {
            return area.containsPoint(point);
        }
    }

    public static class Cities {
        public enum Ardougne {
            CENTRE(new WorldArea(new WorldPoint(2651, 3294, 0), new WorldPoint(2675, 3320, 0)));

            WorldArea area;

            Ardougne(WorldArea Area) {
                area = Area;
            }

            public WorldArea get() {
                return area;
            }

            public boolean contains(WorldPoint point) {
                return area.containsPoint(point);
            }
        }

        public enum Lumbridge {
            COURTYARD(new WorldArea(new WorldPoint(3216, 3209, 0), new WorldPoint(3227, 3228, 0))),
            FOREST(new WorldArea(new WorldPoint(3160, 3214, 0), new WorldPoint(0, 0, 0))),
            HAM_ENTRANCE(new WorldArea(new WorldPoint(3161, 3245, 0), new WorldPoint(3168, 3253, 0)));

            WorldArea area;

            Lumbridge(WorldArea Area) {
                area = Area;
            }

            public WorldArea get() {
                return area;
            }

            public boolean contains(WorldPoint point) {
                return area.containsPoint(point);
            }
        }

        public enum Vyre {
            PUB(new WorldArea(new WorldPoint(3593, 3386, 0), new WorldPoint(3606, 3397, 0)));

            WorldArea area;

            Vyre(WorldArea Area) {
                area = Area;
            }

            public WorldArea get() {
                return area;
            }

            public boolean contains(WorldPoint point) {
                return area.containsPoint(point);
            }
        }

        public enum AlKharid {
            OUTSIDE_BANK(new WorldArea(new WorldPoint(3272, 3160, 0), new WorldPoint(3280, 3172, 0))),
            PALACE(new WorldArea(new WorldPoint(3282, 3159, 0), new WorldPoint(3304, 3178, 0)));

            WorldArea area;

            AlKharid(WorldArea Area) {
                area = Area;
            }

            public WorldArea get() {
                return area;
            }

            public boolean contains(WorldPoint point) {
                return area.containsPoint(point);
            }
        }

        public enum Draynor {

        }
    }

    public enum Ham_Hideout {
        ENTRANCE(new WorldArea(new WorldPoint(3143, 9638, 0), new WorldPoint(3161, 9655, 0))),
        MIDDLE(new WorldArea(new WorldPoint(3154, 9620, 0), new WorldPoint(3177, 9640, 0)));

        WorldArea area;

        Ham_Hideout(WorldArea Area) {
            area = Area;
        }

        public WorldArea get() {
            return area;
        }

        public boolean contains(WorldPoint point) {
            return area.containsPoint(point);
        }
    }

    public enum DZone {
        FULL_ZONE(new WorldArea(new WorldPoint(2818, 5056, 0), new WorldPoint(2876, 5118, 0))),
        LOBBY(new WorldArea(new WorldPoint(2831, 5090, 0), new WorldPoint(2863, 5106, 0))),
        WOODCUTTING(new WorldArea(new WorldPoint(2823, 5071, 0), new WorldPoint(2840, 5087, 0)));

        WorldArea area;

        DZone(WorldArea Area) {
            area = Area;
        }

        public WorldArea get() {
            return area;
        }

        public boolean contains(WorldPoint locatable) {
            if (area.containsPoint(locatable)) {
                return true;
            }
            return false;
        }

        public enum Dungeon {
            FULL_ZONE(new WorldArea(new WorldPoint(1900, 8960, 1), new WorldPoint(1970, 9050, 1))),
            LADDER(new WorldArea(new WorldPoint(1926, 9001, 1), new WorldPoint(1942, 9017, 1))),
            GREEN_DRAGONS(new WorldArea(new WorldPoint(1903, 8991, 1), new WorldPoint(1925, 9016, 1))),
            ROCKS(new WorldArea(new WorldPoint(1932, 9016, 1), new WorldPoint(1940, 9022, 1))),
            NECHS(new WorldArea(new WorldPoint(1926, 8985, 1), new WorldPoint(1952, 9002, 1))),
            DARK_BEASTS(new WorldArea(new WorldPoint(1918, 8962, 1), new WorldPoint(1938, 8984, 1))),
            ABBY_DEMONS(new WorldArea(new WorldPoint(1942, 8964, 1), new WorldPoint(1954, 8980, 1)));

            WorldArea area;

            Dungeon(WorldArea Area) {
                area = Area;
            }

            public WorldArea getArea() {
                return area;
            }

            public boolean contains(WorldPoint locatable) {
                if (area.containsPoint(locatable)) {
                    return true;
                }
                return false;
            }
        }
    }

    public enum Bosses {
        NIGHTMARE_LOBBY(new WorldArea(new WorldPoint(3795, 9748, 1), new WorldPoint(3820, 9771, 1))),
        NIGHTMARE(new WorldArea(new WorldPoint(3861, 9940, 3), new WorldPoint(3883, 9962, 3)));

        WorldArea area;

        Bosses(WorldArea Area) {
            area = Area;
        }

        public WorldArea get() {
            return area;
        }

        public boolean contains(WorldPoint locatable) {
            if (area.containsPoint(locatable)) {
                return true;
            }
            return false;
        }
    }

    public static class Wilderness {
        public enum Bosses {
            CALVARION(new WorldArea(new WorldPoint(1877, 11533, 1), new WorldPoint(1896, 11554, 1))),
            CALVARION_ENT(new WorldArea(new WorldPoint(3177, 3675, 0), new WorldPoint(3183, 3685, 0)));

            WorldArea area;

            Bosses(WorldArea area) {
                this.area = area;
            }

            public WorldArea get() {
                return area;
            }

            public boolean contains(WorldPoint point) {
                return area.containsPoint(point);
            }
        }
    }

}
