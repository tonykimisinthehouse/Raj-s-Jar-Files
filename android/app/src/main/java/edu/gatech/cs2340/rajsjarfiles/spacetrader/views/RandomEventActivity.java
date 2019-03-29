package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.R;

public class RandomEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_event);
        eventOccurrence();
    }

    private void eventOccurrence() {
        Random rand = new Random();
        int chance = rand.nextInt(100);
        Intent intent;
        if (chance >= 0 && chance <= 99) {
            intent = new Intent(getBaseContext(), PoliceActivity.class);
        } else if (chance > 4 && chance <= 9) {
            intent = new Intent(getBaseContext(), PirateActivity.class);
        } else {
            intent = new Intent(getBaseContext(), GameActivity.class);
        }
        startActivity(intent);
        this.overridePendingTransition(0,0);
    }
}
