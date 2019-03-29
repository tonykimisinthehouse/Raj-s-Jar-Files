package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Good;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Item;

/**
 * Represents a ship that a character or enemy can use.
 */
public class Ship {
    private ShipType shipType;

    private HashMap<Good, Item> cargo;
    private int totalCap;
    private int usedCap;

    private int health;

    private List<Weapon> weapons;

    /**
     * Default constructor that sets ShipType to GNAT.
     */
    public Ship() {
        this(10, ShipType.GNAT);
    }

    /**
     * One arg constructor for creating a ship.
     *
     * @param shipType the ship type
     */
    public Ship(ShipType shipType) {
        this(10, shipType);
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

        health = shipType.getMaxHealth();

        weapons = new ArrayList<>(shipType.getMaxWeaponSlots());
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Does damage to the ship. Returns true if dead, false if still alive.
     *
     * @param damage the amount of damage
     * @return whether or not the ship is destroyed
     */
    public boolean takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
        }
        return health <= 0;
    }

    public void addWeapon(Weapon w) {
        if (weapons.size() < shipType.getMaxWeaponSlots()) {
            weapons.add(w);
        }
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    @Override
    public String toString() {
        return shipType.toString();
    }

    private static Random rand = new Random();

    /**
     * @return a random instance of a ship with no cargo
     */
    public static Ship getRandomShip() {
        ShipType st = ShipType.values()[rand.nextInt(ShipType.values().length)];
        Ship ship = new Ship(0, st);
        return ship;
    }

    /**
     * @return a random instance of a ship that has weapons and is slightly
     * damaged (up to half its health can be gone)
     */
    public static Ship getRandomShipWithWeapons() {
        ShipType st = ShipType.getShipsWithWeapons().
                get(rand.nextInt(ShipType.getShipsWithWeapons().size()));

        Ship ship = new Ship(0, st);

        //add weapons to the ship
        int numWeaponsToAdd;

        if (st.getMaxWeaponSlots() == 1) {
            numWeaponsToAdd = 1;
        } else {
            numWeaponsToAdd = rand.nextInt(st.getMaxWeaponSlots() - 1) + 1;
        }
        for (int i = 0; i < numWeaponsToAdd; i++) {
            ship.addWeapon(Weapon.getRandomWeapon());
        }

        ship.setHealth(rand.nextInt(st.getMaxHealth() / 2)
                + st.getMaxHealth() / 2);
        return ship;
    }
}
