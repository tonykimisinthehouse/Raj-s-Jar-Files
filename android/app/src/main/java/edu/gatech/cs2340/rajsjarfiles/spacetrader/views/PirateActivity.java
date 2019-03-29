package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.R;

public class PirateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pirate);
    }

    /**
     * This method runs when the run button is clicked in the pirate activity.
     */
    public void runPirateButton(View view) {
        //update code below
        /*Intent intent = new Intent(view.getContext(), RandomEventActivity
                .class);
        startActivity(intent);*/
    }

    /**
     * This method runs when the attack button is clicked in the pirate
     * activity.
     */
    public void attackPirateButton(View view) {
        //update code below
        /*Intent intent = new Intent(view.getContext(), RandomEventActivity
                .class);
        startActivity(intent);*/
    }

    /**
     * This method runs when the surrender button is clicked in the pirate
     * activity.
     */
    public void surrenderPirateButton(View view) {
        //update code below
        /*Intent intent = new Intent(view.getContext(), RandomEventActivity
                .class);
        startActivity(intent);*/
    }
}
