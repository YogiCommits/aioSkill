package org.data.database;

public class Seeds {
    public enum Herb {
        GUAM_SEED(5291),
        MARRENTILL_SEED(5292),
        TARROMIN_SEED(5293),
        HARRALANDER_SEED(5294),
        RANARR_SEED(5295),
        TOADFLAX_SEED(5296),
        IRIT_SEED(5297),
        AVANTOE_SEED(5298),
        KWUARM_SEED(5299),
        SNAPDRAGON_SEED(5300),
        CADANTINE_SEED(5301),
        LANTADYME_SEED(5302),
        DWARF_WEED_SEED(5303),
        TORSTOL_SEED(5303);

        private final int seedId;

        Herb(int seedId) {
            this.seedId = seedId;
        }

        public int getSeedId() {
            return seedId;
        }
    }

    public enum Hop {
        BARLEY_SEED(5305),
        HAMMERSTONE_SEED(5307),
        ASGARNIAN_SEED(5308),
        JUTE_SEED(5306),
        YANILLIAN_SEED(5309),
        KRANDORIAN_SEED(5310),
        WILDBLOOD_SEED(5311);

        private final int seedId;

        Hop(int seedId) {
            this.seedId = seedId;
        }

        public int getSeedId() {
            return seedId;
        }
    }

    public enum Flower {
        MARIGOLD_SEED(5096),
        ROSEMARY_SEED(5097),
        NASTURTIUM_SEED(5098),
        WOAD_SEED(5099),
        LIMPWURT_SEED(5100),
        WHITE_LILY_SEED(14589);

        private final int seedId;

        Flower(int seedId) {
            this.seedId = seedId;
        }

        public int getSeedId() {
            return seedId;
        }
    }

    public enum Allotment {
        POTATO_SEED(5318),
        ONION_SEED(5319),
        CABBAGE_SEED(5324),
        TOMATO_SEED(5322),
        SWEETCORN_SEED(5320),
        STRAWBERRY_SEED(5323),
        WATERMELON_SEED(5321),
        SNAPE_GRASS_SEED(28262);

        private final int seedId;

        Allotment(int seedId) {
            this.seedId = seedId;
        }

        public int getSeedId() {
            return seedId;
        }
    }

    public enum Tree {
        ACORN_SEED(5312),
        WILLOW_SEED(5313),
        MAPLE_SEED(5314),
        YEW_SEED(5315),
        MAGIC_SEED(5316),
        SPIRIT_SEED(5317);

        private final int seedId;

        Tree(int seedId) {
            this.seedId = seedId;
        }

        public int getSeedId() {
            return seedId;
        }
    }

    public enum FruitTree {
        APPLE_TREE_SEED(5283),
        BANANA_TREE_SEED(5284),
        ORANGE_TREE_SEED(5285),
        CURRY_TREE_SEED(5286),
        PINEAPPLE_TREE_SEED(5287),
        PAPAYA_TREE_SEED(5288),
        PALM_TREE_SEED(5289),
        DRAGONFRUIT_TREE_SEED(48763);

        private final int seedId;

        FruitTree(int seedId) {
            this.seedId = seedId;
        }

        public int getSeedId() {
            return seedId;
        }
    }
}
