package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.widget.TextView;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.R;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels.ShipViewModel;

public class ShipActivity extends BaseActivity {

    /**
     * View model for Ship activity
     */
    private ShipViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship);

        viewModel = ViewModelProviders.of(this).get(ShipViewModel.class);

        // Indicators
        TextView locationIndicator = findViewById(R.id.indicator_location);
        TextView fuelRemainingIndicator = findViewById(R.id.indicator_fuel_remaining);
        TextView cargoHoldIndicator = findViewById(R.id.indicator_cargo_hold);

        locationIndicator.setText("Location: " + viewModel.getPlayerLocation());
        fuelRemainingIndicator.setText("Fuel Remaining: " + viewModel.getFuelRemaining());
        cargoHoldIndicator.setText("Cargo Hold Usage: " + viewModel.getCargoHoldUsage());
    }

}
