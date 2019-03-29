package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import android.util.Log;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Random;

/**
 * Represents a solar system in the universe.
 */
public class SolarSystem {
    public static final int MIN_PLANETS = 1;
    public static final int MAX_PLANETS = 10;

    static Random rand = new Random();

    //using an array because the size won't change
    private Planet[] planets;
    private HashMap<String, Planet> planetMap;
    private String name;
    private Coordinate coordinate;

    /**
     * Constructor to create a solar system.
     *
     * @param name the system's name
     * @param coordinate the system's coordinate in the universe
     */
    public SolarSystem(String name, Coordinate coordinate) {
        this.name = name;
        this.coordinate = coordinate;
        this.planets = generatePlanets(rand.nextInt(
                MAX_PLANETS - MIN_PLANETS + 1) + MIN_PLANETS);
        this.planetMap = new HashMap<>();
        this.computePlanetMap();
        assignWarpZone();
    }

    private void assignWarpZone() {
        int maxRadius = Integer.MIN_VALUE;
        Planet maxPlanet = planets[0];
        for(Planet planet : planets) {
            if (planet.getRadius() > maxRadius) {
                maxRadius = planet.getRadius();
                maxPlanet = planet;
            }
        }
        maxPlanet.setIsWarpZone(true);
    }

    /**
     * Returns an array of random planets given a size.
     *
     * @param size the number of planets to generate
     * @return the array of planets
     */
    public static Planet[] generatePlanets(int size) {

        Planet[] planets = new Planet[size];
        String[] nameList = PlanetNames.generateName(planets.length);

        int orbitRadius = 0, orbitAngle = 0;
        for (int i = 0; i < nameList.length; i++) {
            orbitRadius += rand.nextInt(2) + 1; // Assign orbit radius
            orbitAngle = rand.nextInt(361);
            planets[i] = new Planet.PlanetBuilder(nameList[i], orbitRadius, orbitAngle).build();
        }
        return planets;
    }

    /**
     * Adds each Planet to the "planetsMap" hash map
     * with key/value pair: String (name), Planet
     */
    private void computePlanetMap() {
        for (Planet planet : this.planets) {
            Log.d("Raj", "Adding " + planet.getName());
            this.planetMap.put(planet.getName(), planet);
        }
    }

    /**
     * Returns the planet with the specified name
     * in this Solar System.
     * @param name the name of the planet to fetch
     * @return the planet
     */
    public Planet getPlanetByName(String name) {
        return this.planetMap.get(name);
    }

    /**
     * Returns an array of all planets in this Solar System.
     * @return the array of planets
     */
    public Planet[] getPlanets() {
        return this.planets;
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

    /**
     * Get random planet from the solar system
     *
     * @return random planet
     */
    public Planet getRandomPlanet() {
        Random rand = new Random();
        return planets[rand.nextInt(planets.length)];
    }

    public Planet getPlanetWithWarp() {
        for (Planet planet : planets) {
            if (planet.getIsWarpZone()) {
                return planet;
            }
        }
        return planets[0];
    }

    @Override
    public String toString() {
        String ret = "";
        ret += String.format("%-12s", getName())
                + " " + getCoordinate() + " " + "\n";
        for (int i = 0; i < planets.length; i++) {
            ret += "   - " + planets[i].toString() + "\n";
        }
        return ret;
    }

    @Override
    public boolean equals(Object that) {
        if (that == this) {
            return true;
        }
        if (!(that instanceof Coordinate)) {
            return false;
        }
        SolarSystem ss = (SolarSystem) that;
        return this.name == ss.name
                && this.getCoordinate().equals(ss.getCoordinate());
    }

}
