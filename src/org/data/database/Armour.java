package org.data.database;

import simple.hooks.queries.SimpleEntityQuery;
import simple.hooks.queries.SimpleItemQuery;
import simple.hooks.wrappers.SimpleGroundItem;
import simple.hooks.wrappers.SimpleItem;
import simple.robot.api.ClientContext;

public class Armour {

    public enum Metal {
        BRONZE_FULL_HELM(1155),
        BRONZE_MED_HELM(1139),
        BRONZE_PLATEBODY(1117),
        BRONZE_CHAINBODY(1103),
        BRONZE_PLATELEGS(1075),
        BRONZE_PLATESKIRT(1087),
        BRONZE_SQ_SHIELD(1173),
        BRONZE_KITE_SHIELD(1189),
        BRONZE_BOOTS(4119),

        IRON_FULL_HELM(1153),
        IRON_MED_HELM(1137),
        IRON_PLATEBODY(1115),
        IRON_CHAINBODY(1101),
        IRON_PLATELEGS(1067),
        IRON_PLATESKIRT(1081),
        IRON_SQ_SHIELD(1175),
        IRON_KITE_SHIELD(1191),
        IRON_BOOTS(4121),

        STEEL_FULL_HELM(1157),
        STEEL_MED_HELM(1141),
        STEEL_PLATEBODY(1119),
        STEEL_CHAINBODY(1105),
        STEEL_PLATELEGS(1069),
        STEEL_PLATESKIRT(1083),
        STEEL_SQ_SHIELD(1177),
        STEEL_KITE_SHIELD(1193),
        STEEL_BOOTS(4123),

        BLACK_FULL_HELM(1165),
        BLACK_MED_HELM(1151),
        BLACK_PLATEBODY(1125),
        BLACK_CHAINBODY(1107),
        BLACK_PLATELEGS(1077),
        BLACK_PLATESKIRT(1089),
        BLACK_SQ_SHIELD(1179),
        BLACK_KITE_SHIELD(1195),
        BLACK_BOOTS(4125),

        MITHRIL_FULL_HELM(1159),
        MITHRIL_MED_HELM(1143),
        MITHRIL_PLATEBODY(1121),
        MITHRIL_CHAINBODY(1109),
        MITHRIL_PLATELEGS(1071),
        MITHRIL_PLATESKIRT(1085),
        MITHRIL_SQ_SHIELD(1181),
        MITHRIL_KITE_SHIELD(1197),
        MITHRIL_BOOTS(4127),

        ADAMANT_FULL_HELM(1161),
        ADAMANT_MED_HELM(1145),
        ADAMANT_PLATEBODY(1123),
        ADAMANT_CHAINBODY(1111),
        ADAMANT_PLATELEGS(1073),
        ADAMANT_PLATESKIRT(1091),
        ADAMANT_SQ_SHIELD(1183),
        ADAMANT_KITE_SHIELD(1199),
        ADAMANT_BOOTS(4129),

        RUNE_FULL_HELM(1163),
        RUNE_MED_HELM(1147),
        RUNE_PLATEBODY(1127),
        RUNE_CHAINBODY(1113),
        RUNE_PLATELEGS(1079),
        RUNE_PLATESKIRT(1093),
        RUNE_SQ_SHIELD(1185),
        RUNE_KITE_SHIELD(1201),
        RUNE_BOOTS(4131),

        GRANITE_SHEILD(3122),
        GRANITE_LEGS(6809),

        DRAGON_FULL_HELM(11335),
        DRAGON_MED_HELM(1149),
        DRAGON_CHAINBODY(1113),
        DRAGON_PLATELEGS(4087),
        DRAGON_PLATESKIRT(4585),
        DRAGON_SQ_SHIELD(1187),
        DRAGON_BOOTS(11732);

        private final int itemId;
        ClientContext c = ClientContext.instance();

        Metal(int itemId) {
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

        public void equipP() {
            if (getInvQuery().isEmpty()) {
                return;
            }
            getInvQuery().next().menuAction("Wear");
        }

        public void unequipP() {
            if (getEquipQuery().isEmpty()) {
                return;
            }
            getEquipQuery().next().menuAction("Remove");
        }
    }

    public enum SpecialityKit {
        ANTI_DRAGON_SHIELD(1540),
        DRAGONFIRE_SHIELD(11283);

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
            if (getInvQuery().isEmpty()) {
                return;
            }
            getInvQuery().next().click(0);
        }

        public void equipP() {
            if (getInvQuery().isEmpty()) {
                return;
            }
            getInvQuery().next().menuAction("Wear");
        }

        public void unequip() {
            if (getEquipQuery().isEmpty()) {
                return;
            }
            getEquipQuery().next().click(0);
        }

        public void unequipP() {
            if (getEquipQuery().isEmpty()) {
                return;
            }
            getEquipQuery().next().menuAction("Remove");
        }
    }

    public enum BarrowsSet {
        DHAROK_HELM(4716, 4880, 4881, 4882, 4883, 4884),
        DHAROK_BODY(4720, 4892, 4893, 4894, 4895, 4896),
        DHAROK_LEGS(4722, 4898, 4899, 4900, 4901, 4902),

        KARIL_COIF(4732, 4928, 4929, 4930, 4931, 4932),
        KARIL_BODY(4736, 4940, 4941, 4942, 4943, 4944),
        KARIL_SKIRT(4738, 4946, 4947, 4948, 4949, 4950),

        VERAC_HELM(4753, 4976, 4977, 4978, 4979, 4980),
        VERAC_BODY(4757, 4988, 4989, 4990, 4991, 4992),
        VERAC_SKIRT(4759, 4994, 4995, 4996, 4997, 4998),

        TORAG_HELM(4745, 4952, 4953, 4954, 4955, 4956),
        TORAG_BODY(4749, 4964, 4965, 4966, 4967, 4968),
        TORAG_LEGS(4751, 4970, 4971, 4972, 4973, 4974),

        AHRIM_HOOD(4708, 4856, 4857, 4858, 4859, 4860),
        AHRIM_BODY(4712, 4868, 4869, 4870, 4871, 4872),
        AHRIM_SKIRT(4714, 4874, 4875, 4876, 4877, 4878),

        GUTHAN_HOOD(4724, 4904, 4905, 4906, 4907, 4908),
        GUTHAN_BODY(4728, 4908, 4917, 4918, 4919, 4920),
        GUTHAN_SKIRT(4730, 4922, 4923, 4924, 4925, 4926);

        private int[] itemIds;
        ClientContext c = ClientContext.instance();

        BarrowsSet(int full, int hun, int sev, int fif, int tw, int zer) {
            itemIds = new int[6];
            itemIds[0] = full;
            itemIds[1] = hun;
            itemIds[2] = sev;
            itemIds[3] = fif;
            itemIds[4] = tw;
            itemIds[5] = zer;
        }

        public int[] getItemIds() {
            return itemIds;
        }

        public SimpleItemQuery<SimpleItem> getInvQuery() {
            return c.inventory.populate().filter(itemIds);
        }

        public SimpleEntityQuery<SimpleGroundItem> getGroQuery() {
            return c.groundItems.populate().filter(itemIds);
        }

        public SimpleItemQuery<SimpleItem> getEquipQuery() {
            return c.equipment.populate().filter(itemIds);
        }

        public void equip() {
            if (getInvQuery().isEmpty()) {
                return;
            }
            getInvQuery().next().click(0);
        }

        public void equipP() {
            if (getInvQuery().isEmpty()) {
                return;
            }
            getInvQuery().next().menuAction("Wear");
        }

        public void unequip() {
            if (getEquipQuery().isEmpty()) {
                return;
            }
            getEquipQuery().next().click(0);
        }

        public void unequipP() {
            if (getEquipQuery().isEmpty()) {
                return;
            }
            getEquipQuery().next().menuAction("Remove");
        }
    }

}
