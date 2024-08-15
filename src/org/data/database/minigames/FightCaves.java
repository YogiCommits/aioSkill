package org.data.database.minigames;

import net.runelite.api.Projectile;
import net.runelite.api.coords.WorldPoint;
import simple.hooks.queries.SimpleEntityQuery;
import simple.hooks.queries.SimpleIdQuery;
import simple.hooks.wrappers.SimpleNpc;
import simple.hooks.wrappers.SimpleObject;
import simple.hooks.wrappers.SimpleProjectile;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

import java.util.ArrayList;

public class FightCaves {

    private static ClientContext c = ClientContext.instance();



    public enum Areas {
        LOBBY(new WorldArea(new WorldPoint(2432, 5161, 0), new WorldPoint(2456, 5183, 0)));

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

    public enum Npcs {
        TZHAAR_XIL(2171),
        TZHAAR_MEJ_JAL(2180);

        int id;
        Npcs(int ID) { id = ID; }
        public int getID() {
            return id;
        }
        public SimpleEntityQuery<SimpleNpc> getQuery() {
            return c.npcs.populate().filter(id);
        }

        public void sendNearestP(String interaction) {
            getQuery().nextNearest().menuAction(interaction);
        }

        public void sendP(SimpleNpc npc, String interaction) {
            npc.menuAction(interaction);
        }

        public enum Enemies {
            JAD(3127),
            HEALERS(3128),
            MAGE(3125),
            RANGE(3121),
            MELEE(3123);

            int id;
            Enemies(int ID) {id = ID;}

            public int getID() {
                return id;
            }

            public SimpleEntityQuery<SimpleNpc> getQuery() {
                return c.npcs.populate().filter(id);
            }

            public void sendNearestP(String interaction) {
                getQuery().nextNearest().menuAction(interaction);
            }

            public void sendP(SimpleNpc npc, String interaction) {
                npc.menuAction(interaction);
            }

            public WorldArea getIntArea() {
                SimpleNpc npc = getQuery().nextNearest();
                return new WorldArea(new WorldPoint(npc.getLocation().getX() - 2, npc.getLocation().getY() - 2, 0), new WorldPoint(npc.getLocation().getX() + (npc.getNpcDefinitions().getSize() + 2), npc.getLocation().getY() + (npc.getNpcDefinitions().getSize() + 2), 0));
            }
        }
    }

    public enum Objects {
        BANK_CHEST(30267),
        ENTRANCE(11833),
        EXIT(11834);

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
        public enum Jad {
            IDLE(2653),
            RANGE(2652),
            MAGE(2356);

            int anim;

            Jad(int ANIMATION_ID) {
                anim = ANIMATION_ID;
            }

            public int getAnim() {
                return anim;
            }

            public boolean matchCurrentAnim() {
                SimpleNpc npc = Npcs.Enemies.JAD.getQuery().next();
                if (npc == null) {
                    System.out.println("We cant find the jad to check...");
                }
                if (npc.getAnimation() == anim) {
                    return true;
                }
                return false;
            }
        }

        public enum Range {
            ATTACK(2633),
            FLINCH(2629);

            int id;

            Range(int AnimID) {
                id = AnimID;
            }

            public int getAnim() {
                return id;
            }

            public boolean matchCurrentAnim() {
                SimpleNpc npc = Npcs.Enemies.RANGE.getQuery().next();
                if (npc == null) {
                    System.out.println("We cant find the jad to check...");
                }
                if (npc.getAnimation() == id) {
                    return true;
                }
                return false;
            }
        }

        public enum Mage {
            ATTACK(2647),
            FLINCH(2645);

            int id;

            Mage(int AnimID) {
                id = AnimID;
            }

            public int getAnim() {
                return id;
            }

            public boolean matchCurrentAnim() {
                SimpleNpc npc = Npcs.Enemies.MAGE.getQuery().next();
                if (npc == null) {
                    System.out.println("We cant find the jad to check...");
                }
                if (npc.getAnimation() == id) {
                    return true;
                }
                return false;
            }
        }
    }

    public enum Projectiles {
        MAGE(445),
        RANGE(443);

        int id;
        Projectiles(int ProjectileID) { id = ProjectileID;}

        public int getID() {return id;}

        public SimpleIdQuery<SimpleProjectile> getProjectile() {
            return c.projectiles.populate().filter(id);
        }
    }

    public static WorldPoint getItalyRock() {
        try {
            WorldPoint home_base = FightCaves.Objects.EXIT.getQuery().next().getLocation();
            return new WorldPoint(home_base.getX() + 9, home_base.getY() - 18, 0);
        } catch (Exception e) {
            System.out.println("Unable to find Exit Door - Don't think we're in the instance yet!");
        }
        System.out.println("We couldn't find italy rock :(");
        return null;
    }

    public static WorldArea getSafeArea() {
        if (getItalyRock() == null) {return null;}
        return new WorldArea(new WorldPoint(getItalyRock().getX()-14, getItalyRock().getY() - 20, 0), new WorldPoint(getItalyRock().getX()+5, getItalyRock().getY()-9, 0));
    }

    public static WorldArea getDangerArea() {
        if (getItalyRock() == null) {return null;}
        return new WorldArea(new WorldPoint(getItalyRock().getX()-14, getItalyRock().getY() - 2, 0), new WorldPoint(getItalyRock().getX()-5, getItalyRock().getY()+6, 0));
    }

}
