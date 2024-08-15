package org.data.database;

import simple.hooks.queries.SimpleEntityQuery;
import simple.hooks.queries.SimpleItemQuery;
import simple.hooks.wrappers.SimpleGroundItem;
import simple.hooks.wrappers.SimpleItem;
import simple.robot.api.ClientContext;

public class Melee {
    private static ClientContext c = ClientContext.instance();

    public enum FullHelm {
        BRONZE_FULL_HELM(1155),
        IRON_FULL_HELM(1153),
        STEEL_FULL_HELM(1157),
        BLACK_FULL_HELM(1165),
        MITHRIL_FULL_HELM(1159),
        ADAMANT_FULL_HELM(1161),
        RUNE_FULL_HELM(1163),
        DRAGON_FULL_HELM(11335);

        private final int itemId;
        

        FullHelm(int itemId) {
            this.itemId = itemId;
        }

        public int getId() {
            return itemId;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(itemId);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return c.groundItems.populate().filter(itemId);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return c.equipment.populate().filter(itemId);
        }

        public void equip() {
            if (getInvQuery().isEmpty()) {
                return;
            }
            getInvQuery().next().menuAction("Wear");
        }

        public void unequip() {
            if (getEquipQuery().isEmpty()) {
                return;
            }
            getEquipQuery().next().menuAction("Remove");
        }
    }

    public enum MedHelm {
        BRONZE_MED_HELM(1139),
        IRON_MED_HELM(1137),
        STEEL_MED_HELM(1141),
        BLACK_MED_HELM(1151),
        MITHRIL_MED_HELM(1143),
        ADAMANT_MED_HELM(1145),
        RUNE_MED_HELM(1147),
        DRAGON_MED_HELM(1149);

        private final int itemId;
        

        MedHelm(int itemId) {
            this.itemId = itemId;
        }

        public int getId() {
            return itemId;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(itemId);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return c.groundItems.populate().filter(itemId);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return c.equipment.populate().filter(itemId);
        }

        public void equip() {
            if (getInvQuery().isEmpty()) {
                return;
            }
            getInvQuery().next().menuAction("Wear");
        }

        public void unequip() {
            if (getEquipQuery().isEmpty()) {
                return;
            }
            getEquipQuery().next().menuAction("Remove");
        }
    }

    public enum Platebody {
        BRONZE_PLATEBODY(1117),
        IRON_PLATEBODY(1115),
        STEEL_PLATEBODY(1119),
        BLACK_PLATEBODY(1125),
        MITHRIL_PLATEBODY(1121),
        ADAMANT_PLATEBODY(1123),
        RUNE_PLATEBODY(1127),
        DRAGON_PLATEBODY(3140);  // Assuming Dragon Platebody is added

        private final int itemId;
        

        Platebody(int itemId) {
            this.itemId = itemId;
        }

        public int getId() {
            return itemId;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(itemId);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return c.groundItems.populate().filter(itemId);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return c.equipment.populate().filter(itemId);
        }

        public void equip() {
            if (getInvQuery().isEmpty()) {
                return;
            }
            getInvQuery().next().menuAction("Wear");
        }

        public void unequip() {
            if (getEquipQuery().isEmpty()) {
                return;
            }
            getEquipQuery().next().menuAction("Remove");
        }
    }

    public enum Chainbody {
        BRONZE_CHAINBODY(1103),
        IRON_CHAINBODY(1101),
        STEEL_CHAINBODY(1105),
        BLACK_CHAINBODY(1107),
        MITHRIL_CHAINBODY(1109),
        ADAMANT_CHAINBODY(1111),
        RUNE_CHAINBODY(1113),
        DRAGON_CHAINBODY(3140);  // Assuming Dragon Chainbody is added

        private final int itemId;
        

        Chainbody(int itemId) {
            this.itemId = itemId;
        }

        public int getId() {
            return itemId;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(itemId);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return c.groundItems.populate().filter(itemId);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return c.equipment.populate().filter(itemId);
        }

        public void equip() {
            if (getInvQuery().isEmpty()) {
                return;
            }
            getInvQuery().next().menuAction("Wear");
        }

        public void unequip() {
            if (getEquipQuery().isEmpty()) {
                return;
            }
            getEquipQuery().next().menuAction("Remove");
        }
    }

    public enum Platelegs {
        BRONZE_PLATELEGS(1075),
        IRON_PLATELEGS(1067),
        STEEL_PLATELEGS(1069),
        BLACK_PLATELEGS(1077),
        MITHRIL_PLATELEGS(1071),
        ADAMANT_PLATELEGS(1073),
        RUNE_PLATELEGS(1079),
        DRAGON_PLATELEGS(4087);

        private final int itemId;
        

        Platelegs(int itemId) {
            this.itemId = itemId;
        }

        public int getId() {
            return itemId;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(itemId);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return c.groundItems.populate().filter(itemId);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return c.equipment.populate().filter(itemId);
        }

        public void equip() {
            if (getInvQuery().isEmpty()) {
                return;
            }
            getInvQuery().next().menuAction("Wear");
        }

        public void unequip() {
            if (getEquipQuery().isEmpty()) {
                return;
            }

        }
    }

    public enum Plateskirt {
        BRONZE_PLATESKIRT(1087),
        IRON_PLATESKIRT(1081),
        STEEL_PLATESKIRT(1083),
        BLACK_PLATESKIRT(1089),
        MITHRIL_PLATESKIRT(1085),
        ADAMANT_PLATESKIRT(1091),
        RUNE_PLATESKIRT(1093),
        DRAGON_PLATESKIRT(4585);

        private final int itemId;
        

        Plateskirt(int itemId) {
            this.itemId = itemId;
        }

        public int getId() {
            return itemId;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(itemId);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return c.groundItems.populate().filter(itemId);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return c.equipment.populate().filter(itemId);
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

    public enum SqShield {
        BRONZE_SQ_SHIELD(1173),
        IRON_SQ_SHIELD(1175),
        STEEL_SQ_SHIELD(1177),
        BLACK_SQ_SHIELD(1179),
        MITHRIL_SQ_SHIELD(1181),
        ADAMANT_SQ_SHIELD(1183),
        RUNE_SQ_SHIELD(1185),
        DRAGON_SQ_SHIELD(1187);

        private final int itemId;
        

        SqShield(int itemId) {
            this.itemId = itemId;
        }

        public int getId() {
            return itemId;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(itemId);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return c.groundItems.populate().filter(itemId);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return c.equipment.populate().filter(itemId);
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

    public enum KiteShield {
        BRONZE_KITE_SHIELD(1189),
        IRON_KITE_SHIELD(1191),
        STEEL_KITE_SHIELD(1193),
        BLACK_KITE_SHIELD(1195),
        MITHRIL_KITE_SHIELD(1197),
        ADAMANT_KITE_SHIELD(1199),
        RUNE_KITE_SHIELD(1201);

        private final int itemId;
        

        KiteShield(int itemId) {
            this.itemId = itemId;
        }

        public int getId() {
            return itemId;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(itemId);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return c.groundItems.populate().filter(itemId);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return c.equipment.populate().filter(itemId);
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

    public enum Boots {
        BRONZE_BOOTS(4119),
        IRON_BOOTS(4121),
        STEEL_BOOTS(4123),
        BLACK_BOOTS(4125),
        MITHRIL_BOOTS(4127),
        ADAMANT_BOOTS(4129),
        RUNE_BOOTS(4131),
        DRAGON_BOOTS(11732);

        private final int itemId;
        

        Boots(int itemId) {
            this.itemId = itemId;
        }

        public int getId() {
            return itemId;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(itemId);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return c.groundItems.populate().filter(itemId);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return c.equipment.populate().filter(itemId);
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

    public enum Granite {
        GRANITE_SHEILD(3122),
        GRANITE_LEGS(6809);

        private final int itemId;
        

        Granite(int itemId) {
            this.itemId = itemId;
        }

        public int getId() {
            return itemId;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(itemId);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return c.groundItems.populate().filter(itemId);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return c.equipment.populate().filter(itemId);
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

    public enum Sword {
        BRONZE_SWORD(1277),
        IRON_SWORD(1279),
        STEEL_SWORD(1281),
        BLACK_SWORD(1283),
        MITHRIL_SWORD(1285),
        ADAMANT_SWORD(1287),
        RUNE_SWORD(1289),
        DRAGON_SWORD(21009);

        private final int id;
        ClientContext c = ClientContext.instance();

        Sword(int id) {
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

    public enum Longsword {
        BRONZE_LONGSWORD(1291),
        IRON_LONGSWORD(1293),
        STEEL_LONGSWORD(1295),
        BLACK_LONGSWORD(1297),
        MITHRIL_LONGSWORD(1299),
        ADAMANT_LONGSWORD(1301),
        RUNE_LONGSWORD(1303),
        DRAGON_LONGSWORD(1305),
        THIRD_AGE_LONGSWORD(12426),
        VESTAS_LONGSWORD_FULL(22613),
        VESTAS_LONGSWORD_ACTIVE(23615),
        VESTAS_LONGSWORD_EMPTY(24619);

        private final int id;
        ClientContext c = ClientContext.instance();

        Longsword(int id) {
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

    public enum Scimitar {
        BRONZE_SCIMITAR(1321),
        IRON_SCIMITAR(1323),
        STEEL_SCIMITAR(1325),
        BLACK_SCIMITAR(1327),
        MITHRIL_SCIMITAR(1329),
        ADAMANT_SCIMITAR(1331),
        RUNE_SCIMITAR(1333),
        DRAGON_SCIMITAR(4587),
        GILDED_SCIMITAR(12389);

        private final int id;
        ClientContext c = ClientContext.instance();

        Scimitar(int id) {
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

    public enum Dagger {
        BRONZE(1205, 1221, 5670, 5688),
        IRON(1203, 1219, 5668, 5686),
        STEEL(1207, 1223, 5672, 5690),
        BLACK(1217, 1233, 5682, 5700),
        MITHRIL(1209, 1225, 5674, 5692),
        ADAMANT(1211, 1227, 5676, 5694),
        RUNE(1213, 1229, 5678, 5696),
        DRAGON(1215, 1231, 5680, 5698);

        private final int id;
        private final int pId;
        private final int pxId;
        private final int pxxId;
        ClientContext c = ClientContext.instance();

        Dagger(int id, int pId, int pxId, int pxxId) {
            this.id = id;
            this.pId = pId;
            this.pxId = pxId;
            this.pxxId = pxxId;
        }

        public int getId() {
            return id;
        }

        public int getP() {
            return pId;
        }

        public int getPx() {
            return pxId;
        }

        public int getPxx() {
            return pxxId;
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

    public enum Spear {
        RUNE_SPEAR(1247);

        private final int id;
        ClientContext c = ClientContext.instance();

        Spear(int id) {
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

    public enum TwoHSword {
        RUNE_2H_SWORD(1319);

        private final int id;
        ClientContext c = ClientContext.instance();

        TwoHSword(int id) {
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

    public enum Battleaxe {
        RUNE_BATTLEAXE(1373);

        private final int id;
        ClientContext c = ClientContext.instance();

        Battleaxe(int id) {
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

    public enum Abyssal {
        DAGGER(13265),
        WHIP(4151);

        private final int id;
        ClientContext c = ClientContext.instance();

        Abyssal(int id) {
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

    public enum Hasta {
        BRONZE_HASTA(11367),
        IRON_HASTA(11369),
        STEEL_HASTA(11371),
        MITHRIL_HASTA(11373),
        ADAMANT_HASTA(11375),
        RUNE_HASTA(11377),
        DRAGON_HASTA(22731),
        ZAMORAKIAN_HASTA(11889),
        GILDED_HASTA(20014);

        private final int id;

        Hasta(int id) {
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


    public enum Warhammer {
        BRONZE_WARHAMMER(1337),
        IRON_WARHAMMER(1335),
        STEEL_WARHAMMER(1339),
        BLACK_WARHAMMER(1341),
        MITHRIL_WARHAMMER(1343),
        ADAMANT_WARHAMMER(1345),
        RUNE_WARHAMMER(1347),
        DRAGON_WARHAMMER(13576),
        STATIUS_WARHAMMER(22622);

        private final int id;

        Warhammer(int id) {
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

    public enum SpecialityKit {
        ANTI_DRAGON_SHIELD(1540),
        DRAGONFIRE_SHIELD(11283),
        MIND_SHIELD(9731),
        ELEMENTAL_SHIELD(2890),
        ANCIENT_WYVERN_SHIELD(21634),
        CRYSTAL_SHIELD(4224),
        PHOENIX_NECKLACE(11090),
        BANDOS_WARSHIELD(21736),
        LUNAR_SHIELD(8992);

        private final int itemId;
        ClientContext c = ClientContext.instance();

        SpecialityKit(int itemId) {
            this.itemId = itemId;
        }

        public int getItemId() {
            return itemId;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(itemId);
        }
        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return c.groundItems.populate().filter(itemId);
        }
        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return c.equipment.populate().filter(itemId);
        }


        public void equip() {
            if (getInvQuery().isEmpty()) {return;}
            getInvQuery().next().menuAction("Wear");
        }

        public void unequip() {
            if (getEquipQuery().isEmpty()) {return;}
            getEquipQuery().next().menuAction("Remove");
        }
    }

    public enum SpiritShields {
        ARCANE_SPIRIT_SHIELD(12825),
        SPECTRAL_SPIRIT_SHIELD(12821),
        ELYSIAN_SPIRIT_SHIELD(12817),
        DIVINE_SPIRIT_SHIELD(12819);

        private final int itemId;
        ClientContext c = ClientContext.instance();

        SpiritShields(int itemId) {
            this.itemId = itemId;
        }

        public int getItemId() {
            return itemId;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(itemId);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return c.groundItems.populate().filter(itemId);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return c.equipment.populate().filter(itemId);
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

    public enum Defender {
        BRONZE_DEFENDER(8844),
        IRON_DEFENDER(8845),
        STEEL_DEFENDER(8846),
        BLACK_DEFENDER(8847),
        MITHRIL_DEFENDER(8848),
        ADAMANT_DEFENDER(8849),
        RUNE_DEFENDER(8850),
        DRAGON_DEFENDER(12954),
        AVERNIC_DEFENDER(22322);

        private final int itemId;
        ClientContext c = ClientContext.instance();

        Defender(int itemId) {
            this.itemId = itemId;
        }

        public int getItemId() {
            return itemId;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(itemId);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return c.groundItems.populate().filter(itemId);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return c.equipment.populate().filter(itemId);
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

    public enum RFDGloves {
        BRONZE_GLOVES(7453),
        IRON_GLOVES(7454),
        STEEL_GLOVES(7455),
        BLACK_GLOVES(7456),
        MITHRIL_GLOVES(7457),
        ADAMANT_GLOVES(7458),
        RUNE_GLOVES(7459),
        DRAGON_GLOVES(7460),
        BARROWS_GLOVES(7462);

        private final int itemId;
        ClientContext c = ClientContext.instance();

        RFDGloves(int itemId) {
            this.itemId = itemId;
        }

        public int getItemId() {
            return itemId;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(itemId);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return c.groundItems.populate().filter(itemId);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return c.equipment.populate().filter(itemId);
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

    public static class Barrows {
        public enum Guthans {
            GUTHANS_HELM(4724),
            GUTHANS_PLATEBODY(4728),
            GUTHANS_CHAINSKIRT(4730),
            GUTHANS_WARSPEAR(4726);

            private final int itemId;
            ClientContext c = ClientContext.instance();

            Guthans(int itemId) {
                this.itemId = itemId;
            }

            public int getItemId() {
                return itemId;
            }

            public SimpleItemQuery<SimpleItem> getInvQuery() {
                return c.inventory.populate().filter(itemId);
            }

            public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                return c.groundItems.populate().filter(itemId);
            }

            public SimpleItemQuery<SimpleItem> getEquipQuery() {
                return c.equipment.populate().filter(itemId);
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

        public enum Dharok {
            DHAROKS_HELM(4716),
            DHAROKS_PLATEBODY(4720),
            DHAROKS_PLATELEGS(4722),
            DHAROKS_GREATAXE(4718);

            private final int itemId;
            ClientContext c = ClientContext.instance();

            Dharok(int itemId) {
                this.itemId = itemId;
            }

            public int getItemId() {
                return itemId;
            }

            public SimpleItemQuery<SimpleItem> getInvQuery() {
                return c.inventory.populate().filter(itemId);
            }

            public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                return c.groundItems.populate().filter(itemId);
            }

            public SimpleItemQuery<SimpleItem> getEquipQuery() {
                return c.equipment.populate().filter(itemId);
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

        public enum Verac {
            VERACS_HELM(4753),
            VERACS_BRASSARD(4757),
            VERACS_PLATESKIRT(4759),
            VERACS_FLAIL(4755);

            private final int itemId;
            ClientContext c = ClientContext.instance();

            Verac(int itemId) {
                this.itemId = itemId;
            }

            public int getItemId() {
                return itemId;
            }

            public SimpleItemQuery<SimpleItem> getInvQuery() {
                return c.inventory.populate().filter(itemId);
            }

            public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                return c.groundItems.populate().filter(itemId);
            }

            public SimpleItemQuery<SimpleItem> getEquipQuery() {
                return c.equipment.populate().filter(itemId);
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

        public enum Torag {
            TORAGS_HELM(4745),
            TORAGS_PLATEBODY(4749),
            TORAGS_PLATELEGS(4751),
            TORAGS_HAMMERS(4747);

            private final int itemId;
            ClientContext c = ClientContext.instance();

            Torag(int itemId) {
                this.itemId = itemId;
            }

            public int getItemId() {
                return itemId;
            }

            public SimpleItemQuery<SimpleItem> getInvQuery() {
                return c.inventory.populate().filter(itemId);
            }

            public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
                return c.groundItems.populate().filter(itemId);
            }

            public SimpleItemQuery<SimpleItem> getEquipQuery() {
                return c.equipment.populate().filter(itemId);
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
