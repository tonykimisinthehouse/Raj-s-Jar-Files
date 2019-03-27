package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import java.util.Random;

/**
 * Represents species
 */
public enum Species {

    HUMANOID, // Human
    MACHINE, // Machine
    FUNGOID, // Fungi
    REPTILIAN; // Reptile

    /**
     * Get Random species that can live in certain habitats
     *
     * @param habitats habitat of the planet
     * @return habitable species
     */
    public static Species getRandomHabitableSpecies(Habitats habitats) {
        Random rand = new Random();
        return habitats.getHabitableSpecies().
                get(rand.nextInt(habitats.getHabitableSpecies().size()));
    }
}


