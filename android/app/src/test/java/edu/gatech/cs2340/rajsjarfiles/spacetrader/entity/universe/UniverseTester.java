package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import org.junit.Test;

public class UniverseTester {

    @Test
    public void testUniverseCreation() {
        Universe uni = new Universe();
        System.out.println(uni.toString());
    }
}
