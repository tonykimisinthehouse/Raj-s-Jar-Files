package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import java.util.HashSet;
import java.util.Random;

public class SolarSystem {
    public static final int MIN_PLANETS = 1;
    public static final int MAX_PLANETS = 10;

    //using an array because the size won't change
    private Planet[] planets;

    public SolarSystem() {
        Random rand = new Random();
        planets = new Planet[rand.nextInt(
                        MAX_PLANETS - MIN_PLANETS + 1) + MIN_PLANETS];
        createSolarSystem();
    }

    public void createSolarSystem() {
        Random rand = new Random();
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
