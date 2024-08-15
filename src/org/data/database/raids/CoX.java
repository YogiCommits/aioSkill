package org.data.database.raids;

import net.runelite.api.coords.WorldPoint;
import simple.hooks.queries.SimpleEntityQuery;
import simple.hooks.wrappers.SimpleNpc;
import simple.hooks.wrappers.SimpleObject;
import simple.hooks.wrappers.SimpleWidget;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

public class CoX {

    private static ClientContext c = ClientContext.instance();

    public static class Areas {

        public WorldArea SPAWN_ROOM() {
            try {
                WorldPoint base = Objects.LOBBY_BANK_CHEST.getQuery().next().getLocation();
                return new WorldArea(new WorldPoint(base.getX()-5, base.getY()-11, base.getPlane()), new WorldPoint(base.getX()+22, base.getY()+11, base.getPlane()));
            } catch (NullPointerException e) {
                return null;
            }
        }

    }

    public enum Npcs {

        LIZARDMAN_SHAMAN(7745);

        int[] id;
        Npcs(int... id) {
            this.id = id;
        }

        public int[] getIDs() {return id;}

        public SimpleNpc get() {
            return getQuery().nextNearest();
        }

        public SimpleEntityQuery<SimpleNpc> getQuery() {
            return ClientContext.instance().npcs.populate().filter(id);
        }

    }

    public enum Widgets {
        CLICK_START_MESSAGE(229, 0);

        int x, y;
        Widgets(int X, int Y) {x = X; y = Y;}

        public SimpleWidget get() {
            try {
                return ClientContext.instance().widgets.getWidget(x, y);
            } catch (NullPointerException e) {
                System.out.println("Failed to get widget {"+x+","+y+"}");
            }
            return null;
        }

    }

    public enum Objects {
        LOBBY_BANK_CHEST(47420),
        PASSAGE(29789),
        EXIT_STAIRS(29778),
        TENDRILS(29768);

        int[] ids;

        Objects(int... ids) {
            this.ids = ids;
        }

        public SimpleEntityQuery<SimpleObject> getQuery() {
            return c.objects.populate().filter(ids);
        }
    }

    public enum Animations {
        SHAMAN_JUMP(Npcs.LIZARDMAN_SHAMAN, 7152);

        Npcs target;
        int anim;

        Animations(Npcs target, int anim) {
            this.target = target;
            this.anim = anim;
        }

        public boolean isAnimating() {
            for (SimpleNpc snpc : target.getQuery()) {
                if (snpc.getAnimation() == anim) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class Locations {

        public enum Anchors {
            LOBBY(Objects.EXIT_STAIRS.getQuery().nextNearest().getLocation());

            WorldPoint wp;

            Anchors(WorldPoint wp) {
                this.wp = wp;
            }

            public WorldPoint get() {
                return wp;
            }

        }

        public static WorldPoint getPassageLoc() {
            switch (workoutLayout()) {
                case ROTATION_1:
                    return new WorldPoint(Anchors.LOBBY.get().getX()-10, Anchors.LOBBY.get().getY()+14, Anchors.LOBBY.get().getPlane());

                case ROTATION_2:
                    return new WorldPoint(Anchors.LOBBY.get().getX()+9, Anchors.LOBBY.get().getY()+20, Anchors.LOBBY.get().getPlane());

                case ROTATION_4:
                    return new WorldPoint(Anchors.LOBBY.get().getY()+23, Anchors.LOBBY.get().getY()+10, Anchors.LOBBY.get().getPlane());
            }
            return null;
        }
    }

    public enum Layout {
        ROTATION_1,
        ROTATION_2,
        ROTATION_3,
        ROTATION_4,
        ROTATION_5;
    }

    public static CoX.Layout workoutLayout() {
        try {
            WorldPoint loc = CoX.Objects.LOBBY_BANK_CHEST.getQuery().next().getLocation();
            WorldPoint pas = Objects.PASSAGE.getQuery().nextNearest().getLocation();

            WorldPoint base = CoX.Objects.EXIT_STAIRS.getQuery().next().getLocation();

            if (loc.equals(new WorldPoint(base.getX() + 1, base.getY() + 16, base.getPlane()))) {
                return Layout.ROTATION_1;
            } else if ((loc.equals(new WorldPoint(base.getX() + 5, base.getY() + 13, base.getPlane()))) && (!pas.equals(new WorldPoint(base.getX()+9, base.getY()+20, base.getPlane())))) {
                return Layout.ROTATION_2;
            } else if (loc.equals(new WorldPoint(base.getX() + 5, base.getY() + 9, base.getPlane()))) {
                return Layout.ROTATION_3;
            } else if (loc.equals(new WorldPoint(base.getX() + 3, base.getY() + 9, base.getPlane())) && pas.equals(new WorldPoint(base.getX() + 23, base.getY() + 10, base.getPlane()))) {
                return Layout.ROTATION_4;
            } else if (loc.equals(new WorldPoint(base.getX() + 5, base.getY() + 13, base.getPlane())) && pas.equals(new WorldPoint(base.getX()+9, base.getY()+20, base.getPlane()))) {
                return Layout.ROTATION_5;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return null;
    }

}
