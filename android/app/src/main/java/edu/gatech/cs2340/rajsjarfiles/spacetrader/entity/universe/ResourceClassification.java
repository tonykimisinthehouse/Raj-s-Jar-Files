package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import java.util.Random;

/**
 * Represents a planet's resource classification.
 */
public enum ResourceClassification {
    NO_SPECIAL_RESOURCES,
    MINERAL_RICH,
    MINERAL_POOR,
    DESERT,
    LOTS_OF_WATER,
    RICH_SOIL,
    POOR_SOIL,
    RICH_FAUNA,
    LIFELESS,
    WEIRD_MUSHROOMS,
    LOTS_OF_HERBS,
    ARTISTIC,
    WARLIKE;

    private static Random rand = new Random();

    /**
     * Returns a random resource class. The implementation may vary
     * (different weights for each class).
     *
     * @return a random tech level
     */
    public static ResourceClassification getRandomResourceClass() {
        ResourceClassification[] rc = ResourceClassification.values();
        return rc[rand.nextInt(rc.length)];
    }
}
