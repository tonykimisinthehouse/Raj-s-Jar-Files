package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.R;

/**
 * Main activity while playing the game
 */
public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void openMarket(View view) {
        Intent intent = new Intent(view.getContext(), ShipMarketActivity.class);
        startActivity(intent);
    }

}
