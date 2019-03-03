package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Represents habitat of a planet
 */
public enum Habitats {

    DESERT(Arrays.asList(Species.MACHINE, Species.REPTILIAN),
            Arrays.asList(
                    ResourceClassification.LOTS_OF_WATER,
                    ResourceClassification.RICH_SOIL,
                    ResourceClassification.LOTS_OF_HERBS)),

    OCEAN(Arrays.asList(Species.FUNGOID),
            Arrays.asList(
                    ResourceClassification.LOTS_OF_HERBS,
                    ResourceClassification.RICH_SOIL)),

    CONTINENTAL(Arrays.asList(Species.HUMANOID, Species.REPTILIAN, Species.FUNGOID, Species.MACHINE),
                    null),

    ARCTIC(Arrays.asList(Species.HUMANOID),
            Arrays.asList(
                    ResourceClassification.RICH_SOIL,
                    ResourceClassification.LOTS_OF_HERBS)),

    TOMB(Arrays.asList(Species.MACHINE),
            Arrays.asList(
                    ResourceClassification.LOTS_OF_HERBS,
                    ResourceClassification.RICH_SOIL,
                    ResourceClassification.RICH_FAUNA,
                    ResourceClassification.WEIRD_MUSHROOMS));

    // Store list of habitable species
    ArrayList<Species> habitableSpecies = new ArrayList<>();
    // Store list of impossible resources
    ArrayList<ResourceClassification> impossibleResources = new ArrayList<>();

    /**
     * Constructor for enum class Habitats
     *
     * @param species list of species that can live in this habitats
     * @param impossibleResource list of special resources that cannot exists in this habitats
     */
     Habitats(List<Species> species, List<ResourceClassification> impossibleResource) {
        this.habitableSpecies.addAll(species);
        if (impossibleResource != null) impossibleResources.addAll(impossibleResource);
    }

    /**
     * Get random habitats
     *
     * @return random habitat
     */
    public static Habitats getRandomHabitat() {
        Random rand = new Random();
        Habitats[] rh = Habitats.values();
        return rh[rand.nextInt(rh.length)];
    }
}
