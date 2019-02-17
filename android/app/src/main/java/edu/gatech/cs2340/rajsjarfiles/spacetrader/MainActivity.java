package edu.gatech.cs2340.rajsjarfiles.spacetrader;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.GameDifficulty;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.Player;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * Grab the dialog widgets so we can get info for later
         */
        editPlayerName = findViewById(R.id.player_name_input);
        editPilotPoints = findViewById(R.id.pilot_skill_input);
        editFighterPoints = findViewById(R.id.fighter_skill_input);
        editTraderPoints = findViewById(R.id.trader_skill_input);
        editEngineerPoints = findViewById(R.id.engineer_skill_input);
        difficultySpinner = findViewById(R.id.difficulty_spinner);

        ArrayAdapter<GameDifficulty> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                Arrays.asList(GameDifficulty.values()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);

        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "You clicked me",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}
