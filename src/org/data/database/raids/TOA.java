package org.data.database.raids;

import net.runelite.api.Deque;
import net.runelite.api.GraphicsObject;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.plugins.raids.solver.Room;
import simple.hooks.queries.SimpleEntityQuery;
import simple.hooks.queries.SimpleIdQuery;
import simple.hooks.queries.SimpleItemQuery;
import simple.hooks.wrappers.*;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TOA {


    public enum Consumables {
        NECTAR("Drink", 27321, 27319, 27317, 27315),
        SILK_DRESSING("Apply", 27325, 27323),
        CRYSTAL_SCARAB("Crack", 27337, 27335),
        TEARS_OF_ELIDINIS("Drink", 27333, 27331, 27329, 27327),
        SMELLING_SALTS("Crush", 27345, 27343),
        ADRENALINE("Drink", 27341, 27339),
        HONEY_LOCUST("Eat", 27351),
        AMBROSIA("Drink", 27349, 27347);

        int[] ids;
        String action;
        Consumables(String action, int... ids) {
            this.action = action;
            this.ids = ids;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(ids);
        }

        public void consume() {
            getInvQuery().next().menuAction(action);
        }
    }

    private static ClientContext c = ClientContext.instance();

    public static class Lobby {
        public enum Objects {
            ENTRANCE(46089);

            int id;
            Objects(int ID) {id = ID;}

            public SimpleObject get() {
                return getQuery().nextNearest();
            }

            public SimpleEntityQuery<SimpleObject> getQuery() {
                return ClientContext.instance().objects.populate().filter(id);
            }
        }

        public enum Npcs {}

        public static WorldArea Area() {
            return new WorldArea(new WorldPoint(3350, 9111, 0), new WorldPoint(3375, 9131, 0));
        }

        public enum Widgets {
            PARTY_STATUS(773, 4),
            PARTY_LIST(772, 2),
            MYPARTY_WIDGET(774, 3),
            MYPARTY_BUTTON(772, 64);

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
    }

    public static class Akkha {
        public enum Objects {
            EXIT(46055),
            CRYSTAL(45866);

            int[] id;
            Objects(int... ID) {
                id = ID;
            }
            public int getID() {return id[0];}
            public int[] getIDs() {return id;}

            public SimpleObject get() {
                return getQuery().nextNearest();
            }

            public SimpleEntityQuery<SimpleObject> getQuery() {
                return ClientContext.instance().objects.populate().filter(id);
            }

            public enum Lobby {
                SEAL_PROTECT(11706),
                SEAL_OPEN(11707),
                STATUE_FIRE(45486),
                STATUE_TAKE(45485),
                BREAKABLE_WALL(45462, 45464),
                BROKEN_WALL(45466, 48460),
                BARRIER(45135),
                MIRROR(45455),
                MIRROR2(45456),
                EXIT(45128),
                PICKAXE_HOLDER(45468),
                SOLID_WALL(45458);

                int[] id;
                Lobby(int... ID) {
                    id = ID;
                }
                public int getID() {return id[0];}
                public int[] getIDs() {return id;}

                public SimpleObject get() {
                    return getQuery().nextNearest();
                }

                public SimpleEntityQuery<SimpleObject> getQuery() {
                    return ClientContext.instance().objects.populate().filter(id);
                }
            }
        }

        public enum Npcs {
            AKKHA(11789, 11790, 11791, 11792),
            SHADOW_ALIVE(11797),
            SHADOW(11798, 11799),
            ORB_LIGHT(11800),
            ORB_DARK(11801),
            ORB_BURN(11802),
            ORB_FROZEN(11803),
            ORB_UNSTABLE(11804);

            int[] id;
            Npcs(int... ID) {id = ID;}

            public int[] getIDs() {return id;}

            public SimpleNpc get() {
                return getQuery().nextNearest();
            }

            public SimpleEntityQuery<SimpleNpc> getQuery() {
                return ClientContext.instance().npcs.populate().filter(id);
            }

            public void attackP() { getQuery().nextNearest().menuAction("Attack"); }

            public enum Lobby {
                ORB(11708);

                int id;
                Lobby(int ID) {id = ID;}

                public int getID() {return id;}

                public SimpleNpc get() {
                    return getQuery().nextNearest();
                }

                public SimpleEntityQuery<SimpleNpc> getQuery() {
                    return ClientContext.instance().npcs.populate().filter(id);
                }
            }
        }

        public enum Projectile {
            A(-1);

            int id;
            Projectile(int ID) {id = ID;}
            public SimpleIdQuery<SimpleProjectile> getQuery() {
                return c.projectiles.populate().filter(id);
            }
            public SimpleProjectile get() {
                return getQuery().next();
            }
        }

        public static class Areas {
        }

        public static class Lobby {
            public enum Items {
                MIRROR(27296);

                int id;
                Items(int ID) {
                    id = ID;
                }
                public int getID() {return id;}

                public SimpleItem get() {
                    return getInvQuery().next();
                }

                public SimpleItemQuery<SimpleItem> getInvQuery() {
                    return ClientContext.instance().inventory.populate().filter(id);
                }

                public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                    return ClientContext.instance().groundItems.populate().filter(id);
                }
            }

            public enum Objects {
                EXIT(45128),
                PICKAXE(45468),
                CASTER(45486),
                SHIELD(45485),
                BARRIER(45135);

                int id;
                Objects(int ID) {
                    id = ID;
                }
                public int getID() {return id;}

                public SimpleObject get() {
                    return getQuery().nextNearest();
                }

                public SimpleEntityQuery<SimpleObject> getQuery() {
                    return ClientContext.instance().objects.populate().filter(id);
                }
            }

            public enum Npcs {
                A(-1);

                int id;
                Npcs(int ID) {
                    id = ID;
                }
                public int getID() {return id;}

                public SimpleNpc get() {
                    return getQuery().nextNearest();
                }

                public SimpleEntityQuery<SimpleNpc> getQuery() {
                    return ClientContext.instance().npcs.populate().filter(id);
                }
            }

            public enum Widgets {
                A(-1, -1);

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

            public static WorldPoint base() {return new WorldPoint(Objects.EXIT.get().getLocation().getX()-8, Objects.EXIT.get().getLocation().getY(), 0);}
        }
    }

    public static class Zebak {
        public enum Objects {
            EXIT(45543),
            CRYSTAL(45506),
            ACID_BLOBS(45571, 45572, 45573, 45574, 45575),
            BOULDER(43876);

            int[] id;
            Objects(int... ID) {
                id = ID;
            }
            public int getID() {return id[0];}
            public int[] getIDs() {return id;}

            public SimpleObject get() {
                return getQuery().nextNearest();
            }

            public SimpleEntityQuery<SimpleObject> getQuery() {
                return ClientContext.instance().objects.populate().filter(id);
            }

        }

        public enum Npcs {
            ZEBAK(11730),
            JUG(11735),
            WAVES(11738),
            ZEBAK_ATTACK(11744);

            int id;
            Npcs(int ID) {id = ID;}

            public int getID() {return id;}

            public SimpleNpc get() {
                return getQuery().nextNearest();
            }

            public SimpleEntityQuery<SimpleNpc> getQuery() {
                return ClientContext.instance().npcs.populate().filter(id);
            }

            public void attackP() { getQuery().nextNearest().menuAction("Attack"); }
        }

        public enum Projectile {
            RANGE(2178),
            MAGE(2176);

            int id;
            Projectile(int ID) {id = ID;}
            public SimpleIdQuery<SimpleProjectile> getQuery() {
                return c.projectiles.populate().filter(id);
            }
            public SimpleProjectile get() {
                return getQuery().next();
            }
        }

        public static class Areas {
            public static WorldArea Landing() {
                try {
                    WorldPoint base = Objects.CRYSTAL.get().getLocation();
                    WorldPoint bot = new WorldPoint(base.getX() - 2, base.getY() - 7, base.getPlane());
                    WorldPoint top = new WorldPoint(base.getX() + 8, base.getY() + 7, base.getPlane());
                    return new WorldArea(bot, top);
                } catch (Exception e) {
                    System.out.println("Unable to find Teleport Crystal..");
                    return null;
                }
            }

            public static WorldArea Combat() {
                try {
                    WorldPoint base = Objects.CRYSTAL.get().getLocation();
                    WorldPoint bot = new WorldPoint(base.getX()-30, base.getY()-11, base.getPlane());
                    WorldPoint top = new WorldPoint(base.getX()-11, base.getY()+11, base.getPlane());
                    return new WorldArea(bot, top);
                } catch (Exception e) {
                    System.out.println("Unable to find Teleport Crystal..");
                    return null;
                }
            }

            public static WorldArea WaveCheck() {
                try {
                    WorldPoint base = Objects.CRYSTAL.get().getLocation();
                    WorldPoint bot = new WorldPoint(base.getX()-10, base.getY()-12, base.getPlane());
                    WorldPoint top = new WorldPoint(base.getX()-13, base.getY()+12, base.getPlane());
                    return new WorldArea(bot, top);
                } catch (Exception e) {
                    System.out.println("Unable to find Teleport Crystal..");
                    return null;
                }
            }
        }

        public static class Lobby {
            public enum Items {
                WATER_JUG(27295);

                int id;
                Items(int ID) {
                    id = ID;
                }
                public int getID() {return id;}

                public SimpleItem get() {
                    return getInvQuery().next();
                }

                public SimpleItemQuery<SimpleItem> getInvQuery() {
                    return ClientContext.instance().inventory.populate().filter(id);
                }

                public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                    return ClientContext.instance().groundItems.populate().filter(id);
                }
            }

            public enum Objects {
                EXIT(45453),
                BARRIER(45135),
                WATERFALL(45398),
                ENTRY(45397),
                ACID_ORB(45403);

                int id;
                Objects(int ID) {
                    id = ID;
                }
                public int getID() {return id;}

                public SimpleObject get() {
                    return getQuery().nextNearest();
                }

                public SimpleEntityQuery<SimpleObject> getQuery() {
                    return ClientContext.instance().objects.populate().filter(id);
                }
            }

            public enum Npcs {
                CROCODILE(11705),
                PLANT(11700, 11702);

                int[] id;
                Npcs(int... ID) {
                    id = ID;
                }
                public int[] getID() {return id;}

                public SimpleNpc get() {
                    return getQuery().nextNearest();
                }

                public SimpleEntityQuery<SimpleNpc> getQuery() {
                    return ClientContext.instance().npcs.populate().filter(id);
                }
            }

            public enum Widgets {
                MAIN(303, 5),
                PROGRESS(303, 20);

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
        }



    }

    public static class BaBa {
        public enum Objects {
            EXIT(45128),
            BARRIER(45135),
            VENT(45499),
            PILLER(45494),
            STATUE(45496),
            POTION_BOX(45498),
            HAMMER_BOX(45497);

            int id;
            Objects(int ID) {
                id = ID;
            }
            public int getID() {return id;}

            public SimpleObject get() {
                return getQuery().nextNearest();
            }

            public SimpleEntityQuery<SimpleObject> getQuery() {
                return ClientContext.instance().objects.populate().filter(id);
            }
        }

        public enum Items {
            NEUTRAL_POTION(27297);

            int id;
            Items(int ID) {
                id = ID;
            }
            public int getID() {return id;}

            public SimpleItem get() {
                return getQuery().next();
            }

            public SimpleItemQuery<SimpleItem> getQuery() {
                return ClientContext.instance().inventory.populate().filter(id);
            }
        }

        public enum Npcs {
            BRAWLER(11709),
            THROWER(11710, 11713),
            MAGE(11714, 11711),
            SHAMAN(11715),
            VOLATILE(11716),
            CURSED(11717),
            THRALL(11718);

            int[] id;
            Npcs(int... ID) {
                id = ID;
            }
            public int[] getIDs() {return id;}

            public SimpleEntityQuery<SimpleNpc> getQuery() {
                return ClientContext.instance().npcs.populate().filter(id).filter((n) -> n.getAnimation() != 9755);
            }

            public SimpleNpc get() {
                return getQuery().nextNearest();
            }
        }

        public enum GraphicsObjects {
            SKULL(2134);

            int id;
            GraphicsObjects(int id) {
                this.id = id;
            }

            public GraphicsObject get() {
                for (GraphicsObject go : c.getClient().getGraphicsObjects()) {
                    if (go == null) { continue; }
                    if (go.getId() == id) {
                        return go;
                    }
                }
                return null;
            }

            public GraphicsObject[] getArray() {
                ArrayList<GraphicsObject> list = new ArrayList<>();
                for (GraphicsObject go : c.getClient().getGraphicsObjects()) {
                    if (go == null) { continue; }
                    if (go.getId() == id) { list.add(go); }
                }
                return list.toArray(new GraphicsObject[list.size()]);
            }
        }

        public enum Areas {
            LOBBY_PRE(new WorldArea(new WorldPoint(Points.LANCHOR().getX(), Points.LANCHOR().getY()-7, Points.LANCHOR().getPlane()),new WorldPoint(Points.LANCHOR().getX()+6, Points.LANCHOR().getY()+7, Points.LANCHOR().getPlane())));

            WorldArea area;
            Areas(WorldArea area) {
                this.area = area;
            }

            public WorldArea get() {
                return area;
            }
            public boolean contains(WorldPoint wp) {
                return area.containsPoint(wp);
            }
        }

        public static class Points {
            public static WorldPoint LANCHOR() {
                return Objects.EXIT.get().getLocation();
            }

            public static WorldPoint VENT() {
                return new WorldPoint(LANCHOR().getX()+9, LANCHOR().getY()+4, LANCHOR().getPlane());
            }

            public static WorldPoint PILLAR() {
                return new WorldPoint(LANCHOR().getX() + 13, LANCHOR().getY()+8, LANCHOR().getPlane());
            }

            public static WorldPoint BARRIER() {
                return new WorldPoint(LANCHOR().getX()+5, LANCHOR().getY(), LANCHOR().getPlane());
            }
        }
    }

    public static class Kephri {

        public enum Objects {
            EXIT(45128),
            BARRIER(45135),
            AVOID(45338),
            PASSAGE(45343),
            CRYSTAL(45505),
            PLATFORM(45396),
            ENTRY(45337),
            DUNG(45504);

            int id;
            Objects(int ID) {
                id = ID;
            }
            public int getID() {return id;}

            public SimpleObject get() {
                return getQuery().nextNearest();
            }

            public SimpleEntityQuery<SimpleObject> getQuery() {
                return ClientContext.instance().objects.populate().filter(id);
            }

            public enum Puzzle1 {
                TABLET(45339, -1),
                NINE(45353, 45395),
                EIGHT(45352, 45394),
                SEVEN(45351, 45386),
                SIX(45350, 45393),
                FIVE(45349, 45392),
                FOUR(45348, 45391),
                THREE(45347, 45390),
                TWO(45346, 45389),
                ONE(45345, 45388);

                int[] ids;
                ClientContext c = ClientContext.instance();
                Puzzle1(int... ids) {
                    this.ids = ids;
                }

                public SimpleObject get() {
                    return c.objects.populate().filter(ids).nextNearest();
                }

                public SimpleObject getTile() {
                    return c.objects.populate().filter(ids[0]).nextNearest();
                }

                public SimpleObject getObj() {
                    return c.objects.populate().filter(ids[1]).nextNearest();
                }

                public boolean isEnabled() {
                    WorldPoint check = getTile().getLocation();
                    return c.objects.populate().filter(ids[1]).filter(check).isEmpty();
                }
            }

            public enum Puzzle2 {
                UNLIT(45344),
                CHECK(45384);

                int id;
                Puzzle2(int ID) {
                    id = ID;
                }
                public int getID() {return id;}

                public SimpleObject get() {
                    return getQuery().nextNearest();
                }

                public SimpleEntityQuery<SimpleObject> getQuery() {
                    return ClientContext.instance().objects.populate().filter(id);
                }

            }

            public enum Puzzle5 {
                RAW_TILE(45363),
                BIRD(45371),
                HOOK(45367),
                ONE(45365),
                KNIVES(45366),
                SQUARE(45368),
                STAR(45370),
                BOOT(45373),
                W(45372),
                HAND(45369);

                int ids;
                ClientContext c = ClientContext.instance();
                Puzzle5(int ids) {
                    this.ids = ids;
                }

                public int getId() {
                    return ids;
                }

                public SimpleObject get() {
                    return c.objects.populate().filter(ids).nextNearest();
                }

                public SimpleEntityQuery<SimpleObject> getQuery() {
                    return c.objects.populate().filter(ids);
                }
            }

            public static int[] getAllSymbolIds() {
                return new int[] {Puzzle5.BIRD.getId(), Puzzle5.HOOK.getId(), Puzzle5.ONE.getId(), Puzzle5.KNIVES.getId(), Puzzle5.SQUARE.getId(), Puzzle5.STAR.getId(), Puzzle5.BOOT.getId(), Puzzle5.W.getId(), Puzzle5.HAND.getId()};
            }
        }

        public static class Areas {

            public static WorldArea getPuzzleStart() {
                WorldPoint exit = Objects.EXIT.get().getLocation();
                return new WorldArea(new WorldPoint(exit.getX(), exit.getY()-13, exit.getPlane()), new WorldPoint(exit.getX()+11, exit.getY()+13, exit.getPlane()));
            }

            public static WorldArea getRoom1() {
                WorldPoint exit = Objects.EXIT.get().getLocation();
                return new WorldArea(new WorldPoint(exit.getX()+11, exit.getY()+1, exit.getPlane()), new WorldPoint(exit.getX()+28, exit.getY() + 11, exit.getPlane()));
            }
            public static WorldArea getRoom2() {
                WorldPoint exit = Objects.EXIT.get().getLocation();
                return new WorldArea(new WorldPoint(exit.getX()+11, exit.getY()-1, exit.getPlane()), new WorldPoint(exit.getX()+28, exit.getY() - 11, exit.getPlane()));
            }
            public static WorldArea getRoom3() {
                WorldPoint exit = Objects.EXIT.get().getLocation();
                return new WorldArea(new WorldPoint(exit.getX()+28, exit.getY()+1, exit.getPlane()), new WorldPoint(exit.getX()+43, exit.getY()+11, exit.getPlane()));
            }

            public static WorldArea getRoom4() {
                WorldPoint exit = Objects.EXIT.get().getLocation();
                return new WorldArea(new WorldPoint(exit.getX()+28, exit.getY()+1, exit.getPlane()), new WorldPoint(exit.getX()+43, exit.getY()-11, exit.getPlane()));
            }
            public static WorldArea getRoom5() {
                WorldPoint entry = Objects.ENTRY.get().getLocation();
                return new WorldArea(new WorldPoint(entry.getX()-18, entry.getY()-11, entry.getPlane()), new WorldPoint(entry.getX(), entry.getY()+12, entry.getPlane()));
            }

            public static WorldArea getLanding() {
                WorldPoint anchor = Objects.CRYSTAL.get().getLocation();
                return new WorldArea(new WorldPoint(anchor.getX()-5, anchor.getY()-5, anchor.getPlane()), new WorldPoint(anchor.getX()+2, anchor.getY()+4, anchor.getPlane()));
            }

            public static WorldArea getFightArea() {
                WorldPoint anchor = Objects.CRYSTAL.get().getLocation();
                return new WorldArea(new WorldPoint(anchor.getX()+4, anchor.getY()-9, anchor.getPlane()), new WorldPoint(anchor.getX()+22, anchor.getY()+9, anchor.getPlane()));
            }
        }

        public static class Points {

            public static WorldPoint getDoor1() {
                WorldPoint exit = Objects.EXIT.get().getLocation();
                return new WorldPoint(exit.getX()+11, exit.getY()+6, exit.getPlane());
            }

            public static WorldPoint getDoor2() {
                WorldPoint exit = Objects.EXIT.get().getLocation();
                return new WorldPoint(exit.getX()+11, exit.getY()-6, exit.getPlane());
            }

            public static WorldPoint getShitTile() {
                WorldPoint anchor = Npcs.KEPHRI.getQuery().next().getLocation();
                return new WorldPoint(anchor.getX()-1, anchor.getY()+4, anchor.getPlane());
            }

            public static WorldPoint getAtkTile1() {
                WorldPoint anchor = Npcs.KEPHRI.getQuery().next().getLocation();
                return new WorldPoint(anchor.getX()-1, anchor.getY()+3, anchor.getPlane());
            }

            public static WorldPoint getAtkTile2() {
                WorldPoint anchor = Npcs.KEPHRI.getQuery().next().getLocation();
                return new WorldPoint(anchor.getX()-1, anchor.getY()+1, anchor.getPlane());
            }

        }

        public static class Puzzles {

            public static WorldPoint[] TabletPuzzle(WorldPoint Anchor) {
                WorldPoint exit = Anchor;
                return new WorldPoint[] {
                        new WorldPoint(exit.getX()+8, exit.getY()+7, exit.getPlane()),
                        new WorldPoint(exit.getX()+8, exit.getY()+6, exit.getPlane()),
                        new WorldPoint(exit.getX()+8, exit.getY()+5, exit.getPlane()),
                        new WorldPoint(exit.getX()+8, exit.getY()+4, exit.getPlane()),
                        new WorldPoint(exit.getX()+8, exit.getY()+3, exit.getPlane()),

                        new WorldPoint(exit.getX()+9, exit.getY()+7, exit.getPlane()),
                        new WorldPoint(exit.getX()+9, exit.getY()+6, exit.getPlane()),
                        new WorldPoint(exit.getX()+9, exit.getY()+5, exit.getPlane()),
                        new WorldPoint(exit.getX()+9, exit.getY()+4, exit.getPlane()),
                        new WorldPoint(exit.getX()+9, exit.getY()+3, exit.getPlane()),

                        new WorldPoint(exit.getX()+10, exit.getY()+7, exit.getPlane()),
                        new WorldPoint(exit.getX()+10, exit.getY()+6, exit.getPlane()),
                        new WorldPoint(exit.getX()+10, exit.getY()+5, exit.getPlane()),
                        new WorldPoint(exit.getX()+10, exit.getY()+4, exit.getPlane()),
                        new WorldPoint(exit.getX()+10, exit.getY()+3, exit.getPlane()),

                        new WorldPoint(exit.getX()+11, exit.getY()+7, exit.getPlane()),
                        new WorldPoint(exit.getX()+11, exit.getY()+6, exit.getPlane()),
                        new WorldPoint(exit.getX()+11, exit.getY()+5, exit.getPlane()),
                        new WorldPoint(exit.getX()+11, exit.getY()+4, exit.getPlane()),
                        new WorldPoint(exit.getX()+11, exit.getY()+3, exit.getPlane()),

                        new WorldPoint(exit.getX()+12, exit.getY()+7, exit.getPlane()),
                        new WorldPoint(exit.getX()+12, exit.getY()+6, exit.getPlane()),
                        new WorldPoint(exit.getX()+12, exit.getY()+5, exit.getPlane()),
                        new WorldPoint(exit.getX()+12, exit.getY()+4, exit.getPlane()),
                        new WorldPoint(exit.getX()+12, exit.getY()+3, exit.getPlane()),
                };
            }

            public static HashMap<Integer, WorldPoint[]> TabletMap(WorldPoint anchor) {
                HashMap<Integer, WorldPoint[]> tabMap = new HashMap<>();
                /*20*/tabMap.put(20, new WorldPoint[]{ TabletPuzzle(anchor)[1], TabletPuzzle(anchor)[7], TabletPuzzle(anchor)[13] });
                /*21*/tabMap.put(21, new WorldPoint[]{ TabletPuzzle(anchor)[3], TabletPuzzle(anchor)[8], TabletPuzzle(anchor)[13], TabletPuzzle(anchor)[18], TabletPuzzle(anchor)[23] });
                /*22*/tabMap.put(22, new WorldPoint[]{ TabletPuzzle(anchor)[4], TabletPuzzle(anchor)[9], TabletPuzzle(anchor)[14], TabletPuzzle(anchor)[19], TabletPuzzle(anchor)[24] });
                /*23*/tabMap.put(23, new WorldPoint[]{ TabletPuzzle(anchor)[0], TabletPuzzle(anchor)[6], TabletPuzzle(anchor)[12], TabletPuzzle(anchor)[18], TabletPuzzle(anchor)[24] });
                /*24*/tabMap.put(24, new WorldPoint[]{ TabletPuzzle(anchor)[1], TabletPuzzle(anchor)[7], TabletPuzzle(anchor)[13], TabletPuzzle(anchor)[19] });
                /*25*/tabMap.put(25, new WorldPoint[]{ TabletPuzzle(anchor)[2], TabletPuzzle(anchor)[7], TabletPuzzle(anchor)[12], TabletPuzzle(anchor)[17] });
                /*26*/tabMap.put(26, new WorldPoint[]{ TabletPuzzle(anchor)[4], TabletPuzzle(anchor)[9], TabletPuzzle(anchor)[14], TabletPuzzle(anchor)[19], TabletPuzzle(anchor)[24], TabletPuzzle(anchor)[23] });
                /*27*/tabMap.put(27, new WorldPoint[]{ TabletPuzzle(anchor)[2], TabletPuzzle(anchor)[7], TabletPuzzle(anchor)[12], TabletPuzzle(anchor)[17], TabletPuzzle(anchor)[18] });
                /*28*/tabMap.put(28, new WorldPoint[]{ TabletPuzzle(anchor)[1], TabletPuzzle(anchor)[6], TabletPuzzle(anchor)[11], TabletPuzzle(anchor)[16], TabletPuzzle(anchor)[21], TabletPuzzle(anchor)[20] });
                /*29*/tabMap.put(29, new WorldPoint[]{ TabletPuzzle(anchor)[2], TabletPuzzle(anchor)[7], TabletPuzzle(anchor)[12], TabletPuzzle(anchor)[17], TabletPuzzle(anchor)[23] });
                /*30*/tabMap.put(30, new WorldPoint[]{ TabletPuzzle(anchor)[2], TabletPuzzle(anchor)[7], TabletPuzzle(anchor)[12], TabletPuzzle(anchor)[17], TabletPuzzle(anchor)[22] });
                /*31*/tabMap.put(31, new WorldPoint[]{ TabletPuzzle(anchor)[0], TabletPuzzle(anchor)[1], TabletPuzzle(anchor)[2], TabletPuzzle(anchor)[3], TabletPuzzle(anchor)[4] });
                /*32*/tabMap.put(32, new WorldPoint[]{ TabletPuzzle(anchor)[4], TabletPuzzle(anchor)[9], TabletPuzzle(anchor)[14], TabletPuzzle(anchor)[19], TabletPuzzle(anchor)[24], TabletPuzzle(anchor)[23], TabletPuzzle(anchor)[22], TabletPuzzle(anchor)[21] });
                /*33*/tabMap.put(33, new WorldPoint[]{ TabletPuzzle(anchor)[0], TabletPuzzle(anchor)[1], TabletPuzzle(anchor)[2], TabletPuzzle(anchor)[3], TabletPuzzle(anchor)[4], TabletPuzzle(anchor)[8] });
                /*34*/tabMap.put(34, new WorldPoint[]{ TabletPuzzle(anchor)[2], TabletPuzzle(anchor)[7], TabletPuzzle(anchor)[12], TabletPuzzle(anchor)[17], TabletPuzzle(anchor)[22], TabletPuzzle(anchor)[23] });
                /*35*/tabMap.put(35, new WorldPoint[]{ TabletPuzzle(anchor)[0], TabletPuzzle(anchor)[5], TabletPuzzle(anchor)[6], TabletPuzzle(anchor)[7], TabletPuzzle(anchor)[8], TabletPuzzle(anchor)[9] });
                /*36*/tabMap.put(36, new WorldPoint[]{ TabletPuzzle(anchor)[0], TabletPuzzle(anchor)[5], TabletPuzzle(anchor)[10], TabletPuzzle(anchor)[15], TabletPuzzle(anchor)[20], TabletPuzzle(anchor)[21], TabletPuzzle(anchor)[22] });
                /*37*/tabMap.put(37, new WorldPoint[]{ TabletPuzzle(anchor)[2], TabletPuzzle(anchor)[7], TabletPuzzle(anchor)[12], TabletPuzzle(anchor)[17], TabletPuzzle(anchor)[22], TabletPuzzle(anchor)[23], TabletPuzzle(anchor)[24] });
                /*38*/tabMap.put(38, new WorldPoint[]{ TabletPuzzle(anchor)[2], TabletPuzzle(anchor)[7], TabletPuzzle(anchor)[12], TabletPuzzle(anchor)[17], TabletPuzzle(anchor)[22], TabletPuzzle(anchor)[16], TabletPuzzle(anchor)[10] });
                /*39*/tabMap.put(39, new WorldPoint[]{ TabletPuzzle(anchor)[0], TabletPuzzle(anchor)[5], TabletPuzzle(anchor)[10], TabletPuzzle(anchor)[15], TabletPuzzle(anchor)[20], TabletPuzzle(anchor)[21], TabletPuzzle(anchor)[17] });
                /*40*/tabMap.put(40, new WorldPoint[]{ TabletPuzzle(anchor)[0], TabletPuzzle(anchor)[5], TabletPuzzle(anchor)[6], TabletPuzzle(anchor)[1], TabletPuzzle(anchor)[2], TabletPuzzle(anchor)[3], TabletPuzzle(anchor)[4] });
                /*41*/tabMap.put(41, new WorldPoint[]{ TabletPuzzle(anchor)[4], TabletPuzzle(anchor)[3], TabletPuzzle(anchor)[2], TabletPuzzle(anchor)[1], TabletPuzzle(anchor)[0], TabletPuzzle(anchor)[5], TabletPuzzle(anchor)[10] });
                /*42*/tabMap.put(42, new WorldPoint[]{ TabletPuzzle(anchor)[0], TabletPuzzle(anchor)[6], TabletPuzzle(anchor)[12], TabletPuzzle(anchor)[18], TabletPuzzle(anchor)[24], TabletPuzzle(anchor)[19], TabletPuzzle(anchor)[14], TabletPuzzle(anchor)[9], TabletPuzzle(anchor)[4] });
                /*43*/tabMap.put(43, new WorldPoint[]{ TabletPuzzle(anchor)[4], TabletPuzzle(anchor)[3], TabletPuzzle(anchor)[2], TabletPuzzle(anchor)[1], TabletPuzzle(anchor)[0], TabletPuzzle(anchor)[5], TabletPuzzle(anchor)[10], TabletPuzzle(anchor)[15] });
                /*44*/tabMap.put(44, new WorldPoint[]{ TabletPuzzle(anchor)[9], TabletPuzzle(anchor)[8], TabletPuzzle(anchor)[7], TabletPuzzle(anchor)[6], TabletPuzzle(anchor)[5], TabletPuzzle(anchor)[10], TabletPuzzle(anchor)[15], TabletPuzzle(anchor)[20]});
                /*45*/tabMap.put(45, new WorldPoint[]{ TabletPuzzle(anchor)[0], TabletPuzzle(anchor)[1], TabletPuzzle(anchor)[2], TabletPuzzle(anchor)[3], TabletPuzzle(anchor)[8], TabletPuzzle(anchor)[13], TabletPuzzle(anchor)[18], TabletPuzzle(anchor)[23] });

                return tabMap;
            }
        }

        public enum GraphicsObjects {
            FIREBALL(1447);

            int id;
            GraphicsObjects(int ids) {
                this.id = ids;
            }

            public List<GraphicsObject> getGraphicsObjects() {
                Deque<GraphicsObject> go = c.getClient().getGraphicsObjects();
                List<GraphicsObject> list = new ArrayList<>();
                for (GraphicsObject g : go) {
                    try {
                        if (g.getId() == id) {
                            list.add(g);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}
                }
                return list;
            }

            public boolean affectsPlayer() {
                boolean b =  false;

                for (GraphicsObject go : getGraphicsObjects()) {

                    if (c.players.getLocal().getLocation().equals(WorldPoint.fromLocal(c.getClient(), go.getLocation()))) {
                        b = true;
                    }
                }

                return b;
            }

            public boolean affectsWorldPoint(WorldPoint wp) {
                for (GraphicsObject go : getGraphicsObjects()) {
                    if (go == null) { continue; }
                    if (go.getLocation().equals(wp)) {
                        return true;
                    }
                }
                return false;
            }

            public int getId() {
                return id;
            }
        }

        public enum Graphics {
            SHIT_WARNING(2146),
            SHIT_POST(245);

            int id;

            Graphics(int id) {
                this.id = id;
            }

            public boolean playerHeld() {
                return c.players.getLocal().getGraphic() == id;
            }

            public boolean npcHeld(SimpleNpc npc) {
                return npc.getGraphic() == id;
            }

            public int getID() {
                return id;
            }
        }

        public enum Npcs {
            OBELISK_OFF(11698),
            OBELISK_ON(11699),
            KEPHRI(11719, 11720, 11721),
            KEPHRI_DEAD(11722),
            SPIRIT(11689),
            RANGE(11725),
            MAGE(11726),
            MELEE(11724);

            int[] id;
            Npcs(int... ID) {id = ID;}

            public int[] getIDs() {return id;}

            public SimpleNpc get() {
                return getQuery().nextNearest();
            }

            public SimpleEntityQuery<SimpleNpc> getQuery() {
                return ClientContext.instance().npcs.populate().filter(id);
            }

            public void attackP() { getQuery().nextNearest().menuAction("Attack"); }
        }
    }

    public static class Overworld {

        public enum Objects {

            PATH_HET(46164),
            PATH_CRONDIS(46161),
            PATH_SCARABAS(46155, 46157),
            PATH_APMEKEN(46158),

            EXIT(45128);

            int[] id;
            Objects(int... ID) {
                id = ID;
            }
            public int[] getIDs() {return id;}

            public SimpleObject get() {
                return getQuery().nextNearest();
            }

            public SimpleEntityQuery<SimpleObject> getQuery() {
                return ClientContext.instance().objects.populate().filter(id);
            }

            public SimpleObject getOpen() { return getQuery().filter(id[0]).next(); }
            public SimpleObject getClosed() { return getQuery().filter(id[1]).next(); }
        }

        public static boolean inArea() {
            return Objects.PATH_APMEKEN.get() != null || Objects.PATH_HET.get() != null || Objects.PATH_CRONDIS.get() != null || Objects.PATH_SCARABAS.get() != null;
        }

    }

    public static class Tumeken {
        public enum Objects {
            EXIT(45128);

            int id;
            Objects(int ID) {
                id = ID;
            }
            public int getID() {return id;}

            public SimpleObject get() {
                return getQuery().nextNearest();
            }

            public SimpleEntityQuery<SimpleObject> getQuery() {
                return ClientContext.instance().objects.populate().filter(id);
            }
        }
    }

    public static class Warden {
        public enum Objects {
            EXIT(45128);

            int id;
            Objects(int ID) {
                id = ID;
            }
            public int getID() {return id;}

            public SimpleObject get() {
                return getQuery().nextNearest();
            }

            public SimpleEntityQuery<SimpleObject> getQuery() {
                return ClientContext.instance().objects.populate().filter(id);
            }
        }
    }


    public enum Widgets {
        RAID_LEVEL(481, 42),
        SOMETHING(481, 46);

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

}
