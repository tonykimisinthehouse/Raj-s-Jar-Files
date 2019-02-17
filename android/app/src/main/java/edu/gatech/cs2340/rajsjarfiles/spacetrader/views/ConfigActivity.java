package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.GameDifficulty;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.Player
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.PlayerBuilder;

public class ConfigActivity extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_main);

        /*
         * Grab the dialog widgets so we can get info for later
         */
        editPlayerName = findViewById(R.id.player_name_input);
        editPilotPoints = findViewById(R.id.pilot_skill_input);
        editFighterPoints = findViewByID(R.id.fighter_skill_input);;
        editTraderPoints = findViewByID(R.id.trader_skill_input);
        editEngineerPoints = findViewByID(R.id.engineer_skill_input);
        difficultySpinner = findViewByID(R.id.difficulty_spinner);
        Button button = findViewById(R.id.add_button);

        ArrayAdapter<GameDifficulty> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                Arrays.asList(GameDifficulty.values()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);

    }

    /**
     * Connects to the view model.
     * @param view Raw data from the config activity
     */
    public void onAddPressed(View view) {

    }
}
