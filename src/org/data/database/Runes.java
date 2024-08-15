package org.data.database;

public enum Runes {
    FIRE_RUNE(554),
    WATER_RUNE(555),
    AIR_RUNE(556),
    EARTH_RUNE(557),
    MIND_RUNE(558),
    BODY_RUNE(559),
    DEATH_RUNE(560),
    NATURE_RUNE(561),
    CHAOS_RUNE(562),
    LAW_RUNE(563),
    COSMIC_RUNE(564),
    BLOOD_RUNE(565),
    SOUL_RUNE(566),
    STEAM_RUNE(4694),
    MIST_RUNE(4695),
    DUST_RUNE(4696),
    SMOKE_RUNE(4697),
    MUD_RUNE(4698),
    LAVA_RUNE(4699);

    private final int runeId;

    Runes(int runeId) {
        this.runeId = runeId;
    }

    public int getRuneId() {
        return runeId;
    }
}

