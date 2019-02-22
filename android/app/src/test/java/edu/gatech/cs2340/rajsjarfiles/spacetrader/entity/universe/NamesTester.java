package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import org.junit.Test;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Names;

/**
 * Tests the Names class.
 */
public class NamesTester {
    private static final int TIMEOUT = 200;

    /**
     * Prints randomly generated names.
     */
    @Test (timeout = TIMEOUT)
    public void testGenNames() {
        for (int i = 0; i < 10000; i++) {
//            System.out.println(Names.generateName());
            Names.generateName();
        }
    }
}
