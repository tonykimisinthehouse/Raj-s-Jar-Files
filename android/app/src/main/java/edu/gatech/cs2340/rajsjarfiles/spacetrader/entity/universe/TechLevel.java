package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import java.util.Random;

/**
 * Represents a planet's technological level.
 */
public enum TechLevel {
    PRE_AGRICULTURE,
    AGRICULTURE,
    MEDIEVAL,
    RENAISSANCE,
    EARLY_INDUSTRIAL,
    INDUSTRIAL,
    POST_INDUSTRIAL,
    HI_TECH;

    private static final Random rand = new Random();

    /**
     * Returns a random tech level. The implementation may vary (different
     * weights for each level).
     *
     * @return a random tech level
     */
    public static TechLevel getRandomTechLevel() {
        TechLevel[] tl = TechLevel.values();
        return tl[rand.nextInt(tl.length)];
    }
}
