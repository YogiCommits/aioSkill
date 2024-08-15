package org.data.database;

import simple.hooks.queries.SimpleEntityQuery;
import simple.hooks.wrappers.SimpleNpc;
import simple.robot.api.ClientContext;

public class Npcs {

    public static class Skills {
        public enum Thieving {
            MAN(3078, 3079, 3080, 3106, 3107),
            WOMAN(3083, 3084, 3085),
            MASTER_FARMER(3257),
            GUARD(3245),
            KNIGHT_OF_ARDOUGNE(3297),
            PALADIN(3105),
            HERO(3295),
            HAM(2540, 2541);

            private int[] ids;

            Thieving(int... ids) {
                this.ids = ids;
            }

            public int[] getIDs() {
                return ids;
            }

            public SimpleEntityQuery<SimpleNpc> getQuery() {
                return ClientContext.instance().npcs.populate().filter(ids);
            }
        }
    }

    public enum Masters {
        SKILL_MASTER(16380);

        private int[] ids;

        Masters(int... ids) {
            this.ids = ids;
        }

        public int[] getIDs() {
            return ids;
        }

        public SimpleEntityQuery<SimpleNpc> getQuery() {
            return ClientContext.instance().npcs.populate().filter(ids);
        }
    }

    public enum Vyre {
        X;

        public enum Thieving {
            ALL(9713, 9692, 9697, 9692);

            private int[] npcIds;

            Thieving(int... IDS) {
                npcIds = IDS;
            }

            public SimpleEntityQuery<SimpleNpc> getQuery() {
                return ClientContext.instance().npcs.populate().filter(npcIds);
            }
        }
    }

    public enum Stores {
        SIGMUND(3894),
        SAWMILL_OPERATOR(3101);

        private final int npcId;

        Stores(int npcId) {
            this.npcId = npcId;
        }

        public int getID() {
            return npcId;
        }

        public SimpleEntityQuery<SimpleNpc> getQuery() {
            return ClientContext.instance().npcs.populate().filter(npcId);
        }
    }

    public static class Bosses {

        public enum Wilderness {
            CALVARION(11993, 11994),
            SKELETAL_HOUNDS(12107, 12108);

            private int[] ids;

            Wilderness(int... ids) {
                this.ids = ids;
            }

            public int[] getIDs() {
                return ids;
            }

            public SimpleEntityQuery<SimpleNpc> getQuery() {
                return ClientContext.instance().npcs.populate().filter(ids);
            }
        }

        public enum Instanced {
            NIGHTMARE(9427);

            private int[] ids;

            Instanced(int... ids) {
                this.ids = ids;
            }

            public int[] getIDs() {
                return ids;
            }

            public SimpleEntityQuery<SimpleNpc> getQuery() {
                return ClientContext.instance().npcs.populate().filter(ids);
            }
        }
    }
}