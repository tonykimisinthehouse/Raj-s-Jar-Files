package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.planet;

import java.util.HashSet;
import java.util.Random;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Names;


/**
 * Represents a planet within a solar system.
 */
public class Planet {
    private String name;
    private int radius;         //radius of planet itself
    private int orbitRadius;    //distance from center

    private static final int MIN_RADIUS = 1;        //these are relative
    private static final int MAX_RADIUS = 5;

    private TechLevel techLevel;
    private Habitats habitats;
    private Species species;
    private ResourceClassification resourceClass;

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

        this.techLevel = TechLevel.getRandomTechLevel();
        this.habitats = Habitats.getRandomHabitat();
        this.resourceClass =
                ResourceClassification.getRandomResourceClass(this.habitats);
        this.species = this.habitats.getRandomHabitableSpecie();
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
     * @return the planet's tech level
     */
    public TechLevel getTechLevel() {
        return techLevel;
    }

    /**
     * @return the planet's resource classification
     */
    public ResourceClassification getResourceClass() {
        return resourceClass;
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

    @Override
    public String toString() {
        return String.format("%-16s", name)
                + "| Radius: " + radius
                + String.format(", orbit radius: %2d", orbitRadius)
                + String.format(" | tech level: %-16s", techLevel.toString())
                + " | resource class: " + resourceClass.toString()
                + " | species: " + species.toString()
                + " | habitat: " + habitats.toString()
                + ".";
    }

    //static methods

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

    /**
     * Returns an array of random planets given a size.
     *
     * @param size the number of planets to generate
     * @return the array of planets
     */
    public static Planet[] generatePlanets(int size) {
        Planet[] planets = new Planet[size];

        HashSet<String> nameSet = new HashSet<>();
        int orbitRadius = 0;
        for (int i = 0; i < planets.length; i++) {
            orbitRadius += rand.nextInt(2) + 1;
            String name = Names.generateName();
            while (!nameSet.add(name)) {
                name = Names.generateName();
            }
            planets[i] = new Planet(name, orbitRadius);
        }

        return planets;
    }
}
