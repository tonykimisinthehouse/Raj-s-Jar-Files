package edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Ship;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.model.Model;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.views.TravelActivity;

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

    public String getPlayerLocation() {
        return Model.getCurrent().getPlayer().getLocation().getPlanet().getName();
    }

    public int getFuelRemaining() {
        return Model.getCurrent().getPlayer().getShip().getFuel();
    }

    public String getCargoHoldUsage() {
        Ship ship = Model.getCurrent().getPlayer().getShip();
        int total = ship.getCargoCapacity();
        int used = total - ship.getAvailableCargoCapacity();
        return used + "/" + total;
    }

}
