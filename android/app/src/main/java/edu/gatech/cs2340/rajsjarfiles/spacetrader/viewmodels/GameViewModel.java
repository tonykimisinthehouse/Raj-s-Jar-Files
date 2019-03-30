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
    public String getPlanetName() {
        return Model.getModel().getPlayer().getLocation().getPlanet().getName();
    }

    /**
     * @return the orbit radius of the planet the player is currently on
     */
    public int getPlanetOrbitRadius() {
        return Model.getModel().getPlayer().getLocation().getPlanet().getOrbitRadius();
    }

    /**
     * @return the resources of the planet the player is currently on
     */
    public String getPlanetResources() {
        return Model.getModel().getPlayer()
                .getLocation().getPlanet().getResourceClass().toString();
    }

    /**
     * @return the tech level of the planet the player is currently on
     */
    public String getPlanetTechLevel() {
        return Model.getModel().getPlayer()
                .getLocation().getPlanet().getTechLevel().toString();
    }

    /**
     * @return the planet event of the planet the player is currently on
     */
    public String getPlanetEvent() {
        return Model.getModel().getPlayer()
                .getLocation().getPlanet().getMarketplace().getEvent().toString();
    }

    /**
     * @return the species on the planet the player is currently on
     */
    public String getPlanetSpecies() {
        return Model.getModel().getPlayer()
                .getLocation().getPlanet().getSpecies().toString();
    }

    /**
     * @return the color of the planet the player is currently on
     */
    public String getPlanetColorHex() {
        return Model.getModel().getPlayer()
                .getLocation().getPlanet().getHabitats().getColorHex();
    }
}
