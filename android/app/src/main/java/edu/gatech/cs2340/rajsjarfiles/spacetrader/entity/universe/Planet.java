package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import java.util.Random;

/**
 * Represents a planet within a solar system.
 */
public class Planet {
    private static final int MIN_RADIUS = 1;        //these are relative
    private static final int MAX_RADIUS = 5;

    private String name;
    private int radius;         //radius of planet itself
    private int orbitRadius;    //distance from center

    /**
     * Creates a planet with a given name, radius, and orbit radius (how far
     * it is from the center of the solar system).
     *
     * @param name the name of the planet
     * @param orbitRadius the orbit radius of the planet
     */
    public Planet(String name, int orbitRadius) {
        this.name = name;
        this.radius = getRandomRadius();
        this.orbitRadius = orbitRadius;
    }

    //no setters because the fields shouldn't change

    /**
     * @return the planet's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the planet's radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * @return the planet's orbit radius
     */
    public int getOrbitRadius() {
        return orbitRadius;
    }

    /**
     * Returns the "distance" in terms of orbit radius to another planet.
     *
     * @param other the other planet
     * @return the difference in orbit radius
     */
    public int getDist(Planet other) {
        return Math.abs(this.orbitRadius - other.orbitRadius);
    }

    private static Random rand = new Random();

    /**
     * Generates a random radius given the bounds.
     *
     * @return a random radius
     */
    private static int getRandomRadius() {
        return rand.nextInt(
                MAX_RADIUS - MIN_RADIUS + 1)
                + MIN_RADIUS;
    }

    /**
     * Returns the distance between two given planets.
     *
     * @param p1 the first planet
     * @param p2 the second planet
     * @return the distance between the two planets
     */
    public static int distBetween(Planet p1, Planet p2) {
        return Math.abs(p1.getOrbitRadius() - p2.getOrbitRadius());
    }

    @Override
    public String toString() {
        return name + ": Radius "
                + radius + ", orbit radius: "
                + orbitRadius + ".";
    }
}
