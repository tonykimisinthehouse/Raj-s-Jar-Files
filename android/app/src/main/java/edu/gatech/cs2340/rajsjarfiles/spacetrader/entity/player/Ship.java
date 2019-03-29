package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Good;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Item;

/**
 * Represents a ship that a character or enemy can use.
 */
public class Ship {
    private ShipType shipType;
    private HashMap<Good,Item> cargo;

    private int totalCap;
    private int usedCap;
    private int fuelRemaining;

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
        fuelRemaining = 0;
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
     * @param item to add in the cargo
     */
    public void addGood(Item item) {
        int quantity = item.getQuantity();
        int price = item.getPrice();
        Good good = item.getGood();

        if (getAvailableCargoCapacity() < quantity) {
            throw new java.lang.IllegalArgumentException("Not enough cargo");
        }
        if (cargo.containsKey(good)) {
            cargo.get(good).addQuantity(quantity);
        } else {
            cargo.put(good, new Item.ItemBuilder(good).price(price).quantity(quantity).build());
        }
        usedCap += quantity;
    }

    /**
     * Sell quantity of goods from the cargo.
     *
     * @param item item to sell from the cargo
     */
    public void sellGood(Item item) {
        Good good = item.getGood();
        int quantity = item.getQuantity();
        if (hasGoods(good, quantity)) {
            cargo.get(good).subQuantity(quantity);
            usedCap -= quantity;
        }
    }

    public boolean hasGoods(Good good, int quantity) {
        if (cargo.containsKey(good)) {
            if (cargo.get(good).getQuantity() >= quantity) {
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

    public Collection<Item> getCargoGoods() {
        Collection<Item> items = new ArrayList<>();
        for (Good good : this.cargo.keySet()) {
            // Build a new item based on this good
            Item item = new Item.ItemBuilder(good)
                    .quantity(this.cargo.get(good).getQuantity())
                    .price(this.cargo.get(good).getPrice())
                    .build();
            items.add(item);
        }
        return items;
    }

    public int getFuelRemaining() {
        return this.fuelRemaining;
    }

    @Override
    public String toString() {
        return shipType.toString();
    }
}
