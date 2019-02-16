package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity;

/**
 * Represents the user's player.
 *
 * Uses the builder pattern.
 */
public class Player {
    private static final int MAX_POINTS = 16;

    private String name;

    /**
     * 0 is pilot
     * 1 is engineer
     * 2 is trade
     * 3 is fight
     */
    private int[] points;
    private int credits;

    private Ship ship;

    /**
     * Player constructor with all arguments.
     *
     * @param name the player's name
     * @param points the player's points
     * @param credits the player's credits
     * @param ship the player's ship
     */
    public Player(String name, int[] points, int credits, Ship ship) {
        this.name = name;
        setPoints(points); //may be invalid so use the method
        this.credits = credits;
        this.ship = ship;
    }

    /**
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the player's name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the player's points
     */
    public int[] getPoints() {
        return points;
    }

    /**
     * @return the player's pilot points
     */
    public int getPilot() {
        return points[0];
    }

    /**
     * @return the player's engineer points
     */
    public int getEngineer() {
        return points[1];
    }

    /**
     * @return the player's trade points
     */
    public int getTrade() {
        return points[2];
    }

    /**
     * @return the player's fight points
     */
    public int getFight() {
        return points[3];
    }

    /**
     * Sets the player's points to a new set of points.
     *
     * @param points the new points
     * @throws java.lang.IllegalArgumentException if points are invalid
     */
    public void setPoints(int[] points) {
        //check if sum is 16 or in the future, less than 16
        int sum = 0;
        for (int i = 0; i < points.length; i++) {
            if (points[i] < 0) {
                throw new IllegalArgumentException(
                        "Point at index " + i + " is less than zero.");
            }
            sum += points[i];
        }

        if (sum != MAX_POINTS) {
            throw new IllegalArgumentException(
                    "Points do not sum to " + MAX_POINTS + ".");
        }
        this.points = points;
    }

    /**
     * @return the player's credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Sets the player's credits.
     *
     * @param credits the new credits
     * @throws java.lang.IllegalArgumentException if credits are negative
     */
    public void setCredits(int credits) {
        if (credits < 0) {
            throw new IllegalArgumentException(
                    "Cannot set credits to a negative number.");
        }
        this.credits = credits;
    }

    /**
     * @return the player's ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Sets the player's ship to a new ship.
     *
     * @param ship the new ship
     * @throws java.lang.IllegalArgumentException when ship is null
     */
    public void setShip(Ship ship) {
        if (ship == null) {
            throw new IllegalArgumentException(
                    "Cannot set ship to null with this method. Use setNoShip()."
            );
        }
        this.ship = ship;
    }

    /**
     * Sets ship to null if a player has no ship.
     */
    public void setNoShip() {
        this.ship = null;
    }
}
