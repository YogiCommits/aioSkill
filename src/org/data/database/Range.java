package org.data.database;

import simple.hooks.queries.SimpleEntityQuery;
import simple.hooks.queries.SimpleItemQuery;
import simple.hooks.wrappers.SimpleGroundItem;
import simple.hooks.wrappers.SimpleItem;
import simple.robot.api.ClientContext;

public class Range {

    private static ClientContext c = ClientContext.instance();

    public static class Thrown {
        public enum Dart {
            BRONZE(806),
            IRON(807),
            STEEL(808),
            MITHRIL(809),
            ADAMANT(810),
            RUNE(811);

            private final int id;
            

            Dart(int id) {
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


        public enum Knife {
            BRONZE(864),
            IRON(863),
            BLACK(869),
            STEEL(865),
            MITHRIL(866),
            ADAMANT(867),
            RUNE(868);

            private final int id;
            

            Knife(int id) {
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


        public enum Axe {
            BRONZE_THROWNAXE(800),
            IRON_THROWNAXE(801),
            STEEL_THROWNAXE(802),
            MITHRIL_THROWNAXE(803),
            ADAMANT_THROWNAXE(804),
            RUNE_THROWNAXE(805);

            private final int id;
            

            Axe(int id) {
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


        public enum Javelin {
            BRONZE(825),
            IRON(826),
            STEEL(827),
            MITHRIL(828),
            ADAMANT(829),
            RUNE(830);

            private final int id;
            

            Javelin(int id) {
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
            TOKTZ_XIL_UL(6522);

            private final int id;
            

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

    public static class Ammo {
        public enum Arrow {
            BRONZE(882, 883),
            IRON(884, 885),
            STEEL(886, 887),
            MITHRIL(888, 889),
            ADAMANT(890, 891),
            RUNE(892, 893),
            DRAGON(11212, -1);

            private final int id;
            private final int pId;
            

            Arrow(int id, int pId) {
                this.id = id;
                this.pId = pId;
            }

            public int getId() {
                return id;
            }

            public int getP() {
                return pId;
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
        
        public static class Bolts {
            public enum Metal {
                BRONZE_BOLTS(877),
                IRON_BOLTS(9140),
                STEEL_BOLTS(9141),
                MITHRIL_BOLTS(9142),
                ADAMANT_BOLTS(9143),
                RUNITE_BOLTS(9144),
                DRAGON_BOLTS(21905);

                private final int id;
                

                Metal(int id) {
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

            public enum Gem {
                OPAL_BOLTS(879),
                SAPPHIRE_BOLTS(9337),
                JADE_BOLTS(9335),
                PEARL_BOLTS(880),
                EMERALD_BOLTS(9338),
                TOPAZ_BOLTS(9336),
                RUBY_BOLTS(9339),
                DIAMOND_BOLTS(9340),
                DRAGONSTONE_BOLTS(9341),
                ONYX_BOLTS(9342);

                private final int id;
                

                Gem(int id) {
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

            public enum Enchanted {
                OPAL_BOLTS_E(9236),
                SAPPHIRE_BOLTS_E(9240),
                JADE_BOLTS_E(9237),
                PEARL_BOLTS_E(9238),
                EMERALD_BOLTS_E(9241),
                TOPAZ_BOLTS_E(9239),
                RUBY_BOLTS_E(9242),
                DIAMOND_BOLTS_E(9243),
                DRAGONSTONE_BOLTS_E(9244),
                ONYX_BOLTS_E(9245);

                private final int id;
                

                Enchanted(int id) {
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

            public static class Dragon {
                public enum Gem {
                    OPAL_DRAGON_BOLTS(21955),
                    SAPPHIRE_DRAGON_BOLTS(21963),
                    JADE_DRAGON_BOLTS(21957),
                    PEARL_DRAGON_BOLTS(21959),
                    EMERALD_DRAGON_BOLTS(21965),
                    TOPAZ_DRAGON_BOLTS(21961),
                    RUBY_DRAGON_BOLTS(21967),
                    DIAMOND_DRAGON_BOLTS(21967),
                    DRAGONSTONE_DRAGON_BOLTS(21971),
                    ONYX_DRAGON_BOLTS(21973);

                    private final int id;
                    

                    Gem(int id) {
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
                
                public enum Enchanted {
                    OPAL_DRAGON_BOLTS_E(21932),
                    SAPPHIRE_DRAGON_BOLTS_E(21940),
                    JADE_DRAGON_BOLTS_E(21934),
                    PEARL_DRAGON_BOLTS_E(21936),
                    EMERALD_DRAGON_BOLTS_E(21942),
                    TOPAZ_DRAGON_BOLTS_E(21938),
                    RUBY_DRAGON_BOLTS_E(21944),
                    DIAMOND_DRAGON_BOLTS_E(21946),
                    DRAGONSTONE_DRAGON_BOLTS_E(21948),
                    ONYX_DRAGON_BOLTS_E(21950);

                    private final int id;
                    

                    Enchanted(int id) {
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
        }
    }

    public enum Chin {
        GRAY_CHINCHOMPA(9976),
        RED_CHINCHOMPA(9977),
        BLACK_CHINCHOMPA(11959);

        private final int id;
        

        Chin(int id) {
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
    
    public enum Crossbow {
        CROSSBOW(837),
        DORGESHUUN_CROSSBOW(8880),
        BRONZE_CROSSBOW(9174),
        IRON_CROSSBOW(9177),
        STEEL_CROSSBOW(9179),
        MITHRIL_CROSSBOW(9181),
        ADAMANT_CROSSBOW(9183),
        RUNE_CROSSBOW(9185),
        ARMADYL_CROSSBOW(11785),
        DRAGON_CROSSBOW(21902),
        DRAGON_HUNTER_CROSSBOW(21012);

        private final int id;
        

        Crossbow(int id) {
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

    public enum Shortbow {
        SHORTBOW(841),
        OAK_SHORTBOW(843),
        WILLOW_SHORTBOW(849),
        MAPLE_SHORTBOW(853),
        YEW_SHORTBOW(857),
        MAGIC_SHORTBOW(861);

        private final int id;
        

        Shortbow(int id) {
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
    
    public enum Longbow {
        LONGBOW(839),
        OAK_LONGBOW(845),
        WILLOW_LONGBOW(847),
        MAPLE_LONGBOW(851),
        YEW_LONGBOW(855),
        MAGIC_LONGBOW(859),
        TWISTED_BOW(20997);

        private final int id;
        

        Longbow(int id) {
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
        TOXIC_BLOWPIPE_FULL(12926),
        TOXIC_BLOWPIPE_EMPTY(12924),
        TWISTED_BOW(20997),
        BOW_OF_FAERDHINEN(25865),
        CRYSTAL_BOW(4214),
        DARK_BOW(11235);

        private final int id;
        

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

    public enum Chaps {
        LEATHER_CHAPS(1095),
        STUDDED_CHAPS(1097),
        GREEN_DHIDE_CHAPS(1099),
        BLUE_DHIDE_CHAPS(2493),
        RED_DHIDE_CHAPS(2495),
        BLACK_DHIDE_CHAPS(2497),
        GUTHIX_BLESSED_DHIDE_CHAPS(10390),
        SARADOMIN_BLESSED_DHIDE_CHAPS(10392),
        ZAMORAK_BLESSED_DHIDE_CHAPS(10394),
        ANCIENT_BLESSED_DHIDE_CHAPS(12462),
        ARMADYL_BLESSED_DHIDE_CHAPS(12464),
        BANDOS_BLESSED_DHIDE_CHAPS(12466),
        SNAKESKIN_CHAPS(6324),
        GILDED_DHIDE_CHAPS(23267),
        ZARYTE_CHAPS(24491),
        MASORI_CHAPS(27265);

        private final int id;

        Chaps(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return ClientContext.instance().inventory.populate().filter(id);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return ClientContext.instance().groundItems.populate().filter(id);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return ClientContext.instance().equipment.populate().filter(id);
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

    public enum Body {
        LEATHER_BODY(1129),
        HARDLEATHER_BODY(1131),
        STUDDED_BODY(1133),
        GREEN_DHIDE_BODY(1135),
        BLUE_DHIDE_BODY(2499),
        RED_DHIDE_BODY(2501),
        BLACK_DHIDE_BODY(2503),
        GUTHIX_BLESSED_DHIDE_BODY(10378),
        SARADOMIN_BLESSED_DHIDE_BODY(10380),
        ZAMORAK_BLESSED_DHIDE_BODY(10382),
        ANCIENT_BLESSED_DHIDE_BODY(12468),
        ARMADYL_BLESSED_DHIDE_BODY(12470),
        BANDOS_BLESSED_DHIDE_BODY(12472),
        KARILS_LEATHERTOP(4736),
        MASORI_BODY(27261),
        GILDED_DHIDE_BODY(23269),
        ZARYTE_BODY(24489);

        private final int id;

        Body(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return ClientContext.instance().inventory.populate().filter(id);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return ClientContext.instance().groundItems.populate().filter(id);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return ClientContext.instance().equipment.populate().filter(id);
        }

        public void equip() {
            if (getInvQuery().isEmpty()) { return; }
            getInvQuery().next().menuAction("Wear");
        }

        public void unequip() {
            if (getEquipQuery().isEmpty()) { return; }
            getEquipQuery().next().menuAction("Remove");
        }
    }

    public enum Vambs {
        LEATHER_VAMBRACES(1063),
        GREEN_DHIDE_VAMBRACES(1065),
        BLUE_DHIDE_VAMBRACES(2487),
        RED_DHIDE_VAMBRACES(2489),
        BLACK_DHIDE_VAMBRACES(2491),
        GUTHIX_BLESSED_DHIDE_VAMBRACES(10376),
        SARADOMIN_BLESSED_DHIDE_VAMBRACES(10378),
        ZAMORAK_BLESSED_DHIDE_VAMBRACES(10380),
        ANCIENT_BLESSED_DHIDE_VAMBRACES(12458),
        ARMADYL_BLESSED_DHIDE_VAMBRACES(12460),
        BANDOS_BLESSED_DHIDE_VAMBRACES(12462),
        SNAKESKIN_VAMBRACES(6330),
        ZARYTE_VAMBRACES(24475),
        THIRD_AGE_VAMBRACES(10336),
        GILDED_DHIDE_VAMBRACES(23261),
        MASORI_VAMBRACES(27263);

        private final int id;

        Vambs(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return ClientContext.instance().inventory.populate().filter(id);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return ClientContext.instance().groundItems.populate().filter(id);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return ClientContext.instance().equipment.populate().filter(id);
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

    public enum Boots {
        SNAKESKIN_BOOTS(6328),
        RANGER_BOOTS(2577),
        PEGASIAN_BOOTS(13237),
        GUTHIX_BLESSED_DHIDE_BOOTS(19985),
        SARADOMIN_BLESSED_DHIDE_BOOTS(19952),
        ZAMORAK_BLESSED_DHIDE_BOOTS(19958),
        ANCIENT_BLESSED_DHIDE_BOOTS(19964),
        ARMADYL_BLESSED_DHIDE_BOOTS(19970),
        BANDOS_BLESSED_DHIDE_BOOTS(19976),
        MASORI_BOOTS(27268);

        private final int id;

        Boots(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return ClientContext.instance().inventory.populate().filter(id);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return ClientContext.instance().groundItems.populate().filter(id);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return ClientContext.instance().equipment.populate().filter(id);
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

    public enum Armadyl {
        ARMADYL_HELMET(11826),
        ARMADYL_CHESTPLATE(11828),
        ARMADYL_CHAINSKIRT(11830);

        private final int id;

        Armadyl(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return ClientContext.instance().inventory.populate().filter(id);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return ClientContext.instance().groundItems.populate().filter(id);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return ClientContext.instance().equipment.populate().filter(id);
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
