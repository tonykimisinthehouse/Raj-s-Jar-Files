package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity;

import org.junit.Test;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Names;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.SolarSystem;

public class SolarSystemTester {
    private static final int TIMEOUT = 200;

    @Test (timeout = TIMEOUT)
    public void testSolarSystemGeneration() {
        for (int i = 0; i < 1000; i++) {
            SolarSystem s = new SolarSystem();
            System.out.println(s.toString());
        }
    }
}
