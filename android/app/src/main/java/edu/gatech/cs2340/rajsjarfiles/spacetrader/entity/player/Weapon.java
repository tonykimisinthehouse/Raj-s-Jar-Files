package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player;

import java.util.Random;

public enum Weapon {
    PULSE_LASER(10), //these are fairly arbitrary
                    //so we'll have to balance them later
    BEAM_LASER(12),
    MILITARY_LASER(20);

    private int strength;

    Weapon(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public static Weapon getRandomWeapon() {
        //can do different weights later

        Random rand = new Random();
        return values()[rand.nextInt(values().length)];
    }
}
