package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Planet;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.SolarSystem;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.model.Model;

public class Location {

    private Planet planet; // Planet that the player is currently on.
    private SolarSystem solarSystem;

    Location (){
        this(Model.getCurrent().getGame().getUniverse().getRandomSolarSystem());
    }

    Location(SolarSystem solarSystem) {
        this(solarSystem, solarSystem.getRandomPlanet());
    }

    Location(SolarSystem solarSystem, Planet planet) {
        setSolarSystem(solarSystem);
        setPlanet(planet);
    }

    public void setSolarSystem(SolarSystem solarSystem) {
        this.solarSystem = solarSystem;
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

}
