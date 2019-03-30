package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * An activity for an encounter between a player and another ship.
 */
public abstract class EncounterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }

    /**
     * @return the encounter layout id
     */
    protected abstract int getLayoutId();
}
