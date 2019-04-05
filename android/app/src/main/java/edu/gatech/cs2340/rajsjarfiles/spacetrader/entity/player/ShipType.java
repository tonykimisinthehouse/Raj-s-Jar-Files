package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a ship type.
 */
public enum ShipType implements Serializable {
    FLEA(25, 0),
    GNAT(100, 1),
    FIREFLY(100, 1),
    MOSQUITO(100, 2),
    BUMBLEBEE(100, 1),
    BEETLE(50, 0),
    HORNET(150, 3),
    GRASSHOPPER(150, 2),
    TERMITE(200, 1),
    WASP(200, 3);

    private int maxHealth;

    private int maxWeaponSlots;

    /**
     * Constructor for a ship type.
     *
     * @param maxHealth the max health of the ship
     * @param maxWeaponSlots the max weapons slots the ship has
     */
    ShipType(int maxHealth, int maxWeaponSlots) {
        this.maxHealth = maxHealth;
        this.maxWeaponSlots = maxWeaponSlots;
    }

    /**
     * @return the max health of the ship
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * @return the max weapons slots the ship has
     */
    public int getMaxWeaponSlots() {
        return maxWeaponSlots;
    }

    /**
     * @return ship types that can have weapons
     */
    public static List<ShipType> getShipsWithWeapons() {
        List<ShipType> list = new ArrayList<>();
        for (ShipType s : ShipType.values()) {
            if (s.maxWeaponSlots > 0) {
                list.add(s);
            }
        }
        return list;
    }
}
