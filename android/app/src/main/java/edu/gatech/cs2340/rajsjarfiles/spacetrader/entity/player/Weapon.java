package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player;

import java.io.Serializable;
import java.util.Random;

public enum Weapon implements Serializable {
    //these are fairly arbitrary
    //so we'll have to balance them later
    PULSE_LASER(10),
    BEAM_LASER(12),
    MILITARY_LASER(20);

    private int strength;

    /**
     * Creates a weapon with a given strength.
     *
     * @param strength how much damage the weapon does
     */
    Weapon(int strength) {
        this.strength = strength;
    }

    /**
     * @return the weapon's strength
     */
    public int getStrength() {
        return strength;
    }

    /**
     * @return a random weapon
     */
    public static Weapon getRandomWeapon() {
        //can do different weights later

        Random rand = new Random();
        return values()[rand.nextInt(values().length)];
    }
}
