package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.R;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels.ShipViewModel;

/**
 * Ship activity for ship-related actions and monitoring.
 */
public class ShipActivity extends AppCompatActivity {

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
