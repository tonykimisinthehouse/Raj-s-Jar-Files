package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player;

import java.util.Collection;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Item;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Planet;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.SolarSystem;

/**
 * Represents the user's player.
 *
 * Uses the builder pattern.
 */
public class Player {
    private static final int MAX_POINTS = 16;
    private static final int START_CREDITS = 9999;

    private String name;

    /**
     * 0 is pilot
     * 1 is engineer
     * 2 is trade
     * 3 is fight
     */
    private int[] points;

    private Ship ship;
    private Wallet wallet;
    private Location location;

    ///////////////////////////// CONSTRUCTORS /////////////////////////////
    /**
     * Player constructor with all arguments.
     *
     * @param builder the Player builder
     */
    public Player(PlayerBuilder builder) {
        setName(builder.name);
        setPoints(builder.points);
        setWallet(builder.wallet);
        setShip(builder.ship);
        setLocation(builder.location);
    }

    ///////////////////////////// SHIP OPERATION /////////////////////////////
    /**
     * Makes sure the user cannot buy more goods than the cargo capacity
     *
     * @param quantity the number of items the user wants to buy
     * @return boolean of whether the user has enough cargo capacity
     */
    public Boolean checkCargoCapacityEnough(int quantity) {
        return quantity <= ship.getAvailableCargoCapacity();
    }

    /**
     * @return the player's ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * @return the ship's health
     */
    public int getHealth() {
        return ship.getHealth();
    }

    public Collection<Item> getCargoGoods() {
        return ship.getCargoGoods();
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

    ///////////////////////////// WALLET OPERATION /////////////////////////////

    /**
     * @return the player's credits
     */
    public int getCredits() {
        return wallet.getCredits();
    }

    /**
     * Set the player's credits to a new value.
     *
     * @param credits the new credits
     */
    public void setCredits(int credits) {
        try {
            wallet.setCredits(credits);
        } catch (IllegalArgumentException e) {
            wallet.setCredits(0);
        }
    }

    /**
     * Set the player's credits to a new amount based on the
     * ratio left.
     *
     * @param takenRatio the ratio of money to remove
     * @return the new credits
     */
    public int setCredits(float takenRatio) {
        return wallet.setCredits(takenRatio);
    }

    /**
     * @return the player's wallet
     */
    public Wallet getWallet() {
        return this.wallet;
    }

    /**
     * Sets the player's wallet to a new wallet.
     *
     * @param wallet the new wallet
     */
    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
        this.wallet.setOwner(this);
    }

    ///////////////////////////// LOCATION OPERATION ///////////////////////////

    /**
     * Set the player's location to a new location.
     *
     * @param location the new location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return the player's current location
     */
    public Location getLocation() {
        return this.location;
    }

    public Planet getPlanet() {
        return location.getPlanet();
    }

    /**
     * Determines if the player can travel to a new destination and if so,
     * moves the player to the new destination.
     *
     * @param destinationSS the destination solar system
     * @param destinationP the destination planet
     * @return if the travel was successful
     */
    public boolean travel(SolarSystem destinationSS, Planet destinationP) {

        final int statusCODE = this.location.checkIfTravelPossible(
               destinationSS, destinationP);

        // Travel not possible since the player is not in the warp zone planet.
        if (statusCODE == -1) {
            return false;
        }

        // Inter solar system travel. (travel between solar system)
        if (statusCODE == 1) {
            final int travelFare = 1000;
            if (this.wallet.checkCreditEnough(travelFare)) {
                //TODO STUB PRICE FOR INTER SOLAR TRAVEL
                this.wallet.useCredits(travelFare);
                this.location = new Location(
                        destinationSS, destinationSS.getPlanetWithWarp());
                return true;
            }
        }

        // Travel inside the solar system
        if (statusCODE == 0) {
            int fuelRequired = this.location.calculateFuelRq(destinationP);
            if (this.ship.hasFuels(fuelRequired)) {
                this.ship.subFuel(fuelRequired);
                this.location = new Location(destinationSS, destinationP);
                return true;
            }
        }
        return false;
    }

    ///////////////////////////// PLAYER ATTRIBUTES ///////////////////////////
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

    @Override
    public String toString() {
        return "The player's name is " + getName() + " with stats:\n"
                + " - Pilot: " + getPilot() + "\n"
                + " - Engineer: " + getEngineer() + "\n"
                + " - Trade: " + getTrade() + "\n"
                + " - Fight: " + getFight() + "\n"
                + "They also have "
                + wallet.getCredits() + " credits and they fly a "
                + getShip().toString();
    }

    /**
     * Builder for building player object
     */
    public static class PlayerBuilder {

        private final String name;
        private int[] points;
        private Ship ship;
        private Wallet wallet;
        private Location location;

        /**
         * One arg constructor for the Player Builder pattern.
         * Gives other fields default values.
         *
         * @param name the player's name
         */
        public PlayerBuilder(String name) {
            // Default Values
            this.name = name;
            this.points = new int[] {4, 4, 4, 4};
            this.wallet = new Wallet(START_CREDITS); //TODO STUB CREDIT AMOUNT
            this.ship = new Ship(ShipType.GNAT);
            this.location = null;
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
         * Sets the builder's location.
         *
         * @param ss the solar system
         * @return the builder object
         */
        public PlayerBuilder location(SolarSystem ss) {
            this.location = new Location(ss);
            return this;
        }

        /**
         * Sets the builder's credits.
         *
         * @param credits the new credits
         * @return the builder object
         */
        public PlayerBuilder credits(int credits) {
            this.wallet.setCredits(credits);
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
            if (location == null) {
                location = new Location();
            }
            return new Player(this);
        }
    }
}
