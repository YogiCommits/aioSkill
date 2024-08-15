package org.data.database;

import net.runelite.api.Deque;
import net.runelite.api.GraphicsObject;
import simple.robot.api.ClientContext;

import java.util.ArrayList;
import java.util.List;

public class GraphicsObjects {

    private static ClientContext c = ClientContext.instance();

    public static class Wilderness {

        public static class Bosses {

            public enum Calvarion {
                LIGHTNING(2346, 2347),
                SHIELD_PUSH(1446, 0);

                int[] ids;

                Calvarion(int... ids) {
                    this.ids = ids;
                }

                public List<GraphicsObject> getGraphicsObjects() {
                    Deque<GraphicsObject> go = c.getClient().getGraphicsObjects();
                    List<GraphicsObject> list = new ArrayList<>();
                    for (GraphicsObject g : go) {
                        if (g.getId() == ids[0] || g.getId() == ids[1]) {
                            list.add(g);
                        }
                    }
                    return list;
                }

            }

        }

    }

    public static class Bosses {

        public enum Nightmare {
            BLACK_ORB(1767);

            int[] ids;

            Nightmare(int... ids) {
                this.ids = ids;
            }

            public List<GraphicsObject> getGraphicsObjects() {
                Deque<GraphicsObject> go = c.getClient().getGraphicsObjects();
                List<GraphicsObject> list = new ArrayList<>();
                for (GraphicsObject g : go) {
                    if (g.getId() == ids[0]) {
                        list.add(g);
                    }
                }
                return list;
            }
        }

    }

}
