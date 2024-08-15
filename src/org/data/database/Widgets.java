package org.data.database;

import simple.hooks.wrappers.SimpleWidget;
import simple.robot.api.ClientContext;

public class Widgets {

    public enum Dialogue {
        NPC_DIALOGUE_MASTER(231, 3),
        NPC_DIALOGUE_NAME(231, 4),
        NPC_DIALOGUE_TEXT(231, 6),
        NPC_DIALOGUE_CONTINUE(231, 5),

        MAKE_X(270, 14),
        MAKE_PLANK(270,13),
        PLANK_LOGS(270, 14),
        PLANK_OAK(270, 15),
        PLANK_TEAK(270, 16),
        PLANK_MAHOGANY(270, 17),

        PLAYER_DIALOGUE_MASTER(217, 4),
        PLAYER_DIALOGUE_TEXT(217, 6),
        PLAYER_DIALOGUE_CONTINUE(217, 5),

        OPTION_DIALOGUE_MASTER(219, 1);

        int mast, sub;
        ClientContext c;

        Dialogue(int Master, int Sub) {
            mast = Master;
            sub = Sub;
        }

        public SimpleWidget get() {
            if (c == null) {
                c = ClientContext.instance();
            }
            try {
                return c.widgets.getWidget(mast, sub);
            } catch (NullPointerException e) {
                return null;
            }
        }
    }

    public static class Tasks {
        public enum Slayer {}

        public enum Skilling {
            SKILL(509, 14),
            TASK(509, 15),
            REMAINING(509, 16),
            COLLECTED(509, 17);
            int mast, sub;
            ClientContext c;

            Skilling(int Master, int Sub) {
                mast = Master;
                sub = Sub;
            }

            public SimpleWidget get() {
                if (c == null) {
                    c = ClientContext.instance();
                }
                try {
                    return c.widgets.getWidget(mast, sub);
                } catch (NullPointerException e) {
                    return null;
                }
            }
        }
    }

        public enum Shops {
            SIGMUND(300,16);

            int mast, sub;
            ClientContext c;

            Shops(int Master, int Sub) {
                mast = Master;
                sub = Sub;
            }

            public SimpleWidget get() {
                if (c == null) {
                    c = ClientContext.instance();
                }
                try {
                    return c.widgets.getWidget(mast, sub);
                } catch (NullPointerException e) {
                    return null;
                }
            }
        }

        public enum x {




            CLAIM_COIN_POUCH_CONTINUE(584, 1),


            TELEPORT_MAIN(612, 1),
            TELEPORT_BUTTON(162, 33),

            BARROWS_POTENTIAL(24, 10);

            int mast, sub;
            ClientContext c;

            x(int Master, int Sub) {
                mast = Master;
                sub = Sub;
            }

            public SimpleWidget get() {
                if (c == null) {
                    c = ClientContext.instance();
                }
                try {
                    return c.widgets.getWidget(mast, sub);
                } catch (NullPointerException e) {
                    return null;
                }
            }
        }

        public static class Construction {
            public enum Tables {
                MAIN(458, 3, -1, "null"),
                WOOD_DINING_TABLE(458, 4, 30015492, "<col=ff9040>Wood dining table</col>"),
                OAK_DINING_TABLE(458, 5, 30015493, "<col=ff9040>Oak dining table</col>"),
                OAK_CARVED_TABLE(458, 6, 30015494, "<col=ff9040>Carved oak table</col>"),
                TEAK_DINING_TABLE(458, 7, 30015495, "<col=ff9040>Teak dining table</col>"),
                TEAK_CARVED_TABLE(458, 8, 30015496, "<col=ff9040>Carved teak table</col>"),
                MAHOGANY_TABLE(458, 9, 30015497, "<col=ff9040>Mahogany table</col>"),
                OPULENT_TABLE(458, 10, 30015498, "<col=ff9040>Opulent table</col>");

                int mast, sub, packetID;
                String targ;
                ClientContext c;

                Tables(int Master, int Sub, int PacketID, String Target) {
                    mast = Master;
                    sub = Sub;
                    packetID = PacketID;
                    targ = Target;
                }

                public SimpleWidget get() {
                    if (c == null) {
                        c = ClientContext.instance();
                    }
                    try {
                        return c.widgets.getWidget(mast, sub);
                    } catch (NullPointerException e) {
                        return null;
                    }
                }

                public void sendClickPacket() {
                    try {
                        c.menuActions.sendAction(57, -1, packetID, 1, "Build", targ);
                        c.sleep(250);
                    } catch (Exception e) {
                        System.out.println("Failed to send Interaction Packet to Widget [Click]");
                    }
                }

            }
        }

        public enum Teleporter {
            BUTTON()
        }
    }
