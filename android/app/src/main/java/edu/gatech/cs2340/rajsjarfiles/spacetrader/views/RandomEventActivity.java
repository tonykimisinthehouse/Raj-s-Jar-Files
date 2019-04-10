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
        if (chance >= 0 && chance <= 50) {
            intent = new Intent(getBaseContext(), PoliceActivity.class);
        } else if (chance > 50 && chance <= 99) {
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
