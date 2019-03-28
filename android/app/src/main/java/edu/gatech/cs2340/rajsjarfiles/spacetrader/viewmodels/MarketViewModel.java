package edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

/**
 * Represents the view model for Main Activity.
 */
public class MarketViewModel extends AndroidViewModel {
    /**
     * StartViewModel constructor with all arguments.
     *
     * @param application represents the application context
     */
    public MarketViewModel(@NonNull Application application) {
        super(application);
    }
}
