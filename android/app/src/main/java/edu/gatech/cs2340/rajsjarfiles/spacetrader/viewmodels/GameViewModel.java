package edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Ship;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.model.Model;

/**
 * Represents the view model for Main Activity.
 */
public class GameViewModel extends AndroidViewModel {

    /**
     * Backing model
     */
    public Model model;

    /**
     * StartViewModel constructor with all arguments.
     *
     * @param application represents the application context
     */
    public GameViewModel(@NonNull Application application) {
        super(application);
    }


    public String getPlanetName() {
        return Model.getModel().getPlayer().getPlanet().getName();
    }

    public int getPlanetOrbitRadius() {
        return Model.getModel().getPlayer().getPlanet().getOrbitRadius();
    }

    public String getPlanetResources() {
        return Model.getModel().getPlayer().getPlanet().getResourceClass().toString();
    }

    public String getPlanetTechLevel() {
        return Model.getModel().getPlayer().getPlanet().getTechLevel().toString();
    }

    public String getPlanetEvent() {
        return Model.getModel().getPlayer().getPlanet().getMarketplace().getEvent().toString();
    }

    public String getPlanetSpecies() {
        return Model.getModel().getPlayer().getPlanet().getSpecies().toString();
    }

    public String getPlanetColorHex() {
        return Model.getModel().getPlayer().getPlanet().getHabitat().getColorHex();
    }

    /**
     * Create new model
     */
    public void createNewModel() {
        model = new Model(null, null, 0, new Ship(), null);
    }

}
