package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import java.util.HashSet;

/**
 * Represents the entire universe of the game.
 */
public class Universe {
    private static final int MAX_SOLAR_SYSTEMS = 20;

    //using an array because the size won't change
    private SolarSystem[] solarSystem;

    /**
     * Constructor to create the universe.
     */
    public Universe() {
        solarSystem = new SolarSystem[MAX_SOLAR_SYSTEMS];
        createUniverse();
    }

    /**
     * Method that creates a universe with multiple solar systems with unique
     * names and coordinates.
     */
    private void createUniverse() {
        for (int i = 0; i < solarSystem.length; i++) {
            HashSet<String> nameSet = new HashSet<>();
            HashSet<Coordinate> coordSet = new HashSet<>();
            String name = Names.generateName();
            Coordinate coordinate = new Coordinate();
            while (!nameSet.add(name)) {
                name = Names.generateName();
            }
            while (!coordSet.add(coordinate)) {
                coordinate = new Coordinate();
            }
            solarSystem[i] = new SolarSystem(name, coordinate);
        }
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
