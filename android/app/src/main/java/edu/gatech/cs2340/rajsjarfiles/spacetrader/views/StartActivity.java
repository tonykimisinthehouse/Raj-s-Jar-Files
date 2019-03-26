package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.R;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.game.GameDifficulty;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels.StartViewModel;

/**
 * Represents the Activity file for content_start.xml
 */
public class StartActivity extends AppCompatActivity {

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

    private StartViewModel viewModel;

    /**
     * Method that return new Textwatcher to set.
     *
     * @param editText represents the EditText in activity_start.xml
     * @param index indicates the type of skill points
     * @return
     */
    private TextWatcher getTextWatcher(final EditText editText, final int index) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                validateCreditEntries(editText, index);
            }
        };
    }

    /**
     * Helper method that update remaining credit indicator based on the remaining skill points
     * @param editText represents the EditText in activity_start.xml
     * @param index indicates the type of skill points
     */
    private void validateCreditEntries(EditText editText, int index) {
        int result = viewModel.calculateRemainingCredit(editText, index);
        TextView creditIndicator = findViewById(R.id.skill_point_indicator);
        if (result == -1) {
            creditIndicator.setText("YOU EXCEEDED MAX SKILL POINTS");
        } else if (result == 0) {
            creditIndicator.setText("You have no pts remaining");
        } else {
            creditIndicator.setText("You have " + String.valueOf(result) + " pts remaining");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        viewModel = ViewModelProviders.of(this).get(StartViewModel.class);
        /*
         * Grab the dialog widgets so we can get info for later
         */
        editPlayerName = findViewById(R.id.player_name_input);

        editPilotPoints = findViewById(R.id.pilot_skill_input);
        editPilotPoints.addTextChangedListener(getTextWatcher(editPilotPoints, 0));
        editFighterPoints = findViewById(R.id.fighter_skill_input);
        editFighterPoints.addTextChangedListener(getTextWatcher(editFighterPoints, 1));
        editTraderPoints = findViewById(R.id.trader_skill_input);
        editTraderPoints.addTextChangedListener(getTextWatcher(editTraderPoints, 2));
        editEngineerPoints = findViewById(R.id.engineer_skill_input);
        editEngineerPoints.addTextChangedListener(getTextWatcher(editEngineerPoints, 3));
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
        Boolean isValid = viewModel.isValid(
                editPlayerName,
                editPilotPoints,
                editFighterPoints,
                editTraderPoints,
                editEngineerPoints);
        if (!isValid) {
            Toast.makeText(getApplicationContext(),
                    "Your inputs are invalid.",
                    Toast.LENGTH_SHORT).show();
        } else {
            viewModel.createNewModel(editPlayerName, difficultySpinner);
            Toast.makeText(getApplicationContext(),
                    "Your inputs are valid.",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(view.getContext(), GameActivity.class);
            startActivity(intent);
        }
    }

    public void onExit(View view) {
        this.finish();
    }

}
