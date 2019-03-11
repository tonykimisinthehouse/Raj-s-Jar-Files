package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels.GameViewModel;

/**
 * Main activity while playing the game
 */
public class GameActivity extends AppCompatActivity {

    /**
     * View model for Game activity
     */
    private GameViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        viewModel = ViewModelProviders.of(this).get(GameViewModel.class);
    }

    public void openMarket(View view) {
        Intent intent = new Intent(view.getContext(), ShipMarketActivity.class);
        startActivity(intent);
    }

}
