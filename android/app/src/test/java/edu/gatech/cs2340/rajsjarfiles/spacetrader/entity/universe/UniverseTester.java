package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import org.junit.Test;

/**
 * Tests the Universe class.
 */
public class UniverseTester {

    /**
     * Creates a universe and prints everything in it.
     */
    @Test
    public void testUniverseCreation() {
        Universe uni = new Universe();
        System.out.println(uni.toString());
    }
}
