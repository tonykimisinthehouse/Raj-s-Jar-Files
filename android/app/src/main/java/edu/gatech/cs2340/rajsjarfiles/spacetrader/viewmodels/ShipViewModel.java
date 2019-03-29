package edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Ship;
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
    }

    public int getPlayerLocation() {
        return Model.current.getPlayer().getPlanet().getOrbitRadius();
    }

    public int getFuelRemaining() {
        return Model.current.getPlayer().getShip().getFuelRemaining();
    }

    public String getCargoHoldUsage() {
        Ship ship = Model.current.getPlayer().getShip();
        int total = ship.getCargoCapacity();
        int used = total - ship.getAvailableCargoCapacity();
        return used + "/" + total;
    }

    /**
     * Create new model
     */
    public void createNewModel() {
        model = new Model(null, null, 0, new Ship(), null);
    }

}
