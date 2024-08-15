package org.data.database.bosses;

import net.runelite.api.coords.WorldPoint;
import org.data.database.minigames.FightCaves;
import simple.hooks.queries.SimpleEntityQuery;
import simple.hooks.queries.SimpleIdQuery;
import simple.hooks.wrappers.SimpleNpc;
import simple.hooks.wrappers.SimpleObject;
import simple.hooks.wrappers.SimpleProjectile;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Godwars {

    private static ClientContext c = ClientContext.instance();


    public enum Areas {
        MIDDLE(new WorldArea(new WorldPoint(2875, 5347, 2), new WorldPoint(2879, 5335, 2), new WorldPoint(2891, 5335, 2), new WorldPoint(2900, 5324, 2), new WorldPoint(2904, 5306, 2), new WorldPoint(2913, 5304, 2), new WorldPoint(2913, 5298, 2), new WorldPoint(2909, 5281, 2), new WorldPoint(2878, 5278, 2), new WorldPoint(2867, 5278, 2), new WorldPoint(2861, 5276, 2), new WorldPoint(2846, 5291, 2), new WorldPoint(2846, 5293, 2), new WorldPoint(2844, 5296, 2), new WorldPoint(2844, 5312, 2), new WorldPoint(2851, 5330, 2), new WorldPoint(2851, 5337, 2), new WorldPoint(2865, 5349, 2), new WorldPoint(2874, 5349, 2))),
        ZAMORAK_ROOM(new WorldArea(new WorldPoint(2875, 5347, 2), new WorldPoint(2879, 5335, 2), new WorldPoint(2891, 5335, 2), new WorldPoint(2900, 5324, 2), new WorldPoint(2904, 5306, 2), new WorldPoint(2913, 5304, 2), new WorldPoint(2913, 5298, 2), new WorldPoint(2909, 5281, 2), new WorldPoint(2878, 5278, 2), new WorldPoint(2867, 5278, 2), new WorldPoint(2861, 5276, 2), new WorldPoint(2846, 5291, 2), new WorldPoint(2846, 5293, 2), new WorldPoint(2844, 5296, 2), new WorldPoint(2844, 5312, 2), new WorldPoint(2851, 5330, 2), new WorldPoint(2851, 5337, 2), new WorldPoint(2865, 5349, 2), new WorldPoint(2874, 5349, 2))),
        ZAMORAK_ISLAND(new WorldArea(new WorldPoint(2927, 5332, 2), new WorldPoint(2922, 5332, 2), new WorldPoint(2922, 5333, 2), new WorldPoint(2913, 5333, 2), new WorldPoint(2905, 5343, 2), new WorldPoint(2891, 5349, 2), new WorldPoint(2888, 5342, 2), new WorldPoint(2882, 5342, 2), new WorldPoint(2881, 5368, 2), new WorldPoint(2921, 5370, 2), new WorldPoint(2943, 5355, 2), new WorldPoint(2935, 5335, 2))),
        BANDOS_ROOM(new WorldArea(new WorldPoint(2863, 5350, 2), new WorldPoint(2877, 5373, 2))),
        BANDOS_ISLAND(new WorldArea(new WorldPoint(2863, 5357, 2), new WorldPoint(2863, 5352, 2), new WorldPoint(2860, 5345, 2), new WorldPoint(2851, 5335, 2), new WorldPoint(2851, 5330, 2), new WorldPoint(2845, 5314, 2), new WorldPoint(2824, 5312, 2), new WorldPoint(2825, 5363, 2), new WorldPoint(2862, 5370, 2))),
        ARMADYL_ROOM(new WorldArea(new WorldPoint(2819, 5295, 2), new WorldPoint(2843, 5309, 2))),
        ARMADYL_ISLAND(new WorldArea(new WorldPoint(2837, 5295, 2), new WorldPoint(2844, 5295, 2), new WorldPoint(2859, 5277, 2), new WorldPoint(2868, 5275, 2), new WorldPoint(2870, 5274, 2), new WorldPoint(2870, 5270, 2), new WorldPoint(2875, 5270, 2), new WorldPoint(2877, 5271, 2), new WorldPoint(2879, 5269, 2), new WorldPoint(2879, 5255, 2), new WorldPoint(2833, 5256, 2), new WorldPoint(2824, 5295, 2))),
        SARADOMIN_ROOM(new WorldArea(new WorldPoint(2883, 5257, 0), new WorldPoint(2908, 5276, 0))),
        SARADOMIN_ISLAND_UP(new WorldArea(new WorldPoint(2913, 5275, 1), new WorldPoint(2930, 5305, 1))),
        SARADOMIN_ISLAND_LOW(new WorldArea(new WorldPoint(2909, 5257, 0), new WorldPoint(2930, 5276, 0)));

        private WorldArea area;
        Areas(WorldArea Area) {
            area = Area;
        }
        public boolean contains(WorldPoint point) {
            return area.containsPoint(point);
        }

        public WorldArea get() {
            return area;
        }

        public static ArrayList<WorldPoint> toPaint;
    }

    public static class Npcs {
        public enum Bandos {
            GRAARDOR(2215),
            STRONGSTACK(2216),
            STEELWILL(2217),
            GRIMSPIKE(2218);

            int id;

            Bandos(int ID) {
                id = ID;
            }
            public int getID() {
                return id;
            }
            public SimpleEntityQuery<SimpleNpc> getQuery() {
                return c.npcs.populate().filter(id);
            }
            public SimpleEntityQuery<SimpleNpc> getNearestQuery() {
                return getQuery().sort(Comparator.comparingDouble(npc -> c.pathing.collisionDistance(npc.getLocation())));
            }
        }
    }

    public enum Objects {
        BANDOS_DOOR(26503),
        BANDOS_ALTER(26423);

        int id;
        Objects(int ID) {id = ID;}

        public int getID() {
            return id;
        }

        public SimpleEntityQuery<SimpleObject> getQuery() {
            return c.objects.populate().filter(id);
        }

        public void sendNearestP(String interaction) {
            getQuery().nextNearest().menuAction(interaction);
        }

        public void sendP(SimpleNpc npc, String interaction) {
            npc.menuAction(interaction);
        }
    }

    public static class Anims {
        public enum Bandos {
            RANGED(Npcs.Bandos.GRIMSPIKE, 6155),
            MAGE(Npcs.Bandos.STEELWILL, 6155),
            MELEE(Npcs.Bandos.STRONGSTACK, 6154);

            Npcs.Bandos npc;
            int anim;

            Bandos(Npcs.Bandos npc, int anim) {
                this.npc = npc;
                this.anim = anim;
            }

            public boolean isAnimating() {
                return npc.getQuery().nextNearest().getAnimation() == anim;
            }
        }
    }

    public enum Projectiles {
        NULL(0);

        int id;
        Projectiles(int ProjectileID) { id = ProjectileID;}

        public int getID() {return id;}

        public SimpleIdQuery<SimpleProjectile> getProjectile() {
            return c.projectiles.populate().filter(id);
        }
    }

    public static class Strategies {
        public static class Six_Zero {
            public static WorldPoint[] getWorldPoints() {
                ClientContext c = ClientContext.instance();
                WorldPoint base = Objects.BANDOS_DOOR.getQuery().next().getLocation();
                WorldPoint start = new WorldPoint(base.getX()+9, base.getY()-2, base.getPlane());
                WorldPoint first = new WorldPoint(base.getX()+1, base.getY()-3, base.getPlane());
                WorldPoint second = new WorldPoint(base.getX()+1, base.getY()+6, base.getPlane());
                WorldPoint third = new WorldPoint(base.getX()+1, base.getY()+15, base.getPlane());
                WorldPoint forth = new WorldPoint(base.getX()+13, base.getY()+11, base.getPlane());
                WorldPoint fifth = new WorldPoint(base.getX()+13, base.getY()+3, base.getPlane());
                WorldPoint sixth = new WorldPoint(base.getX()+13, base.getY()-3, base.getPlane());

                return new WorldPoint[]{start, first,second,third,forth,fifth,sixth};
            }
        }
    }

}
