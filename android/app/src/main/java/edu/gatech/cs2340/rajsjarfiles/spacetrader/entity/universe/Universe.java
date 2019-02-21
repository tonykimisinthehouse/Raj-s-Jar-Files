package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

public class Universe {
    private static final int MAX_SOLAR_SYSTEMS = 20;

    //using an array because the size won't change
    private SolarSystem[] solarSystem;

    public Universe() {
        solarSystem = new SolarSystem[MAX_SOLAR_SYSTEMS];
        createUniverse();
    }

    public void createUniverse() {

    }
}
