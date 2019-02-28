package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import org.junit.Test;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.planet.Planet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * JUnit tests for the Planet class.
 */
public class PlanetTester {
    private static final int TIMEOUT = 10000;

    /**
     * Tests for two-arg constructor.
     */
    @Test (timeout = TIMEOUT)
    public void testConstructor() {
        Planet planet = new Planet("Raj", 3);
        assertEquals("Raj", planet.getName());
        assertTrue(planet.getRadius() > 0);
        assertEquals(3, planet.getOrbitRadius());
        assertNotNull(planet.getTechLevel());
        assertNotNull(planet.getResourceClass());
    }

    /**
     * Verifies that the names and orbit radii for planets are correct.
     */
    @Test (timeout = TIMEOUT)
    public void testPlanetCreation() {
        Planet planet = new Planet("Thomas-Centauri", 13);
        assertEquals("Thomas-Centauri", planet.getName());
        assertEquals(13, planet.getOrbitRadius());
    }

    /**
     * Verifies that the testDist() method correct computes the distances
     * between planets.
     */
    @Test (timeout = TIMEOUT)
    public void testDist() {
        Planet p1 = new Planet("Justin", 7);
        Planet p2 = new Planet("Raj", 2);

        assertEquals(5, p1.getDist(p2));

        assertEquals(5, Planet.distBetween(p1, p2));
    }
}
