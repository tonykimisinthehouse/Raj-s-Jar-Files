package edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.Spinner;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.GameDifficulty;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.Ship;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.model.Model;

/**
 * Represents the view model for Main Activity.
 */
public class MainViewModel extends AndroidViewModel {
    public Model model;
    int defaultCredit = 1000;

    /**
     * MainViewModel constructor with all arguments.
     *
     * @param application represents the application ocntext
     */
    public MainViewModel(@NonNull Application application) {
        super(application);
    }


//    public Boolean playerNameValid(EditText editPlayerName) {
//        String playerName = editPlayerName.getText().toString();
//        if (playerName == null) {return false;}
//        if (playerName.length() < 0) {return false;}
//        return true;
//    }
//
//    public Boolean skillPointsInputValid(
//            EditText editPilotPoints,
//            EditText editFighterPoints,
//            EditText editTraderPoints,
//            EditText editEngineerPoints) {
//        if (editPilotPoints.getText().length() == 0 ||
//                editFighterPoints.getText().length() == 0 ||
//                editTraderPoints.getText().length() == 0 ||
//                editEngineerPoints.getText().length() == 0) {
//            return false;
//        }
//        return true;
//    }
//
//    public Boolean allPoints(
//            EditText editPilotPoints,
//            EditText editFighterPoints,
//            EditText editTraderPoints,
//            EditText editEngineerPoints) {
//        int pilotPoints = Integer.parseInt(editPilotPoints.getText().toString());
//        int fighterPoints = Integer.parseInt(editFighterPoints.getText().toString());
//        int traderPoints = Integer.parseInt(editTraderPoints.getText().toString());
//        int engineerPoints = Integer.parseInt(editEngineerPoints.getText().toString());
//
//        if ((pilotPoints + fighterPoints + traderPoints + engineerPoints) == 16) {
//            return false;
//        }
//        return true;
//    }
//
//
//    public void setupGame(String playerName,
//                          int pilotPoints,
//                          int fighterPoints,
//                          int traderPoints,
//                          int engineerPoints,
//                          GameDifficulty difficulty) {
//        int[] points = {pilotPoints, fighterPoints, traderPoints, engineerPoints};
//        model = new Model(
//                playerName, points, defaultCredit, new Ship(), difficulty);
//    }



    /**
     * Checks if the user input are all valid and passes in the parameters if
     * they are all valid.
     *
     * @param editPlayerName represents the value from the EditText for playerName
     *                       in activity_main.xml
     * @param editPilotPoints represents the value from the EditText for pilotPoints
     *                        in activity_main.xml
     * @param editFighterPoints represents the value from the EditText for fighterPoints
     *                          in activity_main.xml
     * @param editTraderPoints represents the value from the EditText for traderPoints
     *                         in activity_main.xml
     * @param editEngineerPoints represents the value from the EditText for engineerPoints
     *                           in activity_main.xml
     * @param difficultySpinner represents the value from the Spinner for gameDifficulty
     *                          in activity_main.xml
     * @return Returns boolean of whether the valids were valid or not.
     */
    public Boolean isValid(EditText editPlayerName,
                           EditText editPilotPoints,
                           EditText editFighterPoints,
                           EditText editTraderPoints,
                           EditText editEngineerPoints,
                           Spinner difficultySpinner) {

        String playerName = editPlayerName.getText().toString();
        if (playerName == null) {return false;}
        if (playerName.length() < 0) {return false;}

        if (editPilotPoints.getText().length() == 0 ||
        editFighterPoints.getText().length() == 0 ||
        editTraderPoints.getText().length() == 0 ||
        editEngineerPoints.getText().length() == 0) {
            return false;
        }

        int pilotPoints = Integer.parseInt(editPilotPoints.getText().toString());
        int fighterPoints = Integer.parseInt(editFighterPoints.getText().toString());
        int traderPoints = Integer.parseInt(editTraderPoints.getText().toString());
        int engineerPoints = Integer.parseInt(editEngineerPoints.getText().toString());

        if ((pilotPoints + fighterPoints + traderPoints + engineerPoints) != 16) {
            return  false;
        }
        int[] points = {pilotPoints,
                    fighterPoints,
                    traderPoints,
                    engineerPoints};
        GameDifficulty difficulty = (GameDifficulty) difficultySpinner.getSelectedItem();

        model = new Model(
                playerName, points, defaultCredit, new Ship(), difficulty);
        return true;
    }
}
