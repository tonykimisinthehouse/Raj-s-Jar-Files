package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

/**
 * Represents the entire universe of the game.
 */
public class Universe {
    private static final int MAX_SOLAR_SYSTEMS = 20;

    //using an array because the size won't change
    private final SolarSystem[] solarSystem;

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
            Collection<String> nameSet = new HashSet<>();
            Collection<Coordinate> coordSet = new HashSet<>();
            String name = PlanetNames.generateName();
            Coordinate coordinate = new Coordinate();
            while (!nameSet.add(name)) {
                name = PlanetNames.generateName();
            }
            while (!coordSet.add(coordinate)) {
                coordinate = new Coordinate();
            }
            solarSystem[i] = new SolarSystem(name, coordinate);
        }
    }

    /**
     * @return a random solar system in the universe
     */
    public SolarSystem getRandomSolarSystem() {
        Random rand = new Random();
        return solarSystem[rand.nextInt(solarSystem.length)];
    }

    @Override
    public String toString() {
        String ret = "The universe:\n";
        for (SolarSystem aSolarSystem : solarSystem) {
            ret += "- " + aSolarSystem.toString() + "\n";
        }
        return ret;
    }
}
