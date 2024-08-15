package org.data.database;

import simple.hooks.queries.SimpleEntityQuery;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;

public class Objects {

    static ClientContext c = ClientContext.instance();

    public enum Alters {
        WILDERNESS(411),
        DZONE(31624);

        int id;

        Alters(int ID) {
            this.id = ID;
        }

        public int getID() {
            return id;
        }

        public SimpleEntityQuery<SimpleObject> getQuery() {
            return c.objects.populate().filter(id);
        }
    }

    public static class Banks {
        public static final int FEROX_BANK_CHEST = 26711;
        public static final int[] BANK_BOOTH = {6943, 6944};
    }

    public static final int TELEPORTER = 35000;

    public enum Regen {
        CURSED_ALTER(126);

        private final int id;

        Regen(int id) {
            this.id = id;
        }

        public int getID() {
            return id;
        }

        public SimpleEntityQuery<SimpleObject> getQuery() {
            return c.objects.populate().filter(id);
        }
    }

    public static class Minigames {
        public enum Barrows {
            BARROWS_CHEST(20973),
            DHAROK_SARCOPHAGUS(20720),
            DHAROK_STAIRS(20668),
            GUTHAN_SARCOPHAGUS(20722),
            GUTHAN_STAIRS(20669),
            VERAC_SARCOPHAGUS(20772),
            VERAC_STAIRS(20672),
            AHRIM_SARCOPHAGUS(20770),
            AHRIM_STAIRS(20667),
            KARIL_SARCOPHAGUS(20771),
            KARIL_STAIRS(20670),
            TORAG_SARCOPHAGUS(20721),
            TORAG_STAIRS(20671);

            private final int id;

            Barrows(int id) {
                this.id = id;
            }

            public int getID() {
                return id;
            }

            public SimpleEntityQuery<SimpleObject> getQuery() {
                return c.objects.populate().filter(id);
            }
        }
    }

    public static class Skills {
        public enum Thieving {
            CAKE_STALL(11730),
            SILK_STALL(11729),
            CRAFTING_STALL(4874),
            FOOD_STALL(4875),
            GENERAL_STALL(4876),
            MAGIC_STALL(4877),
            SCIMITAR_STALL(4878);

            private final int itemId;

            Thieving(int itemId) {
                this.itemId = itemId;
            }

            public int getID() {
                return itemId;
            }

            public SimpleEntityQuery<SimpleObject> getQuery() {
                return c.objects.populate().filter(itemId);
            }
        }

        public enum Construction {
            OVERWORLD_HOUSE_PORTAL(15478),
            INHOUSE_PORTAL(4525),
            TABLE_EMPTY(15298),
            OAK_DINING_TABLE(13294),
            CARVED_OAK_TABLE(13295),
            TEAK_DINING_TABLE(13296),
            MAHOGANY_DINING_TABLE(13298);

            private int id;

            Construction(int ID) {
                this.id = ID;
            }

            public int getID() {
                return id;
            }

            public SimpleEntityQuery<SimpleObject> getQuery() {
                return c.objects.populate().filter(id);
            }
        }

        public enum Woodcutting {
            TREE(1276, 1277, 1278, 1279, 1280),
            OAK_TREE(4533, 4540, 10820),
            WILLOW_TREE(4534, 4541, 8481, 8482, 8483, 8484, 8485, 8486, 8487, 8488, 10819, 10829, 10833, 10831),
            MAPLE_TREE(4535, 4674, 5126, 8435, 8436, 8437, 8438, 8439, 8440, 8441, 8442, 8443, 8444, 10832, 36681, 36682, 40754, 40755),
            YEW_TREE(4536, 5121, 8503, 8504, 8505, 8506, 8507, 8508, 8509, 8510, 8511, 8512, 8513),
            MAGIC_TREE(4537, 5127, 8396, 8397, 8398, 8399, 8400, 8401, 8402, 8403, 8404, 8405, 8406, 8407, 8408, 8409),
            TEAK_TREE(9036),
            MAHOGANY_TREE(9034);

            private int[] ids;

            Woodcutting(int... IDs) {
                this.ids = IDs;
            }

            public int[] getIDs() {
                return ids;
            }

            public SimpleEntityQuery<SimpleObject> getQuery() {
                return c.objects.populate().filter(ids);
            }
        }

        public enum Mining {
            COPPER_ROCK(10943, 11161, 11162, 11552, 11553, 11554),
            TIN_ROCK(11360, 11361, 11362, 11555, 11556, 11557),
            IRON_ROCK(11364, 11365, 11366, 7455, 7456),
            COAL_ROCK(11367, 11368, 11369, 7456, 7489),
            GOLD_ROCK(11370, 11371, 11372, 7458, 7459, 7460),
            MITHRIL_ROCK(11373, 11374, 11375, 7461, 7462, 7463),
            ADAMANTITE_ROCK(11376, 11377, 11378, 7464, 7465, 7466),
            RUNITE_ROCK(11379, 11380, 11381, 7467, 7468, 7469);

            private int[] ids;

            Mining(int... IDs) {
                this.ids = IDs;
            }

            public int[] getIDs() {
                return ids;
            }

            public SimpleEntityQuery<SimpleObject> getQuery() {
                return c.objects.populate().filter(ids);
            }
        }


    }

    public enum Cannon {
        FULL(6),
        BASE(7),
        STAND(8),
        FURNACE(9);

        private int id;

        Cannon(int ID) {
            this.id = ID;
        }

        public int getID() {
            return id;
        }

        public SimpleEntityQuery<SimpleObject> getQuery() {
            return c.objects.populate().filter(id);
        }
    }

    public enum Trapdoor {
        HAM_ENTRANCE(5492);

        private int id;

        Trapdoor(int ID) {
            this.id = ID;
        }

        public int getID() {
            return id;
        }

        public SimpleEntityQuery<SimpleObject> getQuery() {
            return c.objects.populate().filter(id);
        }
    }

    public enum Wilderness {
        CALVARION_ENTRANCE(46996),
        CALVARION_EXIT(46925);

        private int id;

        Wilderness(int ID) {
            this.id = ID;
        }

        public int getID() {
            return id;
        }

        public SimpleEntityQuery<SimpleObject> getQuery() {
            return c.objects.populate().filter(id);
        }
    }
}
