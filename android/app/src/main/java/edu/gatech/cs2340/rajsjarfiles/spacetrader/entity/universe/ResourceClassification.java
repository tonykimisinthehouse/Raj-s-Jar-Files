package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * Represents a planet's resource classification.
 */
public enum ResourceClassification {

    NO_SPECIAL_RESOURCES(35),

    MINERAL_RICH(10),
    MINERAL_POOR(10),

    LOTS_OF_WATER(10),

    RICH_SOIL(10),
    POOR_SOIL(10),

    RICH_FAUNA(10),

    WEIRD_MUSHROOMS(10),

    LOTS_OF_HERBS(10),

    ARTISTIC(10),
    WARLIKE(30),
    LIFELESS(5),
    DESERT(10);

    //ARTISTIC,
    //WARLIKE,

    // Probability of having certain resources in a planet
    private int probOfHavingResource;

    /**
     * Constructor for resource classification enum class
     *
     * @param prob probability of having this resource in a planet
     */
    ResourceClassification(int prob) {
        this.probOfHavingResource = prob;
    }

    /**
     * Set probability of resource to exists in a planet
     *
     * @param prob probability of resource to exists
     */
    public void setProbability(int prob) {
        this.probOfHavingResource = prob;
    }

    /**
     * Get random resource classification for certain habitats.
     * @param habitats habitat of a planet where the resource is allocated
     * @return single random resource classification
     */
    public static ResourceClassification getRandomResourceClass(
            Habitats habitats) {
        Random rand = new Random();

        // List that store possible resource classification
        Collection<ResourceClassification> pr = new ArrayList<>();

        // Filter out resources that are possible to exists in this habitat
        for (ResourceClassification resource
                : ResourceClassification.values()) {
            if ((resource != null)
                    && !habitats.getImpossibleResources().contains(resource)) {
                pr.add(resource);
            }
        }

        // Randomize the choice but taking care of the probability
        ArrayList<Integer> distributionResource = new ArrayList<>();
        for (ResourceClassification r : pr) {
            for (int i = 0; i < r.probOfHavingResource; i++) {
                distributionResource.add(r.ordinal());
            }
        }
        int randNum = distributionResource.get(
                rand.nextInt(distributionResource.size()));
        return ResourceClassification.values()[randNum];
    }
}
