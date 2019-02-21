package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import org.junit.Test;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Names;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.SolarSystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SolarSystemTester {
    private static final int TIMEOUT = 200;

    @Test
    public void testSolarSystemGeneration() {
        for (int i = 0; i < 100; i++) {
            SolarSystem s = new SolarSystem();
            System.out.println(s.toString());
        }
    }

    @Test (timeout = TIMEOUT)
    public void testPlanetCreation() {
        Planet planet = new Planet("Thomas-Centauri", 13);
        assertEquals("Thomas-Centauri", planet.getName());
        assertEquals(13, planet.getOrbitRadius());
    }

    @Test (timeout = TIMEOUT)
    public void testDist() {
        Planet p1 = new Planet("Justin", 7);
        Planet p2 = new Planet("Raj", 2);

        assertEquals(5, p1.getDist(p2));

        assertEquals(5, Planet.distBetween(p1, p2));
    }
}
