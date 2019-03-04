package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Good;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Item;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.TradeGoods;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction.TransactionOrder;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction.TransactionResult;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.transaction.TransactionType;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Planet;

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
    }

    private boolean makePurchase(Good good, int quantity) {
        TransactionOrder newTransactionOrder = new TransactionOrder(
                good,
                quantity,
                this,
                TransactionType.BUY);

        TransactionResult newTransactionResult = planet.getMarketplace()
                .validateTransaction(newTransactionOrder);

        if (newTransactionResult.getisTransactionSuccess()) {
            Item item = newTransactionResult.getItem();
            ship.addGood(item.getGood(),
                    item.getQuantity());
            useCredits(item.getPrice() * item.getQuantity());
        }

        return newTransactionResult.getisTransactionSuccess();

    }

    public boolean makeSales(TradeGoods good, int quantity) {
        TransactionOrder newTransactionOrder = new TransactionOrder(
                good,
                quantity,
                this,
                TransactionType.SELL);

        TransactionResult newTransactionResult = planet.getMarketplace()
                .validateTransaction(newTransactionOrder);

        if (newTransactionResult.getisTransactionSuccess()) {
            Item item = newTransactionResult.getItem();
            ship.sellGood(item.getGood(),
                    item.getQuantity());
            earnCredits(item.getPrice() * item.getQuantity());
        }

        return newTransactionResult.getisTransactionSuccess();
    }

    /**
     * Earn credit
     * @param amount of credit earned.
     */
    private void earnCredits(int amount) {
        credits += amount;
    }

    /**
     * Use credit
     * @param amount of credit earned.
     */
    private void useCredits(int amount) {
        credits -= amount;
    }

    /**
     * Makes sure that the user cannot buy more goods than you have money
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

    public static class PlayerBuilder {

        private final String name;
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
            this.name = name;
            this.points = new int[] {4,4,4,4};
            this.credits = 1000;
            this.ship = new Ship(ShipType.GNAT);
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
         * Builds the Player object.
         *
         * @return the new Player object
         */
        public Player build() {
            return new Player(this);
        }
    }
}
