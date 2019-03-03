package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import org.junit.Test;

/**
 * Tests the PlanetNames class.
 */
public class PlanetNamesTester {
    private static final int TIMEOUT = 200;

    /**
     * Prints randomly generated names.
     */
    @Test (timeout = TIMEOUT)
    public void testGenNames() {
        for (int i = 0; i < 10000; i++) {
//            System.out.println(PlanetNames.generateName());
            PlanetNames.generateName();
        }
    }
}
