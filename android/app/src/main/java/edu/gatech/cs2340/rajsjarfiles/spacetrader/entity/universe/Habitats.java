package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
                    ResourceClassification.LOTS_OF_HERBS),
            "#ffff00"),

    OCEAN(Arrays.asList(Species.FUNGOID),
            Arrays.asList(
                    ResourceClassification.LOTS_OF_HERBS,
                    ResourceClassification.RICH_SOIL),
            "#0099ff"),

    CONTINENTAL(Arrays.asList(Species.HUMANOID, Species.REPTILIAN,
            Species.FUNGOID, Species.MACHINE),
                    null,
            "#33cc33"),

    ARCTIC(Arrays.asList(Species.HUMANOID),
            Arrays.asList(
                    ResourceClassification.RICH_SOIL,
                    ResourceClassification.LOTS_OF_HERBS),
            "#B7B7B7"),
    TOMB(Arrays.asList(Species.MACHINE),
            Arrays.asList(
                    ResourceClassification.LOTS_OF_HERBS,
                    ResourceClassification.RICH_SOIL,
                    ResourceClassification.RICH_FAUNA,
                    ResourceClassification.WEIRD_MUSHROOMS),
            "#cc3300");

    // Store list of habitable species
    private final List<Species> habitableSpecies = new ArrayList<>();
    // Store list of impossible resources
    private final List<ResourceClassification> impossibleResources =
            new ArrayList<>();
    // Color of the habitat in hex
    private final String colorHex;

    /**
     * Constructor for enum class Habitats
     *
     * @param species list of species that can live in this habitats
     * @param impossibleResource list of special
     *                           resources that cannot exists in this habitats
     * @param colorHex color tint of the planet
     */
    Habitats(Collection<Species> species,
             Collection<ResourceClassification> impossibleResource,
             String colorHex) {
        this.habitableSpecies.addAll(species);
        if (impossibleResource != null) {
            impossibleResources.addAll(impossibleResource);
        }
        this.colorHex = colorHex;
    }

    /**
     * @return the habitable species
     */
    public List<Species> getHabitableSpecies() {
        return habitableSpecies;
    }

    /**
     * @return the impossible resources
     */
    public List<ResourceClassification> getImpossibleResources() {
        return impossibleResources;
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

    /**
     * @return the color tint for the planet based on the habitat
     */
    public String getColorHex() {
        return colorHex;
    }
}
