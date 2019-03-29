package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.R;

public class PoliceActivity extends EncounterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police);
    }

//    /**
//     * This method runs when the run button is clicked in the police activity.
//     */
//    public void runPoliceButton(View view) {
//        //update code below
//        /*Intent intent = new Intent(view.getContext(), RandomEventActivity
//                .class);
//        startActivity(intent);*/
//    }
//
//    /**
//     * This method runs when the surrender button is clicked in the police
//     * activity.
//     */
//    public void surrenderPoliceButton(View view) {
//        //update code below
//        /*Intent intent = new Intent(view.getContext(), RandomEventActivity
//                .class);
//        startActivity(intent);*/
//    }
//
//    /**
//     * This method runs when the bribe button is clicked in the police activity.
//     */
//    public void bribePoliceButton(View view) {
//        //update code below
//        /*Intent intent = new Intent(view.getContext(), RandomEventActivity
//                .class);
//        startActivity(intent);*/
//    }
}
