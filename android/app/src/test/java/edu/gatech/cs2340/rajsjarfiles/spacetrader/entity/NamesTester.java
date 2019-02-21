package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity;

import org.junit.Test;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Names;

public class NamesTester {
    private static final int TIMEOUT = 200;

    @Test (timeout = TIMEOUT)
    public void testGenNames() {
        for (int i = 0; i < 10000; i++) {
//            System.out.println(Names.generateName());
            Names.generateName();
        }
    }
}
