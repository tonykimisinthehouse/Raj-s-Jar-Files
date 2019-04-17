package edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

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
    public String getPlanetName(Model m) {
        return m.getPlanetName();
    }

    /**
     * @return the orbit radius of the planet the player is currently on
     */
    public int getPlanetOrbitRadius(Model m) {
        return m.getPlanetOrbitRadius();
    }

    /**
     * @return the string representation of the resources of the planet
     * the player is currently on
     */
    public String getPlanetResources(Model m) {
        return m.getResourceClassString();
    }

    /**
     * @return the tech level of the planet the player is currently on
     */
    public String getPlanetTechLevel(Model m) {
        return m.getPlanetTechLevel(m.getPlanet());
    }

    /**
     * @return the planet event of the planet the player is currently on
     */
    public String getPlanetEvent(Model m) {
        return m.getPlanetEvent(m.getPlanet());
    }

    /**
     * @return the species on the planet the player is currently on
     */
    public String getPlanetSpecies(Model m) {
        return m.getPlanetSpecies(m.getPlanet());
    }

    /**
     * @return the color of the planet the player is currently on
     */
    public String getPlanetColorHex(Model m) {
        return m.getPlanetColorHex(m.getPlanet());
    }
}
