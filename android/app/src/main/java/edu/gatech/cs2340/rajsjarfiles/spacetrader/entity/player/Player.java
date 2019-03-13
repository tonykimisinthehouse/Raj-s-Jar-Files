package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Good;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Item;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction.TransactionOrder;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction.TransactionResult;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction.TransactionType;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Planet;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.model.Model;

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
    private Planet planet; // Planet that the player is currently on.

    /**
     * Player constructor with all arguments.
     *
     * @param builder the Player builder
     */
    public Player(PlayerBuilder builder) {
        setName(builder.name);
        setPoints(builder.points);
        setCredits(builder.credits);
        setShip(builder.ship);
        setPlanet(builder.planet);
    }

    /**
     * Setter for planet which the player is on.
     *
     * @param planet that the player is on.
     */
    public void setPlanet(Planet planet) {
        this.planet = planet;
        // tmp
        //this.planet = new Planet.PlanetBuilder("Raj", 3).build();
    }

    /**
     * Method that allows player to buy certain good.
     *
     * @param good good to buy
     * @param quantity quantity of good to buy
     * @return true if the transaction was successful.
     */
    public boolean makePurchase(Good good, int quantity) {
        // Create new Transaction Order
        TransactionOrder newTransactionOrder = new TransactionOrder(
                good,
                quantity,
                this,
                TransactionType.BUY);

        // Get Transaction order
        TransactionResult newTransactionResult = planet.getMarketplace()
                .validateTransaction(newTransactionOrder);

        // Use credit, Get Good to cargo based on the transaction result (success, fail)
        if (newTransactionResult.getisTransactionSuccess()) {
            Item item = newTransactionResult.getItem();
            // Add good to the cargo
            ship.addGood(item);
            // Use credits
            useCredits(item.getPrice() * item.getQuantity());
        }

        // Return if the transaction is success or not.
        return newTransactionResult.getisTransactionSuccess();
    }

    /**
     *  Method that allows player to sell certain good.
     *
     * @param good good to sell
     * @param quantity quantity of good to sell
     * @return true if the transaction was successful
     */
    public boolean makeSales(Good good, int quantity) {
        // Create new transaction.
        TransactionOrder newTransactionOrder = new TransactionOrder(
                good,
                quantity,
                this,
                TransactionType.SELL);

        // Get transaction result.
        TransactionResult newTransactionResult = planet.getMarketplace()
                .validateTransaction(newTransactionOrder);

        // Remove cargo, and earn credit based on the transaction result
        if (newTransactionResult.getisTransactionSuccess()) {
            Item item = newTransactionResult.getItem();
            // Sell goods from cargo.
            ship.sellGood(item);
            // Earn credits.
            earnCredits(item.getPrice() * item.getQuantity());
        }
        return newTransactionResult.getisTransactionSuccess();
    }

    /**
     * Earn credit
     *
     * @param amount of credit earned.
     */
    private void earnCredits(int amount) {
        credits += amount;
    }

    /**
     * Use credit
     *
     * @param amount of credit earned.
     */
    private void useCredits(int amount) {
        credits -= amount;
    }

    /**
     * Makes sure that the user cannot buy more goods than you have money
     *
     * @param marketPrice the price the market is selling at
     * @return boolean of whether the user has enough credit
     */
    public Boolean checkCreditEnough(int marketPrice) {
        if (marketPrice > credits ) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Makes sure the user cannot buy more goods than the cargo capacity
     *
     * @param quantity the number of items the user wants to buy
     * @return boolean of whether the user has enough cargo capacity
     */
    public Boolean checkCargoCapacityEnough(int quantity) {
        if (quantity > ship.getAvailableCargoCapacity()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
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

    /**
     * @return The planet this player is currently on
     */
    public Planet getPlanet() {
        return this.planet;
    }

    @Override
    public String toString() {
        return "The player's name is " + getName() + " with stats:\n"
                + " - Pilot: " + getPilot() + "\n"
                + " - Engineer: " + getEngineer() + "\n"
                + " - Trade: " + getTrade() + "\n"
                + " - Fight: " + getFight() + "\n"
                + "They also have " + getCredits() + " credits and they fly a "
                + getShip().toString();
    }

    /**
     * Builder for building player object
     */
    public static class PlayerBuilder {

        private final String name;
        private int[] points;
        private int credits;
        private Ship ship;
        private Planet planet;

        /**
         * One arg constructor for the Player Builder pattern.
         * Gives other fields default values.
         *
         * @param name the player's name
         */
        public PlayerBuilder(String name) {
            this.name = name;
            this.points = new int[] {4,4,4,4};
            this.credits = 1000;
            this.ship = new Ship(ShipType.GNAT);
            this.planet = Model.current.getGame().getUniverse().getRandomSolarSystem().getRandomPlanet();
        }

        /**
         * Sets the builder's points.
         *
         * @param points the new points
         * @return the builder object
         */
        public PlayerBuilder points(int[] points) {
            this.points = points;
            return this;
        }

        /**
         * Sets the builder's credits.
         *
         * @param credits the new credits
         * @return the builder object
         */
        public PlayerBuilder credits(int credits) {
            this.credits = credits;
            return this;
        }

        /**
         * Sets the builder's ship.
         * @param ship the new ship
         * @return the builder object
         */
        public PlayerBuilder ship(Ship ship) {
            this.ship = ship;
            return this;
        }

        /**
         * Sets the builder's planet.
         * @param planet planet
         * @return the builder object
         */
        public PlayerBuilder planet(Planet planet) {
            this.planet = planet;
            return this;
        }

        /**
         * Builds the Player object.
         *
         * @return the new Player object
         */
        public Player build() {
            return new Player(this);
        }
    }
}
