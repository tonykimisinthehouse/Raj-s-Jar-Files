package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.R;

/**
 * Activity for a random event that can occur while traveling.
 */
public class RandomEventActivity extends AppCompatActivity {
    private static final int POLICE_UPPER = 40;
    private static final int PIRATE_UPPER = 80;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_event);
        eventOccurrence();
    }

    /**
     * Determines which random event will occur, if any.
     */
    private void eventOccurrence() {
        Random rand = new Random();
        int chance = rand.nextInt(100);
        Intent intent;
        if ((chance >= 0) && (chance <= POLICE_UPPER)) {
            intent = new Intent(getBaseContext(), PoliceActivity.class);
        } else if ((chance > POLICE_UPPER) && (chance <= PIRATE_UPPER)) {
            intent = new Intent(getBaseContext(), PirateActivity.class);
        } else {
            intent = new Intent(getBaseContext(), GameActivity.class);
        }
        RandomEventActivity.this.finish();
        startActivity(intent);
        this.overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed() {
        //left blank so the player can't return
    }
}
