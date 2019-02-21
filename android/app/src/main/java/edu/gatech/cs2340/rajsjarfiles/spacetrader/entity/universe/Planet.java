package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import java.util.Random;

public class Planet {
    private static final int MIN_RADIUS = 1;        //these are relative
    private static final int MAX_RADIUS = 5;

    private String name;
    private int radius;         //radius of planet itself
    private int orbitRadius;    //distance from center

    public Planet(String name, int radius, int orbitRadius) {
        this.name = name;
        this.radius = radius;
        this.orbitRadius = orbitRadius;
    }

    public String getName() {
        return name;
    }

    public int getRadius() {
        return radius;
    }

    public int getOrbitRadius() {
        return orbitRadius;
    }

    public int getDist(Planet other) {
        return Math.abs(this.orbitRadius - other.orbitRadius);
    }

    private static Random rand = new Random();

    public static int getRandomRadius() {
        return rand.nextInt(
                MAX_RADIUS - MIN_RADIUS + 1)
                + MIN_RADIUS;
    }

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
