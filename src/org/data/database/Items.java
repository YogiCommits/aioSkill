package org.data.database;

import simple.hooks.queries.SimpleEntityQuery;
import simple.hooks.queries.SimpleItemQuery;
import simple.hooks.wrappers.SimpleGroundItem;
import simple.hooks.wrappers.SimpleItem;
import simple.robot.api.ClientContext;

public class Items {
    static ClientContext c = ClientContext.instance();

    public enum General {
        SPADE(952),
        BUCKET(0),
        KNIFE(946),
        DWARVEN_ROCK_CAKE(7510),
        HAMMER(2347);

        private final int id;

        General(int ID) {
            this.id = ID;
        }

        public int getValue() {
            return id;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(id);
        }
        public void clickP(String interaction) {
            if (getInvQuery().isEmpty()) {return;}
            getInvQuery().next().menuAction(interaction);
        }
        public void click(String interaction) {
            if (getInvQuery().isEmpty()) {return;}
            getInvQuery().next().menuAction(interaction);
        }
        public void click() {
            if (getInvQuery().isEmpty()) {return;}
            getInvQuery().next().click(0);
        }
    }

    public enum Currency {
        COINS(995),
        PLATINUM_TOKENS(13204),
        TOKKUL(6529),
        TRADING_STICKS(6306),
        CASTLE_WARS_TICKETS(4067),
        ECTO_TOKENS(4278),
        CASTLE_WARS_GOLD_TICKET(4068),
        CASTLE_WARS_SILVER_TICKET(4069),
        CASTLE_WARS_BRONZE_TICKET(4070),
        AGILITY_ARENA_TICKETS(2996),
        CRYSTAL_SHARDS(23969),
        MARKS_OF_GRACE(11849);

        private final int[] ids;

        Currency(int... ids) {
            this.ids = ids;
        }

        public int[] getIDs() {
            return ids;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(getIDs());
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return c.groundItems.populate().filter(getIDs());
        }

        public void click(String interaction) {
            if (getInvQuery().isEmpty()) { return; }
            getInvQuery().next().menuAction(interaction);
        }

        public void click() {
            if (getInvQuery().isEmpty()) { return; }
            getInvQuery().next().click(0);
        }
    }

    public enum Bones {
        BONES(526),
        BIG_BONES(532),
        BABYDRAGON_BONES(534),
        DRAGON_BONES(536),
        WYVERN_BONES(6812),
        FAYRG_BONES(4830),
        RAURG_BONES(4832),
        DAGANNOTH_BONES(6729),
        OURG_BONES(4834),
        SUPERIOR_DRAGON_BONES(22124),
        LAVA_DRAGON_BONES(11943),
        HYDRA_BONES(22786),
        WYRM_BONES(22780),
        DRAKE_BONES(22783),
        ZOGRE_BONES(4812),
        JOGRE_BONES(3125),
        MONKEY_BONES(3183),
        BAT_BONES(530),
        WOLF_BONES(2859),
        BURNT_BONES(528),
        LONG_BONES(10976),
        CURVED_BONES(10977);

        private final int id;

        Bones(int id) {
            this.id = id;
        }

        public int getID() {
            return id;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(id);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return c.groundItems.populate().filter(id);
        }

        public void click(String interaction) {
            if (getInvQuery().isEmpty()) { return; }
            getInvQuery().next().menuAction(interaction);
        }

        public void click() {
            if (getInvQuery().isEmpty()) { return; }
            getInvQuery().next().click(0);
        }

    }

    public enum Cannon {
        BASE(6),
        STAND(8),
        BARREL(10),
        FURNACE(12);

        int id;
        Cannon(int ID) {id = ID;}
        public int getID() {return id;}
        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(id);
        }
        public void clickP(String interaction) {
            if (getInvQuery().isEmpty()) {return;}
            getInvQuery().next().menuAction(interaction);
        }
        public void click(String interaction) {
            if (getInvQuery().isEmpty()) {return;}
            getInvQuery().next().menuAction(interaction);
        }
        public void click() {
            if (getInvQuery().isEmpty()) {return;}
            getInvQuery().next().click(0);
        }
    }

    public static class Skills {

        public static class Crafting {
            public enum Gems {
                UNCUT_OPAL(1625),
                OPAL(1609),
                UNCUT_JADE(1627),
                JADE(1611),
                UNCUT_RED_TOPAZ(1629),
                RED_TOPAZ(1613),
                UNCUT_SAPPHIRE(1623),
                SAPPHIRE(1607),
                UNCUT_EMERALD(1621),
                EMERALD(1605),
                UNCUT_RUBY(1619),
                RUBY(1603),
                UNCUT_DIAMOND(1617),
                DIAMOND(1601),
                UNCUT_DRAGONSTONE(1631),
                DRAGONSTONE(1615),
                UNCUT_ONYX(6571),
                ONYX(6573),
                UNCUT_ZENYTE(19496),
                ZENYTE(19493);

                private final int id;

                Gems(int id) {
                    this.id = id;
                }

                public int getID() {
                    return id;
                }

                public SimpleItemQuery<SimpleItem> getInvQuery() {
                    return c.inventory.populate().filter(id);
                }

                public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                    return c.groundItems.populate().filter(id);
                }

                public void click(String interaction) {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().menuAction(interaction);
                }

                public void click() {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().click(0);
                }
            }

            public enum GlassBlowing {
                BEER_GLASS(1919),
                CANDLE_LANTERN(4527),
                OIL_LAMP(4522),
                VIAL(229),
                FISHBOWL(6667),
                UNPOWERED_ORB(567),
                LANTERN_LENS(4542),
                LIGHT_ORB(10973),
                EMPTY_CANDLE_LANTERN(4527),
                EMPTY_OIL_LANTERN(4522),
                EMPTY_BULLSEYE_LANTERN(4544);

                private final int id;

                GlassBlowing(int id) {
                    this.id = id;
                }

                public int getID() {
                    return id;
                }

                public SimpleItemQuery<SimpleItem> getInvQuery() {
                    return c.inventory.populate().filter(id);
                }

                public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                    return c.groundItems.populate().filter(id);
                }

                public void click(String interaction) {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().menuAction(interaction);
                }

                public void click() {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().click(0);
                }
            }

            public static class Jewellery {

                public enum Ring {
                    GOLD_RING(1635),
                    SAPPHIRE_RING(1637),
                    EMERALD_RING(1639),
                    RUBY_RING(1641),
                    DIAMOND_RING(1643),
                    DRAGONSTONE_RING(1645),
                    ONYX_RING(6575),
                    ZENYTE_RING(19484);

                    private final int id;

                    Ring(int id) {
                        this.id = id;
                    }

                    public int getID() {
                        return id;
                    }

                    public SimpleItemQuery<SimpleItem> getInvQuery() {
                        return c.inventory.populate().filter(id);
                    }

                    public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                        return c.groundItems.populate().filter(id);
                    }

                    public void click(String interaction) {
                        if (getInvQuery().isEmpty()) { return; }
                        getInvQuery().next().menuAction(interaction);
                    }

                    public void click() {
                        if (getInvQuery().isEmpty()) { return; }
                        getInvQuery().next().click(0);
                    }
                }

                public enum Amulet {
                    GOLD_AMULET(1692),
                    SAPPHIRE_AMULET(1694),
                    EMERALD_AMULET(1696),
                    RUBY_AMULET(1698),
                    DIAMOND_AMULET(1700),
                    DRAGONSTONE_AMULET(1702),
                    ONYX_AMULET(6581),
                    ZENYTE_AMULET(19501);

                    private final int id;

                    Amulet(int id) {
                        this.id = id;
                    }

                    public int getID() {
                        return id;
                    }

                    public SimpleItemQuery<SimpleItem> getInvQuery() {
                        return c.inventory.populate().filter(id);
                    }

                    public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                        return c.groundItems.populate().filter(id);
                    }

                    public void click(String interaction) {
                        if (getInvQuery().isEmpty()) { return; }
                        getInvQuery().next().menuAction(interaction);
                    }

                    public void click() {
                        if (getInvQuery().isEmpty()) { return; }
                        getInvQuery().next().click(0);
                    }
                }

                public enum Necklace {
                    GOLD_NECKLACE(1654),
                    SAPPHIRE_NECKLACE(1656),
                    EMERALD_NECKLACE(1658),
                    RUBY_NECKLACE(1660),
                    DIAMOND_NECKLACE(1662),
                    DRAGONSTONE_NECKLACE(1664),
                    ONYX_NECKLACE(6577),
                    ZENYTE_NECKLACE(19535);

                    private final int id;

                    Necklace(int id) {
                        this.id = id;
                    }

                    public int getID() {
                        return id;
                    }

                    public SimpleItemQuery<SimpleItem> getInvQuery() {
                        return c.inventory.populate().filter(id);
                    }

                    public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                        return c.groundItems.populate().filter(id);
                    }

                    public void click(String interaction) {
                        if (getInvQuery().isEmpty()) { return; }
                        getInvQuery().next().menuAction(interaction);
                    }

                    public void click() {
                        if (getInvQuery().isEmpty()) { return; }
                        getInvQuery().next().click(0);
                    }
                }

                public enum Bracelet {
                    GOLD_BRACELET(11069),
                    SAPPHIRE_BRACELET(11072),
                    EMERALD_BRACELET(11074),
                    RUBY_BRACELET(11076),
                    DIAMOND_BRACELET(11078),
                    DRAGONSTONE_BRACELET(11080),
                    ONYX_BRACELET(11130),
                    ZENYTE_BRACELET(19541);

                    private final int id;

                    Bracelet(int id) {
                        this.id = id;
                    }

                    public int getID() {
                        return id;
                    }

                    public SimpleItemQuery<SimpleItem> getInvQuery() {
                        return c.inventory.populate().filter(id);
                    }

                    public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                        return c.groundItems.populate().filter(id);
                    }

                    public void click(String interaction) {
                        if (getInvQuery().isEmpty()) { return; }
                        getInvQuery().next().menuAction(interaction);
                    }

                    public void click() {
                        if (getInvQuery().isEmpty()) { return; }
                        getInvQuery().next().click(0);
                    }
                }

                public enum Symbol {
                    HOLY_SYMBOL(1718),
                    UNHOLY_SYMBOL(1724);

                    private final int id;

                    Symbol(int id) {
                        this.id = id;
                    }

                    public int getID() {
                        return id;
                    }

                    public SimpleItemQuery<SimpleItem> getInvQuery() {
                        return c.inventory.populate().filter(id);
                    }

                    public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                        return c.groundItems.populate().filter(id);
                    }

                    public void click(String interaction) {
                        if (getInvQuery().isEmpty()) { return; }
                        getInvQuery().next().menuAction(interaction);
                    }

                    public void click() {
                        if (getInvQuery().isEmpty()) { return; }
                        getInvQuery().next().click(0);
                    }
                }

                public enum Tiara {
                    TIARA(5525);

                    private final int id;

                    Tiara(int id) {
                        this.id = id;
                    }

                    public int getID() {
                        return id;
                    }

                    public SimpleItemQuery<SimpleItem> getInvQuery() {
                        return c.inventory.populate().filter(id);
                    }

                    public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                        return c.groundItems.populate().filter(id);
                    }

                    public void click(String interaction) {
                        if (getInvQuery().isEmpty()) { return; }
                        getInvQuery().next().menuAction(interaction);
                    }

                    public void click() {
                        if (getInvQuery().isEmpty()) { return; }
                        getInvQuery().next().click(0);
                    }
                }
            }

            public enum Arrowtips {
                BRONZE(39),
                IRON(40),
                STEEL(41),
                MITHRIL(42),
                ADAMANT(43),
                RUNE(44),
                DRAGON(11237),
                AMETHYST(21350);

                private final int id;

                Arrowtips(int id) {
                    this.id = id;
                }

                public int getID() {
                    return id;
                }

                public SimpleItemQuery<SimpleItem> getInvQuery() {
                    return c.inventory.populate().filter(id);
                }

                public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                    return c.groundItems.populate().filter(id);
                }

                public void click(String interaction) {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().menuAction(interaction);
                }

                public void click() {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().click(0);
                }
            }

            public enum BoltTips {
                OPAL(45),
                JADE(9187),
                PEARL(46),
                TOPAZ(9188),
                SAPPHIRE(9189),
                EMERALD(9190),
                RUBY(9191),
                DIAMOND(9192),
                DRAGONSTONE(9193),
                ONYX(9194),
                AMETHYST(21338);

                private final int id;

                BoltTips(int id) {
                    this.id = id;
                }

                public int getID() {
                    return id;
                }

                public SimpleItemQuery<SimpleItem> getInvQuery() {
                    return c.inventory.populate().filter(id);
                }

                public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                    return c.groundItems.populate().filter(id);
                }

                public void click(String interaction) {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().menuAction(interaction);
                }

                public void click() {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().click(0);
                }
            }

        }

        public enum Firemaking {
            TINDERBOX(590);

            private final int[] ids;

            Firemaking(int... Ids) {
                ids = Ids;
            }

            public int[] getIDs() {
                return ids;
            }

            public SimpleItemQuery<SimpleItem> getInvQuery() {
                return c.inventory.populate().filter(ids);
            }
            public void clickP(String interaction) {
                if (getInvQuery().isEmpty()) {return;}
                getInvQuery().next().menuAction(interaction);
            }
            public void click(String interaction) {
                if (getInvQuery().isEmpty()) {return;}
                getInvQuery().next().menuAction(interaction);
            }
            public void click() {
                if (getInvQuery().isEmpty()) {return;}
                getInvQuery().next().click(0);
            }

        }

        public enum Woodcutting {
            LOGS(1511),
            OAK_LOGS(1521),
            WILLOW_LOGS(1519),
            MAPLE_LOGS(1517),
            YEW_LOGS(1515),
            MAGIC_LOGS(1513),
            TEAK_LOGS(6333),
            MAHOGANY_LOGS(6332);

            private final int[] ids;

            Woodcutting(int... ids) {
                this.ids = ids;
            }

            public int[] getIDs() {
                return ids;
            }

            public SimpleItemQuery<SimpleItem> getInvQuery() {
                return c.inventory.populate().filter(ids);
            }

            public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                return c.groundItems.populate().filter(ids);
            }

            public void click(String interaction) {
                if (getInvQuery().isEmpty()) { return; }
                getInvQuery().next().menuAction(interaction);
            }

            public void click() {
                if (getInvQuery().isEmpty()) { return; }
                getInvQuery().next().click(0);
            }

            public enum Axes {
                ALL(1351, 1349, 1353, 1361, 1355, 1357, 1359, 6739, 20011, 13241),
                BRONZE_AXE(1351),
                IRON_AXE(1349),
                STEEL_AXE(1353),
                BLACK_AXE(1361),
                MITHRIL_AXE(1355),
                ADAMANT_AXE(1357),
                RUNE_AXE(1359),
                DRAGON_AXE(6739),
                THIRD_AGE_AXE(20011),
                INFERNAL_AXE(13241);

                private final int[] id;

                Axes(int... id) {
                    this.id = id;
                }

                public int getID() {
                    return id[0];
                }

                public int[] getIDs() {
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
                    getInvQuery().next().menuAction("Use");
                }

                public void unequip() {
                    if (getEquipQuery().isEmpty()) { return; }
                    getEquipQuery().next().menuAction("Remove");
                }
            }

        }

        public enum Construction {
            SAW(8794),
            PLANK(960),
            OAK_PLANK(8778),
            TEAK_PLANK(8780),
            MAHOGANY_PLANK(8782);

            int id;
            Construction(int ID) {
                id = ID;
            }

            public int getID() {
                return id;
            }

            public SimpleItemQuery<SimpleItem> getInvQuery() {
                return c.inventory.populate().filter(id);
            }
            public void clickP(String interaction) {
                if (getInvQuery().isEmpty()) {return;}
                getInvQuery().next().menuAction(interaction);
            }
            public void click(String interaction) {
                if (getInvQuery().isEmpty()) {return;}
                getInvQuery().next().menuAction(interaction);
            }
            public void click() {
                if (getInvQuery().isEmpty()) {return;}
                getInvQuery().next().click(0);
            }

        }

        public enum Fishing {
            FISHING_ROD(307),
            FLY_FISHING_ROD(309),
            HARPOON(311),
            LOBSTER_POT(301),
            SMALL_FISHING_NET(303),
            BIG_FISHING_NET(305),
            FISHING_BAIT(313),
            FEATHER(314),
            DARK_FISHING_BAIT(11940),
            FISHING_EXPLOSIVE(666),
            BARBARIAN_ROD(11323),
            SACRED_EEL_BAIT(13339),
            OILY_FISHING_ROD(1585),
            CRYSTAL_HARPOON(23955),
            INFERNAL_HARPOON(21028),
            MINNOW_NET(305);

            private final int id;

            Fishing(int id) {
                this.id = id;
            }

            public int getID() {
                return id;
            }

            public SimpleItemQuery<SimpleItem> getInvQuery() {
                return c.inventory.populate().filter(id);
            }

            public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                return c.groundItems.populate().filter(id);
            }

            public void clickP(String interaction) {
                if (getInvQuery().isEmpty()) { return; }
                getInvQuery().next().menuAction(interaction);
            }

            public void click(String interaction) {
                if (getInvQuery().isEmpty()) { return; }
                getInvQuery().next().menuAction(interaction);
            }

            public void click() {
                if (getInvQuery().isEmpty()) { return; }
                getInvQuery().next().click(0);
            }
        }

        public enum Runecrafting {
            RUNE_ESSENCE(1436),
            PURE_ESSENCE(7936),

            AIR_TALISMAN(1438),
            EARTH_TALISMAN(1440),
            FIRE_TALISMAN(1442),
            WATER_TALISMAN(1444),
            BODY_TALISMAN(1446),
            MIND_TALISMAN(1448),
            CHAOS_TALISMAN(1452),
            COSMIC_TALISMAN(1454),
            DEATH_TALISMAN(1456),
            LAW_TALISMAN(1458),
            NATURE_TALISMAN(1462),
            ELEMENTAL_TALISMAN(5516),

            AIR_TIARA(5527),
            EARTH_TIARA(5535),
            FIRE_TIARA(5537),
            WATER_TIARA(5531),
            BODY_TIARA(5533),
            MIND_TIARA(5529),
            CHAOS_TIARA(5543),
            COSMIC_TIARA(5539),
            DEATH_TIARA(5547),
            LAW_TIARA(5545),
            NATURE_TIARA(5541);

            private final int itemId;

            Runecrafting(int itemId) {
                this.itemId = itemId;
            }

            public int getID() {
                return itemId;
            }

            public SimpleItemQuery<SimpleItem> getInvQuery() {
                return c.inventory.populate().filter(itemId);
            }
            public void clickP(String interaction) {
                if (getInvQuery().isEmpty()) {return;}
                getInvQuery().next().menuAction(interaction);
            }
            public void click(String interaction) {
                if (getInvQuery().isEmpty()) {return;}
                getInvQuery().next().menuAction(interaction);
            }
            public void click() {
                if (getInvQuery().isEmpty()) {return;}
                getInvQuery().next().click(0);
            }
        }

        public enum Herblore {
            PESTLE_MORTER(233),
            VIAL_OF_WATER(227),
            VIAL(229);

            private final int itemId;

            Herblore(int ItemID) {
                itemId = ItemID;
            }

            public int getID() {
                return itemId;
            }

            public SimpleItemQuery<SimpleItem> getInvQuery() {
                return c.inventory.populate().filter(itemId);
            }
            public void clickP(String interaction) {
                if (getInvQuery().isEmpty()) {return;}
                getInvQuery().next().menuAction(interaction);
            }
            public void click(String interaction) {
                if (getInvQuery().isEmpty()) {return;}
                getInvQuery().next().menuAction(interaction);
            }
            public void click() {
                if (getInvQuery().isEmpty()) {return;}
                getInvQuery().next().click(0);
            }
        }

        public static class Hunter {

            public enum Traps {
                BIRD_SNARE(10007),
                BOX_TRAP(10009),
                MAGIC_BOX(10025),
                RABBIT_SNARE(10031);

                private final int id;

                Traps(int id) {
                    this.id = id;
                }

                public int getID() {
                    return id;
                }

                public SimpleItemQuery<SimpleItem> getInvQuery() {
                    return c.inventory.populate().filter(id);
                }

                public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                    return c.groundItems.populate().filter(id);
                }

                public void click(String interaction) {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().menuAction(interaction);
                }

                public void click() {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().click(0);
                }
            }

            public enum Tools {
                BUTTERFLY_NET(10010),
                BUTTERFLY_JAR(10012),
                NOOSE_WAND(10006),
                TEASING_STICK(10029),
                UNLIT_TORCH(596),
                FISHING_EXPLOSIVE(666),
                MAGIC_BUTTERFLY_NET(10026);

                private final int id;

                Tools(int id) {
                    this.id = id;
                }

                public int getID() {
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

                public void click(String interaction) {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().menuAction(interaction);
                }

                public void click() {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().click(0);
                }
            }

            public enum Clothing {
                SPOTTED_CAPE(10073),
                SPOTTIER_CAPE(10074),
                LARUPIA_HAT(10041),
                LARUPIA_TOP(10043),
                LARUPIA_LEGS(10045),
                GRAAHK_HEADDRESS(10049),
                GRAAHK_TOP(10047),
                GRAAHK_LEGS(10049),
                KYATT_HAT(10053),
                KYATT_TOP(10051),
                KYATT_LEGS(10053);

                private final int id;

                Clothing(int id) {
                    this.id = id;
                }

                public int getID() {
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
                    getInvQuery().next().menuAction("Wear");
                }

                public void unequip() {
                    if (getEquipQuery().isEmpty()) { return; }
                    getEquipQuery().next().menuAction("Remove");
                }

                public void clickP(String interaction) {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().menuAction(interaction);
                }

                public void click(String interaction) {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().menuAction(interaction);
                }

                public void click() {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().click(0);
                }
            }

            public enum Furs {
                POLAR_KEBBIT_FUR(10117),
                COMMON_KEBBIT_FUR(10119),
                FELDIP_WEASEL_FUR(10121),
                DESERT_DEVIL_FUR(10123),
                SPOTTED_KEBBIT_FUR(10125),
                DARK_KEBBIT_FUR(10127),
                DASHING_KEBBIT_FUR(10129),
                TATTY_POLAR_KEBBIT_FUR(10109),
                TATTY_COMMON_KEBBIT_FUR(10111),
                TATTY_FELDIP_WEASEL_FUR(10113),
                TATTY_DESERT_DEVIL_FUR(10115),
                TATTY_SPOTTED_KEBBIT_FUR(10131),
                TATTY_DARK_KEBBIT_FUR(10133),
                TATTY_DASHING_KEBBIT_FUR(10135);

                private final int id;

                Furs(int id) {
                    this.id = id;
                }

                public int getID() {
                    return id;
                }

                public SimpleItemQuery<SimpleItem> getInvQuery() {
                    return c.inventory.populate().filter(id);
                }

                public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                    return c.groundItems.populate().filter(id);
                }

                public void click(String interaction) {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().menuAction(interaction);
                }

                public void click() {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().click(0);
                }
            }

            public enum Feathers {
                STRIPY_FEATHER(10088),
                ORANGE_FEATHER(10090),
                BLUE_FEATHER(10091),
                RED_FEATHER(10089),
                BLACK_FEATHER(10087);

                private final int id;

                Feathers(int id) {
                    this.id = id;
                }

                public int getID() {
                    return id;
                }

                public SimpleItemQuery<SimpleItem> getInvQuery() {
                    return c.inventory.populate().filter(id);
                }

                public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                    return c.groundItems.populate().filter(id);
                }

                public void click(String interaction) {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().menuAction(interaction);
                }

                public void click() {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().click(0);
                }
            }

        }

        public enum Slayer {
            SLAYER_HELMET(11864),
            REINFORCED_GOGGLES(19639),
            EARMUFFS(4166),
            FACEMASK(4164),
            NOSEPEG(4168),
            SPINY_HELMET(4551),
            SLAYER_RING_ETERNAL(21268),
            SLAYER_GLOVES(6720),
            SLAYER_BELL(10952),
            ROCK_HAMMER(4162),
            ENCHANTED_GEM(4155),
            SLAYER_STAFF(4170),
            BROAD_ARROWS(4160),
            BROAD_BOLTS(13280),
            LEAF_BLADED_SWORD(4158),
            LEAF_BLADED_BATTLEAXE(20727),
            LEAF_BLADED_SPEAR(4159),
            SLAYER_HELMET_I(11865),
            BLACK_MASK(8921),
            BLACK_MASK_I(11784),
            IMBUED_SLAYER_HELMET(11783),
            IMBUED_SLAYER_HELMET_I(11865),
            FULL_SLAYER_HELMET(11864),
            SLAYERS_RESPITE(5759),
            SLAYERS_ENCHANTMENT(21257),
            SUPERIOR_DRAGON_BONES(22124),
            BOOTS_OF_STONE(23037),
            BOOTS_OF_BRIMSTONE(22951),
            DRAGON_HUNTER_CROSSBOW(21012),
            FUNGICIDE_SPRAY(21884),
            SALT_SHAKER(21885),
            UNFINISHED_BROAD_BOLTS(11875),
            UNFINISHED_BROAD_ARROWS(4160),
            BLIGHTED_SUPER_RESTORE_POTION_4(24638),
            BAG_OF_SALT(4161);

            private final int id;

            Slayer(int id) {
                this.id = id;
            }

            public int getID() {
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
                getInvQuery().next().menuAction("Use");
            }

            public void unequip() {
                if (getEquipQuery().isEmpty()) { return; }
                getEquipQuery().next().menuAction("Remove");
            }

            public void click(String interaction) {
                if (getInvQuery().isEmpty()) { return; }
                getInvQuery().next().menuAction(interaction);
            }

            public void click() {
                if (getInvQuery().isEmpty()) { return; }
                getInvQuery().next().click(0);
            }
        }

        public enum Thieving {
            COIN_POUCH(22521, 22522, 22527, 22531),
            SILK(950);

            private final int[] itemIds;

            Thieving(int... itemIds) {
                this.itemIds = itemIds;
            }

            public int[] getItemIds() {
                return itemIds;
            }

            public SimpleItemQuery<SimpleItem> getInvQuery() {
                return c.inventory.populate().filter(itemIds);
            }
            public void clickP(String interaction) {
                if (getInvQuery().isEmpty()) {return;}
                getInvQuery().next().menuAction(interaction);
            }
            public void click(String interaction) {
                if (getInvQuery().isEmpty()) {return;}
                getInvQuery().next().menuAction(interaction);
            }
            public void click() {
                if (getInvQuery().isEmpty()) {return;}
                getInvQuery().next().click(0);
            }
        }

        public enum Smithing {
            BRONZE(2349),
            IRON(2351),
            SILVER(2355),
            STEEL(2353),
            GOLD(2357),
            MITHRIL(2359),
            ADAMANTITE(2361),
            RUNITE(2363);

            int id;
            Smithing(int id) {
                this.id = id;
            }
            public int getID() {return id;}
            public SimpleItemQuery<SimpleItem> getInvQuery() { return c.inventory.populate().filter(getID()); }

            public enum Tools {
                HAMMER(2347);

                int id;
                Tools(int id) {
                    this.id = id;
                }
                public int getID() {return id;}
                public SimpleItemQuery<SimpleItem> getInvQuery() { return c.inventory.populate().filter(getID()); }
            }
        }

        public static class Mining {
            public enum Ore {
                COPPER(436),
                TIN(438),
                COAL(453),
                IRON(440),
                SILVER(442),
                GOLD(444),
                MITHRIL(447),
                ADAMANTITE(449),
                RUNITE(451);

                int id;

                Ore(int id) {
                    this.id = id;
                }

                public int getID() {
                    return id;
                }

                public SimpleItemQuery<SimpleItem> getInvQuery() {
                    return c.inventory.populate().filter(getID());
                }


            }
            public enum Pickaxe {
                ALL(1265, 1267, 12297, 1269, 1273, 1271, 1275, 11920, 1265, 20014, 13243, 23680),
                BRONZE_PICKAXE(1265),
                IRON_PICKAXE(1267),
                BLACK_PICKAXE(12297),
                STEEL_PICKAXE(1269),
                MITHRIL_PICKAXE(1273),
                ADAMANT_PICKAXE(1271),
                RUNE_PICKAXE(1275),
                DRAGON_PICKAXE(11920),
                GUILDED_PICKAXE(1265),
                THRD_AGE_PICKAXE(20014),
                INFERNAL_PICKAXE(13243),
                CRYSTAL_PICKAXE(23680);

                int[] id;
                Pickaxe(int... id) {
                    this.id = id;
                }

                public int[] getIDs() {
                    return id;
                }
                public int getID() {
                    return id[0];
                }

                public SimpleItemQuery<SimpleItem> getInvQuery() {
                    return c.inventory.populate().filter(getID());
                }
            }
        }

        public static class Fletching {

            public enum Arrows {
                ARROW_SHAFTS(52),
                HEADLESS_ARROWS(53);

                private final int id;

                Arrows(int id) {
                    this.id = id;
                }

                public int getID() {
                    return id;
                }

                public SimpleItemQuery<SimpleItem> getInvQuery() {
                    return c.inventory.populate().filter(id);
                }

                public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                    return c.groundItems.populate().filter(id);
                }

                public void clickP(String interaction) {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().menuAction(interaction);
                }

                public void click(String interaction) {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().menuAction(interaction);
                }

                public void click() {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().click(0);
                }
            }

            public enum UnstrungShortbows {
                SHORTBOW(50),
                OAK_SHORTBOW(54),
                WILLOW_SHORTBOW(60),
                MAPLE_SHORTBOW(64),
                YEW_SHORTBOW(68),
                MAGIC_SHORTBOW(72);

                private final int id;

                UnstrungShortbows(int id) {
                    this.id = id;
                }

                public int getID() {
                    return id;
                }

                public SimpleItemQuery<SimpleItem> getInvQuery() {
                    return c.inventory.populate().filter(id);
                }

                public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                    return c.groundItems.populate().filter(id);
                }

                public void clickP(String interaction) {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().menuAction(interaction);
                }

                public void click(String interaction) {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().menuAction(interaction);
                }

                public void click() {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().click(0);
                }
            }

            public enum UnstrungLongbows {
                UNSTRUNG_LONGBOW(48),
                OAK_LONGBOW(56),
                WILLOW_LONGBOW(58),
                MAPLE_LONGBOW(62),
                YEW_LONGBOW(66),
                MAGIC_LONGBOW(70);

                private final int id;

                UnstrungLongbows(int id) {
                    this.id = id;
                }

                public int getID() {
                    return id;
                }

                public SimpleItemQuery<SimpleItem> getInvQuery() {
                    return c.inventory.populate().filter(id);
                }

                public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                    return c.groundItems.populate().filter(id);
                }

                public void clickP(String interaction) {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().menuAction(interaction);
                }

                public void click(String interaction) {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().menuAction(interaction);
                }

                public void click() {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().click(0);
                }
            }

            public enum Bolts {
                BROAD_BOLTS_UNF(11875),
                BRONZE_BOLTS_UNF(9375),
                BLURITE_BOLTS_UNF(9376),
                IRON_BOLTS_UNF(9377),
                SILVER_BOLTS_UNF(9382),
                STEEL_BOLTS_UNF(9378),
                MITHRIL_BOLTS_UNF(9379),
                ADAMANT_BOLTS_UNF(9380),
                RUNITE_BOLTS_UNF(9381),
                DRAGON_BOLTS_UNF(21905);

                private final int id;

                Bolts(int id) {
                    this.id = id;
                }

                public int getID() {
                    return id;
                }

                public SimpleItemQuery<SimpleItem> getInvQuery() {
                    return c.inventory.populate().filter(id);
                }

                public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                    return c.groundItems.populate().filter(id);
                }

                public void clickP(String interaction) {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().menuAction(interaction);
                }

                public void click(String interaction) {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().menuAction(interaction);
                }

                public void click() {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().click(0);
                }
            }

            public enum Darts {
                BRONZE_DART_TIP(819),
                IRON_DART_TIP(820),
                STEEL_DART_TIP(821),
                MITHRIL_DART_TIP(822),
                ADAMANT_DART_TIP(823),
                RUNE_DART_TIP(824),
                DRAGON_DART_TIP(11232);

                private final int id;

                Darts(int id) {
                    this.id = id;
                }

                public int getID() {
                    return id;
                }

                public SimpleItemQuery<SimpleItem> getInvQuery() {
                    return c.inventory.populate().filter(id);
                }

                public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                    return c.groundItems.populate().filter(id);
                }

                public void clickP(String interaction) {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().menuAction(interaction);
                }

                public void click(String interaction) {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().menuAction(interaction);
                }

                public void click() {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().click(0);
                }
            }

            public enum Crossbows {
                BRONZE_CROSSBOW_U(9454),
                BLURITE_CROSSBOW_U(9456),
                IRON_CROSSBOW_U(9457),
                STEEL_CROSSBOW_U(9459),
                MITHRIL_CROSSBOW_U(9461),
                ADAMANT_CROSSBOW_U(9463),
                RUNITE_CROSSBOW_U(9465),
                DRAGON_CROSSBOW_U(21930);

                private final int id;

                Crossbows(int id) {
                    this.id = id;
                }

                public int getID() {
                    return id;
                }

                public SimpleItemQuery<SimpleItem> getInvQuery() {
                    return c.inventory.populate().filter(id);
                }

                public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                    return c.groundItems.populate().filter(id);
                }

                public void clickP(String interaction) {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().menuAction(interaction);
                }

                public void click(String interaction) {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().menuAction(interaction);
                }

                public void click() {
                    if (getInvQuery().isEmpty()) { return; }
                    getInvQuery().next().click(0);
                }
            }
        }

    }

}
