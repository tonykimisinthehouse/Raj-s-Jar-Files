package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player;

import java.util.HashMap;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Good;

/**
 * Represents a ship that a character or enemy can use.
 */
public class Ship {
    private ShipType shipType;
    private HashMap<Good,Integer> cargo;

    private int totalCap;
    private int usedCap;

    /**
     * Default constructor that sets ShipType to GNAT.
     */
    public Ship() {
        this(10,ShipType.GNAT);
    }

    /**
     * One arg constructor for creating a ship.
     *
     * @param shipType the ship type
     */
    public Ship(ShipType shipType) {
        this(10,shipType);
    }

    /**
     * Two arg constructor for creating a ship.
     *
     * @param shipType the ship type
     * @param cargoSize the size of the cargo
     */
    public Ship(int cargoSize, ShipType shipType) {
        this.cargo = new HashMap<>(cargoSize);
        this.shipType = shipType;
        totalCap =  cargoSize;
        usedCap = 0;
    }

    /**
     * Get total cargo capacity of the ship.
     *
     * @return total cargo capacity
     */
    public int getCargoCapacity() {
        return totalCap;
    }

    /**
     * Get available cargo capacity of the ship.
     *
     * @return available cargo capacity
     */
    public int getAvailableCargoCapacity() {
        return totalCap - usedCap;
    }

    /**
     * Add quantity of goods to the cargo.
     *
     * @param good good to add.
     * @param quantity quantity of good to buy.
     */
    public void addGood(Good good, int quantity) {
        if (getAvailableCargoCapacity() < quantity) {
            throw new java.lang.IllegalArgumentException("Not enough cargo");
        }
        if (cargo.containsKey(good)) {
            cargo.put(good, cargo.get(good) + quantity);
        } else {
            cargo.put(good, quantity);
        }
        usedCap += quantity;
    }

    /**
     * Sell quantity of goods from the cargo.
     *
     * @param good good to sell.
     * @param quantity quantity of good to sell.
     */
    public void sellGood(Good good, int quantity) {
        cargo.put(good,cargo.get(good) - quantity);
        usedCap -= quantity;
    }

    public boolean hasGoods(Good good, int quantity) {
        if (cargo.containsKey(good)) {
            if (cargo.get(good) >= quantity) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the ship type
     */
    public ShipType getShipType() {
        return shipType;
    }

    /**
     * Sets the ship type to a new ship type.
     *
     * @param shipType the new ship type
     */
    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    @Override
    public boolean equals(Object that) {
        if (that == this) {
            return true;
        }
        if (!(that instanceof Ship)) {
            return false;
        }

        Ship s = (Ship) that;

        return this.shipType == s.shipType;
    }

    @Override
    public String toString() {
        return shipType.toString();
    }
}
