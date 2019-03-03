package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import android.util.Log;

import java.util.Random;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Marketplace;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.utility.LogCustom;


/**
 * Represents a planet within a solar system.
 */
public class Planet {
    private String name;
    private int radius;         //radius of planet itself
    private int orbitRadius;    //distance from center

    private TechLevel techLevel;
    private Habitats habitats;
    private Species species;
    private ResourceClassification resourceClass;
    private Marketplace marketplace;

    public Planet(PlanetBuilder builder) {
        this.name = builder.name;
        this.radius = builder.radius;
        this.orbitRadius = builder.orbitRadius;
        this.techLevel = builder.techLevel;
        this.habitats = builder.habitats;
        this.species = builder.species;
        this.resourceClass = builder.resourceClass;
        this.marketplace = builder.marketplace;
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
        LogCustom.largeLog("Market", marketplace.toString());
        return String.format("%-16s", name)
                + "| Radius: " + radius
                + String.format(", orbit radius: %2d", orbitRadius)
                + String.format(" | tech level: %-20s", techLevel.toString())
                + String.format(" | resource class: %-20s",resourceClass.toString())
                + String.format(" | species: %-10s", species.toString())
                + String.format(" | habitat: %s", habitats.toString())
                + ".";
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
        Random rand = new Random();
        String[] nameList = PlanetNames.generateName(planets.length);

        int orbitRadius = 0;

        for (int i = 0; i < nameList.length; i++) {
            orbitRadius += rand.nextInt(2) + 1;
            planets[i] = new PlanetBuilder(nameList[i], orbitRadius).build();
        }
        return planets;
    }

    /**
     * Nested PlanetBuilder class
     */
    public static class PlanetBuilder {

        private static final int MIN_RADIUS = 1;
        private static final int MAX_RADIUS = 5;

        private final String name;
        private int radius;
        private final int orbitRadius;
        private TechLevel techLevel;
        private Habitats habitats;
        private Species species;
        private ResourceClassification resourceClass;
        private Marketplace marketplace;
        private Events event;

        /**
         * Creates a planet with a given name, radius, and orbit radius (how far
         * it is from the center of the solar system).
         *
         * @param name the name of the planet
         * @param orbitRadius the orbit radius of the planet
         */
        public PlanetBuilder(String name, int orbitRadius) {
            this.name = name;
            this.orbitRadius = orbitRadius;
            this.radius = getRandomRadius();
            this.techLevel = TechLevel.getRandomTechLevel();
            this.habitats = Habitats.getRandomHabitat();
            this.resourceClass = ResourceClassification.getRandomResourceClass(this.habitats);
            this.species = Species.getRandomHabitableSpecies(this.habitats);
            this.event = Events.getRandomEvent();
            this.marketplace = new Marketplace(
                    this.name,
                    this.techLevel,
                    this.event,
                    this.habitats,
                    this.resourceClass,
                    this.species
            );
        }

        /**
         * One arg radius setter for builder class
         *
         * @param r radius to set
         * @return builder
         */
        public PlanetBuilder radius(int r) {
            this.radius = r;
            return this;
        }

        /**
         * One arg tech level setter for builder class
         *
         * @param tl tech level to set
         * @return builder
         */
        public PlanetBuilder techLevel(TechLevel tl) {
            this.techLevel = tl;
            return this;
        }

        /**
         * One arg habitat setter for builder class
         *
         * @param ha habitat to set
         * @return builder
         */
        public PlanetBuilder habitats(Habitats ha) {
            this.habitats = ha;
            return this;
        }

        /**
         * One arg species setter for builder class
         *
         * @param sp species to set
         * @return builder
         */
        public PlanetBuilder species(Species sp) {
            this.species = sp;
            return this;
        }

        /**
         * One arg resource class setter for builder class
         *
         * @param rc resource to set
         * @return builder
         */
        public PlanetBuilder resourceClass(ResourceClassification rc) {
            this.resourceClass = rc;
            return this;
        }

        /**
         * Builder for builder class
         *
         * @return planet
         */
        public Planet build() {
            return new Planet(this);
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
    }
}
