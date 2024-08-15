package org.data.database;

import simple.hooks.queries.SimpleEntityQuery;
import simple.hooks.queries.SimpleItemQuery;
import simple.hooks.wrappers.SimpleGroundItem;
import simple.hooks.wrappers.SimpleItem;
import simple.robot.api.ClientContext;

public class Mage {
    private static ClientContext c = ClientContext.instance();

    public static class Staff {
        public enum Elemental {
            STAFF_OF_AIR(1381),
            STAFF_OF_WATER(1383),
            STAFF_OF_EARTH(1385),
            STAFF_OF_FIRE(1387),
            MYSTIC_AIR_STAFF(1405),
            MYSTIC_WATER_STAFF(1403),
            MYSTIC_EARTH_STAFF(1407),
            MYSTIC_FIRE_STAFF(1401);

            private final int id;

            Elemental(int id) {
                this.id = id;
            }

            public int getId() {
                return id;
            }

            public SimpleItemQuery<SimpleItem> getInvQuery() {
                return c.inventory.populate().filter(id);
            }

            public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                return c.groundItems.populate().filter(id);
            }

            public SimpleItemQuery<SimpleItem> getEquipQuery() {
                return c.equipment.populate().filter(id);
            }

            public void equip() {
                if (getInvQuery().isEmpty()) {
                    return;
                }
                getInvQuery().next().menuAction("Wield");
            }

            public void unequip() {
                if (getEquipQuery().isEmpty()) {
                    return;
                }
                getEquipQuery().next().menuAction("Remove");
            }
        }

        public enum Battlestaff {
            AIR_BATTLESTAFF(1397),
            WATER_BATTLESTAFF(1395),
            EARTH_BATTLESTAFF(1399),
            FIRE_BATTLESTAFF(1393);

            private final int id;

            Battlestaff(int id) {
                this.id = id;
            }

            public int getId() {
                return id;
            }

            public SimpleItemQuery<SimpleItem> getInvQuery() {
                return c.inventory.populate().filter(id);
            }

            public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                return c.groundItems.populate().filter(id);
            }

            public SimpleItemQuery<SimpleItem> getEquipQuery() {
                return c.equipment.populate().filter(id);
            }

            public void equip() {
                if (getInvQuery().isEmpty()) {
                    return;
                }
                getInvQuery().next().menuAction("Wield");
            }

            public void unequip() {
                if (getEquipQuery().isEmpty()) {
                    return;
                }
                getEquipQuery().next().menuAction("Remove");
            }
        }

        public enum Combination {
            LAVA_BATTLESTAFF(3053),
            MUD_BATTLESTAFF(6562),
            STEAM_BATTLESTAFF(11787),
            SMOKE_BATTLESTAFF(11998),
            MYSTIC_LAVA_STAFF(3054),
            MYSTIC_MUD_STAFF(6563),
            MYSTIC_STEAM_STAFF(11789),
            MYSTIC_SMOKE_STAFF(12000);

            private final int id;

            Combination(int id) {
                this.id = id;
            }

            public int getId() {
                return id;
            }

            public SimpleItemQuery<SimpleItem> getInvQuery() {
                return c.inventory.populate().filter(id);
            }

            public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                return c.groundItems.populate().filter(id);
            }

            public SimpleItemQuery<SimpleItem> getEquipQuery() {
                return c.equipment.populate().filter(id);
            }

            public void equip() {
                if (getInvQuery().isEmpty()) {
                    return;
                }
                getInvQuery().next().menuAction("Wield");
            }

            public void unequip() {
                if (getEquipQuery().isEmpty()) {
                    return;
                }
                getEquipQuery().next().menuAction("Remove");
            }
        }

        public enum God {
            SARADOMIN_STAFF(2415),
            GUTHIX_STAFF(2416),
            ZAMORAK_STAFF(2417);

            private final int id;

            God(int id) {
                this.id = id;
            }

            public int getId() {
                return id;
            }

            public SimpleItemQuery<SimpleItem> getInvQuery() {
                return c.inventory.populate().filter(id);
            }

            public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                return c.groundItems.populate().filter(id);
            }

            public SimpleItemQuery<SimpleItem> getEquipQuery() {
                return c.equipment.populate().filter(id);
            }

            public void equip() {
                if (getInvQuery().isEmpty()) {
                    return;
                }
                getInvQuery().next().menuAction("Wield");
            }

            public void unequip() {
                if (getEquipQuery().isEmpty()) {
                    return;
                }
                getEquipQuery().next().menuAction("Remove");
            }
        }
    }

    public enum Wand {
        BEGINNER_WAND(6908),
        APPRENTICE_WAND(6910),
        TEACHER_WAND(6912),
        KODAI_WAND(21006),
        MASTER_WAND(6914);

        private final int id;

        Wand(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(id);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return c.groundItems.populate().filter(id);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return c.equipment.populate().filter(id);
        }

        public void equip() {
            if (getInvQuery().isEmpty()) { return; }
            getInvQuery().next().menuAction("Wield");
        }

        public void unequip() {
            if (getEquipQuery().isEmpty()) { return; }
            getEquipQuery().next().menuAction("Remove");
        }
    }

    public enum Salamander {
        SWAMP_LIZARD(10149),
        ORANGE_SALAMANDER(10146),
        RED_SALAMANDER(10147),
        BLACK_SALAMANDER(10148);

        private final int id;

        Salamander(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(id);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return c.groundItems.populate().filter(id);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return c.equipment.populate().filter(id);
        }

        public void equip() {
            if (getInvQuery().isEmpty()) { return; }
            getInvQuery().next().menuAction("Wield");
        }

        public void unequip() {
            if (getEquipQuery().isEmpty()) { return; }
            getEquipQuery().next().menuAction("Remove");
        }
    }

    public enum Special {
        STAFF_OF_THE_DEAD(11792),
        TOXIC_STAFF_OF_THE_DEAD(12904),
        IBANS_STAFF(1409),
        AHRIMS_STAFF(4710),
        TRIDENT_OF_THE_SEAS(11905),
        TRIDENT_OF_THE_SEAS_FULL(11907),
        TRIDENT_OF_THE_SEAS_EMPTY(11908),
        TRIDENT_OF_THE_SWAMP(12899),
        TRIDENT_OF_THE_SWAMP_EMPTY(12900),
        ANCIENT_STAFF(4675),
        SLAYER_STAFF(4170),
        SANGUINESTI_STAFF(22323),
        NIGHTMARE_STAFF(24422),
        VOLATILE_NIGHTMARE_STAFF(24424),
        HARMONISED_NIGHTMARE_STAFF(24423),
        ELDRITCH_NIGHTMARE_STAFF(24425);

        private int id;

        Special(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(id);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return c.groundItems.populate().filter(id);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return c.equipment.populate().filter(id);
        }

        public void equip() {
            if (getInvQuery().isEmpty()) { return; }
            getInvQuery().next().menuAction("Wield");
        }

        public void unequip() {
            if (getEquipQuery().isEmpty()) { return; }
            getEquipQuery().next().menuAction("Remove");
        }
    }

}

