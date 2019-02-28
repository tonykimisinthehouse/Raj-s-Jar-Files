package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.planet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a planet's resource classification.
 */
public enum ResourceClassification {

    NO_SPECIAL_RESOURCES(35),
    MINERAL_RICH(10),
    LOTS_OF_WATER(10),
    RICH_SOIL(10),
    RICH_FAUNA(10),
    WEIRD_MUSHROOMS(10),
    LOTS_OF_HERBS(10),
    LIFELESS(5);

    //ARTISTIC,
    //WARLIKE,

    private int prob;

    ResourceClassification(int prob) {
        this.prob = prob;
    }

   private static Random rand = new Random();

    /**
     * Returns a random resource class. The implementation may vary
     * (different weights for each class).
     *
     * @return a random tech level
     */
    public static ResourceClassification getRandomResourceClass(Habitats habitats) {
        List<ResourceClassification> pr = new ArrayList<>();
        for (ResourceClassification resource : ResourceClassification.values()) {
            if (resource != null &&
                    !habitats.impossibleResources.contains(resource)) {
                pr.add(resource);
            }
        }
        ArrayList<Integer> distributionResource = new ArrayList<>();
        for (ResourceClassification r : pr) {
            for (int i = 0; i < r.prob; i++) {
                distributionResource.add(r.ordinal());
            }
        }
        int randNum = distributionResource.get(
                rand.nextInt(distributionResource.size()));
        return ResourceClassification.values()[randNum];
    }
}
