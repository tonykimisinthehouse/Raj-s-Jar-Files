package edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Log;
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
    private static final int MAX_CREDIT = 16;
    private int[] points = new int[4];

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


    public Boolean isValid(EditText editPlayerName,
                           EditText editPilotPoints,
                           EditText editFighterPoints,
                           EditText editTraderPoints,
                           EditText editEngineerPoints) {

        // Validate Name
        String playerName = editPlayerName.getText().toString();
        if (playerName == null) {return false;}
        if (playerName.length() <= 0) {return false;}

        // Validate Points
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

        // Validate max points
        return (pilotPoints + fighterPoints + traderPoints + engineerPoints) == MAX_CREDIT;
    }

    public void createNewModel(EditText editTextName, Spinner difficultySpinner) {
        String playerName = editTextName.getText().toString();
        GameDifficulty difficulty = (GameDifficulty) difficultySpinner.getSelectedItem();
        model = new Model(playerName, points, defaultCredit, new Ship(), difficulty);
    }

    private int getTotalPoints() {
        int total = 0;
        for (int item : points) {
            total += item;
        }
        return total;
    }

    public int calculateRemainingCredit(EditText editSomePoints, int index) {
        int somePoints;
        if (editSomePoints.getText().toString().equals("")) {
            somePoints = 0;
        } else {
            somePoints = Integer.parseInt(editSomePoints.getText().toString());
        }
        points[index] = somePoints;
        int totalItem = getTotalPoints();
        if (totalItem <= MAX_CREDIT) {
            Log.d("calc remaining",String.valueOf(MAX_CREDIT - totalItem));
            return MAX_CREDIT - totalItem;
        } else {
            return -1;
        }
    }
}
