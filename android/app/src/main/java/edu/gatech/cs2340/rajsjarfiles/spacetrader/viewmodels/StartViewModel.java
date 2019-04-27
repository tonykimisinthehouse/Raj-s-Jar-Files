package edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.game.GameDifficulty;
import java.util.regex.Pattern;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.model.Model;

/**
 * Represents the view model for Main Activity.
 */
public class StartViewModel extends AndroidViewModel {
    private static final int MAX_CREDIT = 16;
    private final int[] points = new int[4];

    /**
     * StartViewModel constructor with all arguments.
     *
     * @param application represents the application context
     */
    public StartViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Checks if the user input are all valid and passes in the parameters if
     * they are all valid.
     *
     * @param editPlayerName represents the value from the EditText for playerName
     *                       in activity_start.xml
     * @param editPilotPoints represents the value from the EditText for pilotPoints
     *                        in activity_start.xml
     * @param editFighterPoints represents the value from the EditText for fighterPoints
     *                          in activity_start.xml
     * @param editTraderPoints represents the value from the EditText for traderPoints
     *                         in activity_start.xml
     * @param editEngineerPoints represents the value from the EditText for engineerPoints
     *                           in activity_start.xml
     * @return Returns boolean of whether the points were valid or not.
     */
    public Boolean isValid(EditText editPlayerName,
                           EditText editPilotPoints,
                           EditText editFighterPoints,
                           EditText editTraderPoints,
                           EditText editEngineerPoints) {

        // Validate Name
        String playerName = editPlayerName.getText().toString();
        if (playerName == null) {
            return false;
        }
        if (playerName.length() <= 0) {
            return false;
        }
        // Regular expression for accepting alphabets, white spaces only
        String regexForName = "^[a-zA-Z\\s]*$";
        if (!Pattern.matches(regexForName, playerName)) {
            return false;
        }

        // Validate Points
        if ((editPilotPoints.getText().length() == 0)
                || (editFighterPoints.getText().length() == 0)
                || (editTraderPoints.getText().length() == 0)
                || (editEngineerPoints.getText().length() == 0)) {
            return false;
        }

        int pilotPoints = Integer.parseInt(editPilotPoints.getText().toString());
        int fighterPoints = Integer.parseInt(editFighterPoints.getText().toString());
        int traderPoints = Integer.parseInt(editTraderPoints.getText().toString());
        int engineerPoints = Integer.parseInt(editEngineerPoints.getText().toString());

        // Validate max points and assign them
        if ((pilotPoints + fighterPoints + traderPoints + engineerPoints) == MAX_CREDIT) {
            points[0] = pilotPoints;
            points[1] = fighterPoints;
            points[2] = traderPoints;
            points[3] = engineerPoints;
            return true;
        }
        return false;
    }

    /**
     * Create new model with player information
     *
     * @param editTextName represents the value from the EditText for playerName
     *      *                       in activity_start.xml
     * @param difficultySpinner represents the value from the Spinner for gameDifficulty
     *      *                       in activity_start.xml
     */
    public void createNewModel(EditText editTextName, Spinner difficultySpinner) {
        String playerName = editTextName.getText().toString();
        GameDifficulty difficulty = (GameDifficulty) difficultySpinner.getSelectedItem();
        Model model = new Model(playerName, points, difficulty);
    }

    /**
     * Private helper function to calculate total skill points that user currently
     * input
     *
     * @return total skill points
     */
    private int getTotalPoints() {
        int total = 0;
        for (int item : points) {
            total += item;
        }
        return total;
    }

    /**
     * Calculate remaining skills points that the user have
     *
     * @param editSomePoints represents the value from the EditText in activity_start.xml
     * @param index indicates the type of skill points
     * @return the remaining skill points. If it exceeds max skill points, return -1
     */
    public int calculateRemainingCredit(EditText editSomePoints, int index) {
        int somePoints;
        if ("".equals(editSomePoints.getText().toString())) {
            somePoints = 0;
        } else {
            somePoints = Integer.parseInt(editSomePoints.getText().toString());
        }
        points[index] = somePoints;
        int totalItem = getTotalPoints();
        if (totalItem <= MAX_CREDIT) {
            Log.d("REMAINING_POINTS", String.valueOf(MAX_CREDIT - totalItem));
            return MAX_CREDIT - totalItem;
        } else {
            return -1;
        }
    }
}
