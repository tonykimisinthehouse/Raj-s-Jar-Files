package edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.model.Model;

/**
 * Represents the view model for Ship Activity.
 */
public class ShipViewModel extends AndroidViewModel {

    /**
     * Backing model
     */
    public Model model;

    /**
     * ViewModel constructor with all arguments.
     *
     * @param application represents the application context
     */
    public ShipViewModel(@NonNull Application application) {
        super(application);
        model = Model.getModel();
    }

    /**
     * @return the player's location
     */
    public String getPlayerLocation() {
        return model.getPlanetName();
    }

    /**
     * @return the fuel remaining in the player's ship
     */
    public int getFuelRemaining() {
        return model.getFuelRemaining(model.getPlayer());
    }

    /**
     * @return a string showing how much cargo space is used
     */
    public String getCargoHoldUsage() {
        int total = model.getCargoCapacity(model.getPlayer());
        int used = total - model.getAvailableCargoCapacity(model.getPlayer());
        return used + "/" + total;
    }

}
