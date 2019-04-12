package edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Planet;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.model.Model;

/**
 * Represents the view model for Main Activity.
 */
public class GameViewModel extends AndroidViewModel {
    /**
     * StartViewModel constructor with all arguments.
     *
     * @param application represents the application context
     */
    public GameViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * @return the name of the planet the player is currently on
     */
    public String getPlanetName() {
        Planet p = getPlanet();
        return p.getName();
    }

    /**
     * @return the orbit radius of the planet the player is currently on
     */
    public int getPlanetOrbitRadius() {
        Planet p = getPlanet();
        return p.getOrbitRadius();
    }

    /**
     * @return the resources of the planet the player is currently on
     */
    public String getPlanetResources() {
        Planet p = getPlanet();
        return p.getResourceClassString();
    }

    /**
     * @return the tech level of the planet the player is currently on
     */
    public String getPlanetTechLevel() {
        Planet p = getPlanet();
        return p.getTechLevelString();
    }

    /**
     * @return the planet event of the planet the player is currently on
     */
    public String getPlanetEvent() {
        Planet p = getPlanet();
        return p.getEventString();
    }

    /**
     * @return the species on the planet the player is currently on
     */
    public String getPlanetSpecies() {
        Planet p = getPlanet();
        return p.getSpeciesString();
    }

    /**
     * @return the color of the planet the player is currently on
     */
    public String getPlanetColorHex() {
        Planet p = getPlanet();
        return p.getColorHex();
    }

    private Planet getPlanet() {
        Model m = Model.getModel();
        return m.getPlanet();
    }
}
