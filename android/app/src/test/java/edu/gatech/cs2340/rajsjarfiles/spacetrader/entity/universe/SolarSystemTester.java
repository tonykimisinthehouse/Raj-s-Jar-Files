package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests the SolarSystem class.
 */
public class SolarSystemTester {
    private static final int TIMEOUT = 200;

    /**
     * Tests two-arg constructor.
     */
    @Test (timeout = TIMEOUT)
    public void testTwoArgConstructor() {
        SolarSystem ss = new SolarSystem("Thomas", new Coordinate(3, -4));
        assertEquals("Thomas", ss.getName());
        assertEquals(new Coordinate(3, -4), ss.getCoordinate());
    }

    /**
     * Checks for correct solar system generation.
     */
    @Test
    public void testSolarSystemGeneration() {
        for (int i = 0; i < 100; i++) {
            SolarSystem s = new SolarSystem("Raj", new Coordinate());
            System.out.println(s.toString());
        }
    }
}
