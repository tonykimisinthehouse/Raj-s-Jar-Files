package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.R;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.game.GameDifficulty;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels.MainViewModel;

/**
 * Represents the Activity file for content_main.xml
 */
public class MainActivity extends AppCompatActivity {

    private EditText editPlayerName;
    private EditText editPilotPoints;
    private EditText editFighterPoints;
    private EditText editTraderPoints;
    private EditText editEngineerPoints;
    private Spinner difficultySpinner;

    /* **********
        Data for player being edited.
     */
    private Player player;

    private MainViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);


        /*
         * Grab the dialog widgets so we can get info for later
         */
        editPlayerName = findViewById(R.id.player_name_input);
        editPilotPoints = findViewById(R.id.pilot_skill_input);
        editFighterPoints = findViewById(R.id.fighter_skill_input);
        editTraderPoints = findViewById(R.id.trader_skill_input);
        editEngineerPoints = findViewById(R.id.engineer_skill_input);
        difficultySpinner = findViewById(R.id.difficulty_spinner);

        /*
         * Set up the dropdown menu for the spinner
         */
        ArrayAdapter<GameDifficulty> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                Arrays.asList(GameDifficulty.values()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);

        Button submitButton = (Button) findViewById(R.id.submit_button);
    }

    /**
     * Method for hooking up the view with viewmodel that does the logic.
     *
     * @param view
     */
    public void onSave(View view) {

//        if (!viewModel.playerNameValid(editPlayerName)) {
//            Toast.makeText(getApplicationContext(),
//                    "Check if your name slot is blank",
//                    Toast.LENGTH_SHORT).show();
//        } else if (!viewModel.skillPointsInputValid(
//                editPilotPoints,
//                editFighterPoints,
//                editTraderPoints,
//                editEngineerPoints
//        )) {
//            Toast.makeText(getApplicationContext(),
//                    "Check if any of your skill point slot is blank",
//                    Toast.LENGTH_SHORT).show();
//        } else if (!viewModel.skillPointsInputValid(
//                editPilotPoints,
//                editFighterPoints,
//                editTraderPoints,
//                editEngineerPoints)) {
//            Toast.makeText(getApplicationContext(),
//                    "Check if you used exactly 16 skill points",
//                    Toast.LENGTH_SHORT).show();
//        }
//
//        viewModel.setupGame(
//                editPlayerName.getText().toString(),
//                Integer.parseInt(editPilotPoints.getText().toString()),
//                Integer.parseInt(editFighterPoints.getText().toString()),
//                Integer.parseInt(editTraderPoints.getText().toString()),
//                Integer.parseInt(editEngineerPoints.getText().toString()),
//                (GameDifficulty) difficultySpinner.getSelectedItem()
//        );
//


        Boolean isValid = viewModel.isValid(
                editPlayerName,
                editPilotPoints,
                editFighterPoints,
                editTraderPoints,
                editEngineerPoints,
                difficultySpinner);
        if (isValid == false) {
            Toast.makeText(getApplicationContext(),
                    "Your inputs are invalid.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(),
                    "Check LogCat! (Command + 6)",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void onExit(View view) {
        this.finish();
    }

}
