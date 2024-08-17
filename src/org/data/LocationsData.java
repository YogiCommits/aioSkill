package org.data;

import simple.robot.utils.WorldArea;

public enum LocationsData {
    HOME("home", new WorldArea(2226, 3319, 25, 28, 0)),
    MINING_DONATOR_ZONE("home", new WorldArea(2263, 3318, 3, 7, 0)),
    WOODCUTTING_DONATOR_ZONE("home", new WorldArea(2203, 3368, 23, 8, 0)),
    HOME_MINING("home mining area", new WorldArea(2267, 3315, 11, 17, 0)),
    HOME_MINING2("home mining area", new WorldArea(2277, 3320, 13, 15,
            0)),
    BARROWS_MINING("barrows mining area", new WorldArea(3544, 3264, 16, 10, 0)),
    BARROWS_MINING2("barrows mining area", new WorldArea(3560, 3265, 4, 5, 0)),
    THIEVE("thieving", new WorldArea(2255, 3302, 23, 11, 0)),
    WOODCUTTING("WOODCUTTING", new WorldArea(2254, 3352, 25, 30, 0)),
    SLAYER_BARROWS("", new WorldArea(3569, 3294, 12, 15, 0)),
    SLAYER_EMERALD("", new WorldArea(3178, 12398, 15, 16, 0)),
    SLAYER_KRAKEN("", new WorldArea(2263, 10001, 15, 16, 0)),
    SLAYER_GREEN_DRAGON("", new WorldArea(2584, 9425, 15, 15, 0)),
    SLAYER_BLACK_DRAGON("", new WorldArea(2821, 9825, 6, 3, 0)),
    ESSENCE_BANK("", new WorldArea(2821, 9825, 6, 3, 0));

    private final String locationName;
    private final WorldArea worldArea;

    LocationsData(String locationName, WorldArea worldArea) {
        this.locationName = locationName;
        this.worldArea = worldArea;
    }

    public String getLocationName() {
        return locationName;
    }

    public WorldArea getWorldArea() {
        return worldArea;
    }
}
