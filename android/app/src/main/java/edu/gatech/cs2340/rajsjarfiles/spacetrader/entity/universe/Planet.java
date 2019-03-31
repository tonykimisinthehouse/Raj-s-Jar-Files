package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import java.util.Random;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Marketplace;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.utility.LogCustom;


/**
 * Represents a planet within a solar system.
 */
public class Planet {
    private String name;

    private int radius;         //radius of planet itself
    private int orbitRadius;    //distance from center
    private int orbitAngle;     //angle from center
    private boolean isWarpZone = false;

    private TechLevel techLevel;
    private Habitats habitats;
    private Species species;
    private ResourceClassification resourceClass;
    private Marketplace marketplace;

    static Random rand = new Random();

    ///////////////////////////// CONSTRUCTOR /////////////////////////////
    /**
     * Creates a planet using a planet builder.
     *
     * @param builder the planet builder
     */
    public Planet(PlanetBuilder builder) {
        this.name = builder.name;
        this.radius = builder.radius;
        this.orbitRadius = builder.orbitRadius;
        this.orbitAngle = builder.orbitAngle;
        this.techLevel = builder.techLevel;
        this.habitats = builder.habitats;
        this.species = builder.species;
        this.resourceClass = builder.resourceClass;
        this.marketplace = builder.marketplace;
    }

    //no setters because the fields shouldn't change

    ///////////////////////////// PLANET INFO /////////////////////////////
    /**
     * @return the planet's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the planet's radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * @return the planet's orbit radius
     */
    public int getOrbitRadius() {
        return orbitRadius;
    }

    public int getOrbitAngle() {
        return orbitAngle;
    }

    public void setIsWarpZone(Boolean bool) {
        this.isWarpZone = bool;
    }

    public boolean getIsWarpZone() {
        return isWarpZone;
    }

    ///////////////////////////// PLANET SPECIFICATION /////////////////////////////
    /**
     * @return the planet's tech level
     */
    public TechLevel getTechLevel() {
        return techLevel;
    }

    /**
     * @return the planet's habitat
     */
    public Habitats getHabitats() {
        return habitats;
    }

    /**
     * @return the species on the planet
     */
    public Species getSpecies() {
        return species;
    }

    /**
     * @return the planet's resource classification
     */
    public ResourceClassification getResourceClass() {
        return resourceClass;
    }

    /**
     * @return planet's market place
     */
    public Marketplace getMarketplace() {
        return marketplace;
    }


    ///////////////////////////// PLANET UTILITY /////////////////////////////
    /**
     * Returns the "distance" in terms of orbit radius to another planet.
     *
     * @param other the other planet
     * @return the difference in orbit radius
     */
    public int getDist(Planet other) {
        // Use cosine rule (c^2 = a^2 + b^2 - 2ab*cos(c))
        int angleRaw = Math.abs(orbitAngle - other.getOrbitAngle());
        int angle = (angleRaw <= 180 ? angleRaw : 360 - angleRaw);

        int a = this.orbitRadius;
        int b = other.orbitRadius;

        int c = (int) Math.sqrt((a*a)+(b*b)-(2*a*b*Math.cos(angle)));
        return c;
    }

    /**
     * Returns the distance between two given planets.
     *
     * @param p1 the first planet
     * @param p2 the second planet
     * @return the distance between the two planets
     */
    public static int distBetween(Planet p1, Planet p2) {
        return p1.getDist(p2);
    }

    ///////////////////////////// OVERRIDE FUNCTION /////////////////////////////
    @Override
    public String toString() {
        LogCustom.largeLog("Market", marketplace.toString());
        return String.format("%-16s", name)
                + "| Radius: " + radius
                + String.format(", orbit radius: %2d", orbitRadius)
                + String.format(" | tech level: %-20s", techLevel.toString())
                + String.format(" | resource class: %-20s",
                resourceClass.toString())
                + String.format(" | species: %-10s", species.toString())
                + String.format(" | habitat: %s", habitats.toString())
                + ".";
    }

    @Override
    public boolean equals(Object that) {
        if (that == this) {
            return true;
        }
        if (!(that instanceof Coordinate)) {
            return false;
        }
        Planet p = (Planet) that;
        return this.name == p.name
                && this.radius == p.radius;
    }

    /**
     * Nested PlanetBuilder class
     */
    public static class PlanetBuilder {

        private static final int MIN_RADIUS = 2;
        private static final int MAX_RADIUS = 5;

        private final String name;

        private int radius;
        private final int orbitRadius;
        private final int orbitAngle;

        private TechLevel techLevel;
        private Habitats habitats;
        private Species species;
        private ResourceClassification resourceClass;
        private Marketplace marketplace;
        private PlanetEvents event;

        /**
         * Creates a planet with a given name, radius, and orbit radius (how far
         * it is from the center of the solar system).
         *
         * @param name the name of the planet
         * @param orbitRadius the orbit radius of the planet
         */
        public PlanetBuilder(String name, int orbitRadius, int orbitAngle) {
            this.name = name;
            this.orbitRadius = orbitRadius;
            this.orbitAngle = orbitAngle;
        }

        /**
         * One arg radius setter for builder class
         *
         * @param r radius to set
         * @return builder
         */
        public PlanetBuilder radius(int r) {
            this.radius = r;
            return this;
        }

        /**
         * One arg tech level setter for builder class
         *
         * @param tl tech level to set
         * @return builder
         */
        public PlanetBuilder techLevel(TechLevel tl) {
            this.techLevel = tl;
            return this;
        }

        /**
         * One arg habitat setter for builder class
         *
         * @param ha habitat to set
         * @return builder
         */
        public PlanetBuilder habitats(Habitats ha) {
            this.habitats = ha;
            return this;
        }

        /**
         * One arg species setter for builder class
         *
         * @param sp species to set
         * @return builder
         */
        public PlanetBuilder species(Species sp) {
            this.species = sp;
            return this;
        }

        /**
         * One arg resource class setter for builder class
         *
         * @param rc resource to set
         * @return builder
         */
        public PlanetBuilder resourceClass(ResourceClassification rc) {
            this.resourceClass = rc;
            return this;
        }

        /**
         * Builder for builder class
         *
         * @return planet
         */
        public Planet build() {
            // If the builder does not get explicit value, it will randomly assign them.
            if (this.radius == 0) {
                this.radius = getRandomRadius();
            }
            if (this.habitats == null) {
                this.habitats = Habitats.getRandomHabitat();
                this.resourceClass = ResourceClassification.
                        getRandomResourceClass(this.habitats);
                this.species = Species.getRandomHabitableSpecies(this.habitats);
            }

            if (this.techLevel == null) {
                this.techLevel = TechLevel.getRandomTechLevel();
            }

            if (this.event == null) {
                this.event = PlanetEvents.getRandomEvent();
            }

            if (this.marketplace == null) {
                this.marketplace = new Marketplace(
                        this.name,
                        this.techLevel,
                        this.event,
                        this.resourceClass
                );
            }
            return new Planet(this);
        }

        /**
         * Generates a random radius given the bounds.
         *
         * @return a random radius
         */
        private static int getRandomRadius() {
            return rand.nextInt(
                    MAX_RADIUS - MIN_RADIUS + 1)
                    + MIN_RADIUS;
        }
    }
}
