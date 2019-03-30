package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.R;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Ship;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.model.Model;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels.ShipViewModel;

public class ShipActivity extends BaseActivity {

    /**
     * View model for Ship activity
     */
    private ShipViewModel viewModel;

    private TextView locationIndicator;
    private TextView fuelRemainingIndicator;
    private TextView cargoHoldIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship);

        viewModel = ViewModelProviders.of(this).get(ShipViewModel.class);

        // Indicators
        this.locationIndicator = findViewById(R.id.indicator_location);
        this.fuelRemainingIndicator = findViewById(R.id.indicator_fuel_remaining);
        this.cargoHoldIndicator = findViewById(R.id.indicator_cargo_hold);
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.locationIndicator.setText("Location: " + viewModel.getPlayerLocation());
        this.fuelRemainingIndicator.setText("Fuel Remaining: " + viewModel.getFuelRemaining());
        this.cargoHoldIndicator.setText("Cargo Hold Usage: " + viewModel.getCargoHoldUsage());
    }

    public void chooseDestination(View view) {
        Intent intent = new Intent(this, TravelActivity.class);
        startActivity(intent);
    }

}
