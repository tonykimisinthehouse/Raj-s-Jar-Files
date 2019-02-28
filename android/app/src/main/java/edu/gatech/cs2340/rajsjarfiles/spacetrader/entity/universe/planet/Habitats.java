package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.planet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum Habitats {

    DESERT(
            Arrays.asList(
                    Species.MACHINE,
                    Species.REPTILIAN),
            Arrays.asList(
                    ResourceClassification.LOTS_OF_WATER,
                    ResourceClassification.RICH_SOIL,
                    ResourceClassification.LOTS_OF_HERBS)),
    OCEAN(
            Arrays.asList(
                    Species.FUNGOID),
            Arrays.asList(
                    ResourceClassification.LOTS_OF_HERBS,
                    ResourceClassification.RICH_SOIL)),

    CONTINENTAL(
            Arrays.asList(
                    Species.HUMANOID,
                    Species.REPTILIAN,
                    Species.FUNGOID,
                    Species.MACHINE),
                    null),

    ARCTIC(
            Arrays.asList(
                    Species.HUMANOID),
            Arrays.asList(
                    ResourceClassification.RICH_SOIL,
                    ResourceClassification.LOTS_OF_HERBS)),

    TOMB(
            Arrays.asList(
                    Species.MACHINE),
            Arrays.asList(
                    ResourceClassification.LOTS_OF_HERBS,
                    ResourceClassification.RICH_SOIL,
                    ResourceClassification.RICH_FAUNA,
                    ResourceClassification.WEIRD_MUSHROOMS));

    ArrayList<Species> habitableSpecies = new ArrayList<>();
    ArrayList<ResourceClassification> impossibleResources = new ArrayList<>();

    Habitats(List<Species> species, List<ResourceClassification> impossibleResource) {
        this.habitableSpecies.addAll(species);
        if (impossibleResource != null) impossibleResources.addAll(impossibleResource);
    }

    private static Random rand = new Random();

    public static Habitats getRandomHabitat() {
        Habitats[] rh = Habitats.values();
        return rh[rand.nextInt(rh.length)];
    }

    public Species getRandomHabitableSpecie() {
        return this.habitableSpecies.get(rand.nextInt(habitableSpecies.size()));
    }
}
