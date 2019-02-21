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
        //generate all solar systems with no duplicate names
        //see solar system generating planets for example
    }

    @Override
    public String toString() {
        String ret = "The universe:\n";
        for (int i = 0; i < solarSystem.length; i++) {
            ret += "- " + solarSystem[i].toString() + "\n";
        }
        return ret;
    }
}
