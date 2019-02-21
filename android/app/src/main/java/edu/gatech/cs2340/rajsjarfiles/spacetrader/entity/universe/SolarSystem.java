package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import java.util.Random;

/**
 * Represents a solar system in the universe.
 */
public class SolarSystem {
    public static final int MIN_PLANETS = 1;
    public static final int MAX_PLANETS = 10;

    //using an array because the size won't change
    private Planet[] planets;
    private String name;
    private Coordinate coordinate;

    /**
     * Constructor to create a solar system.
     */
    public SolarSystem(String name, Coordinate coordinate) {
        Random rand = new Random();
        this.name = name;
        this.coordinate = coordinate;
        planets = Planet.generatePlanets(rand.nextInt(
                MAX_PLANETS - MIN_PLANETS + 1) + MIN_PLANETS);
    }

    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < planets.length; i++) {
            ret += "   - " + planets[i].toString() + "\n";
        }
        return ret;
    }
}
