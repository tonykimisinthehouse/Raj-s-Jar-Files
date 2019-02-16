package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity;

/**
 * Builder pattern for the Player class.
 */
public class PlayerBuilder {
    private String name;
    private int[] points;
    private int credits;
    private Ship ship;

    /**
     * One arg constructor for the Player Builder pattern.
     * Gives other fields default values.
     *
     * @param name the player's name
     */
    public PlayerBuilder(String name) {
        this(name, new int[] {4, 4, 4, 4}, 1000, new Ship(ShipType.GNAT));
    }

    /**
     * Constructor with all arguments.
     *
     * @param name the player's name
     * @param points the player's points
     * @param credits the player's credits
     * @param ship the player's ship
     */
    public PlayerBuilder(String name, int[] points, int credits, Ship ship) {
        this.name = name;
        this.points = points;
        this.credits = credits;
        this.ship = ship;
    }

    /**
     * Sets the builder's points.
     *
     * @param points the new points
     * @return the builder object
     */
    public PlayerBuilder setPoints(int[] points) {
        this.points = points;
        return this;
    }

    /**
     * Sets the builder's credits.
     *
     * @param credits the new credits
     * @return the builder object
     */
    public PlayerBuilder setCredits(int credits) {
        this.credits = credits;
        return this;
    }

    /**
     * Sets the builder's ship.
     * @param ship the new ship
     * @return the builder object
     */
    public PlayerBuilder setShip(Ship ship) {
        this.ship = ship;
        return this;
    }

    /**
     * Builds the Player object.
     *
     * @return the new Player object
     */
    public Player build() {
        return new Player(name, points, credits, ship);
    }
}
