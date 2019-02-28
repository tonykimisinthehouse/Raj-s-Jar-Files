package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import java.util.Random;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.planet.Planet;

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
     *
     * @param name the system's name
     * @param coordinate the system's coordinate in the universe
     */
    public SolarSystem(String name, Coordinate coordinate) {
        Random rand = new Random();
        this.name = name;
        this.coordinate = coordinate;
        planets = Planet.generatePlanets(rand.nextInt(
                MAX_PLANETS - MIN_PLANETS + 1) + MIN_PLANETS);
    }

    /**
     * @return the name of the solar system
     */
    public String getName() {
        return name;
    }

    /**
     * @return the coordinate of this solar system
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
        String ret = "";
        ret += String.format("%-12s", getName()) + " " + getCoordinate() + " " + "\n";
        for (int i = 0; i < planets.length; i++) {
            ret += "   - " + planets[i].toString() + "\n";
        }
        return ret;
    }
}
