package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity;

/**
 * Builder pattern for the Player class.
 */
public class PlayerBuilder {
    private String name;
    private int[] points;
    private int credits;
    private Ship ship;

    public PlayerBuilder(String name) {
        this(name, new int[] {4, 4, 4, 4}, 1000, new Ship(ShipType.GNAT));
    }

    public PlayerBuilder(String name, int[] points, int credits, Ship ship) {
        this.name = name;
        this.points = points;
        this.credits = credits;
        this.ship = ship;
    }

    public PlayerBuilder setPoints(int[] points) {
        this.points = points;
        return this;
    }

    public PlayerBuilder setCredits(int credits) {
        this.credits = credits;
        return this;
    }

    public PlayerBuilder setShip(Ship ship) {
        this.ship = ship;
        return this;
    }

    public Player build() {
        return new Player(name, points, credits, ship);
    }
}
