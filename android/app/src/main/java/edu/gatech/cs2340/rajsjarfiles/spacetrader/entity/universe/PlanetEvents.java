package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import java.util.Random;

/**
 * Represents an event that can occur on a planet.
 */
public enum PlanetEvents {
    DROUGHT,
    COLD,
    CROP_FAIL,
    WAR,
    BOREDOM,
    PLAGUE,
    LACK_OF_WORKERS;

    /**
     * @return a random event
     */
    public static PlanetEvents getRandomEvent() {
        Random rand = new Random();
        PlanetEvents[] re = PlanetEvents.values();
        return re[rand.nextInt(re.length)];
    }
}