package org.data.database;

public class Herblore {
    public enum Herbs {
        GUAM(199, 249, 91),
        MARRENTILL(201, 251, 93),
        TARROMIN(203, 253, 95),
        HARRALANDER(205, 255, 97),
        RANARR(207, 257, 99),
        TOADFLAX(3049, 2998, 3002),
        IRIT(209, 259, 101),
        AVANTOE(211, 261, 103),
        KWUARM(213, 263, 105),
        SNAPDRAGON(3051, 3000, 3004),
        CADANTINE(215, 265, 107),
        LANTADYME(2485, 2481, 2483),
        DWARF_WEED(217, 267, 109),
        TORSTOL(219, 269, 111);

        private final int clean, grimy, unf;

        Herbs(int... itemIds) {
            grimy = itemIds[0];
            clean = itemIds[1];
            unf = itemIds[2];
        }

        public int getClean() {return clean;}
        public int getGrimy() {return grimy;}
        public int getUnf() {return unf;}
    }

    public enum Secondaries {
        SNAPE_GRASS(231),
        TOADS_LEGS(2152),
        EYE_OF_NEWT(221),
        UNICORN_HORN(235, 237),
        RED_SPIDERS_EGGS(223),
        CHOCOLATE(1975, 1973),
        RED_BERRIES(1951),
        DESERT_GOAT_HORN(9736, 9735),
        MORT_MYRE_FUNGUS(2970),
        BLUE_DRAGON_SCALE(241, 243),
        POTATO_CACTUS(3138),
        JANGERBERRIES(247),
        BIRDS_NEST(6693, 5075),
        LIMPWURT_ROOTS(225),
        WHITE_BERRIES(239);

        private final int[] itemIds;

        Secondaries(int... itemIds) {
            this.itemIds = itemIds;
        }

        public int getRawID() {
            return itemIds[1];
        }

        public int getID() {
            return itemIds[0];
        }
    }

}
