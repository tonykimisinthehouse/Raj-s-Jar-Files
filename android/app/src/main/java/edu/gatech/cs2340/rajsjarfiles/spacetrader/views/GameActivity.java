package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels.GameViewModel;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.R;

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

        // Planet Image
        ImageView imageView = findViewById(R.id.imageView);

        // TODO: experiment with planet color changes
        //imageView.setColorFilter(Color.parseColor("#ffff00"), PorterDuff.Mode.MULTIPLY); //Desert
        //imageView.setColorFilter(Color.parseColor("#0099ff"), PorterDuff.Mode.MULTIPLY); //Ocean
        //imageView.setColorFilter(Color.parseColor("#33cc33"), PorterDuff.Mode.MULTIPLY); //Continental
        //imageView.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.MULTIPLY); //Arctic
        //imageView.setColorFilter(Color.parseColor("#cc3300"), PorterDuff.Mode.MULTIPLY); //TOMB
    }



    public void openMarket(View view) {
        Intent intent = new Intent(view.getContext(), ShipMarketActivity.class);
        startActivity(intent);
    }

}
