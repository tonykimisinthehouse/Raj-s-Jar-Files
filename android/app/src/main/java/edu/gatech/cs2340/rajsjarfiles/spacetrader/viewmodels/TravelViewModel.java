package edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.model.Model;

/**
 * Represents the view model for Travel Activity.
 */
public class TravelViewModel extends AndroidViewModel {

    /**
     * Backing model
     */
    public Model model;

    /**
     * ViewModel constructor with all arguments.
     *
     * @param application represents the application context
     */
    public TravelViewModel(@NonNull Application application) {
        super(application);
    }

//    /**
//     * Verifies that the chosen destination is reachable by the ship.
//     */
//    public void checkDestination() {
//        // Verify the chosen destination is reachable by the ship
//    }

}
