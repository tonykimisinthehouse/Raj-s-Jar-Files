package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Planet;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.SolarSystem;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.model.Model;

/**
 * Represents a location in the Universe.
 */
public class Location {

    private Planet planet; // Planet that the player is currently on.
    private SolarSystem solarSystem;

    Location() {
        this(Model.getModel().getGame().getUniverse().getRandomSolarSystem());
    }

    Location(SolarSystem solarSystem) {
        this(solarSystem, solarSystem.getRandomPlanet());
    }

    Location(SolarSystem solarSystem, Planet planet) {
        setSolarSystem(solarSystem);
        setPlanet(planet);
    }

    /**
     * Sets solar system to a new solar system.
     *
     * @param solarSystem the new solar system.
     */
    private void setSolarSystem(SolarSystem solarSystem) {
        this.solarSystem = solarSystem;
    }

    /**
     * @return the solar system
     */
    public SolarSystem getSolarSystem() {
        return this.solarSystem;
    }

    public int checkIfTravelPossible(SolarSystem destinationSS, Planet destinationP) {
        // Yes possible (between planet)
        if (checkIfTravelInSS(destinationSS)) {
            return 0;
        } else {
            if (planet.getIsWarpZone()) {
                // Yes possible (between solar system)
                return 1;
            }
        }
        return -1;
    }

    private boolean checkIfTravelInSS(SolarSystem destinationSS) {
        return solarSystem.equals(destinationSS);
    }

    /**
     * Determine how much fuel is required to travel to this planet.
     *
     * @param destinationP the new planet
     * @return the amount of fuel needed
     */
    public int calculateFuelRq(Planet destinationP) {
        int distance = this.planet.getDist(destinationP);
        return distance * 5;
    }

    /**
     * Setter for planet which the player is on.
     *
     * @param planet that the player is on.
     */
    private void setPlanet(Planet planet) {
        this.planet = planet;
    }

    /**
     * @return The planet this player is currently on
     */
    public Planet getPlanet() {
        return this.planet;
    }

    /**
     * @return a list of the planets in the solar system the player
     * is in
     */
    public Planet[] getPlanets() {
        return solarSystem.getPlanets();
    }
}
