package edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.regex.Pattern;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.game.GameDifficulty;
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

    /**
     * Create new model
     */
    public void createNewModel() {
        model = new Model(null, null, 0, new Ship(), null);
    }

}
