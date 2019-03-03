package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player;

import java.util.HashMap;
import java.util.HashSet;

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

    public int getCargoCapacity() {
        return totalCap;
    }

    public void addToCargo(Good good, int quantity) {
        if (cargo.containsKey(good)) {
            cargo.put(good, cargo.get(good) + quantity);
        } else {
            cargo.put(good, quantity);
        }
    }

    public boolean hasGoods(Good good, int quantity) {
        if (cargo.containsKey(good)) {
            if (cargo.get(good) >= quantity) {
                return true;
            }
        }
        return false;
    }

    public int getAvailableCargoCapacity() {
        return totalCap - usedCap;
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
