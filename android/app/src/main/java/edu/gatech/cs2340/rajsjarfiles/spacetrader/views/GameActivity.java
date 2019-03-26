package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels.GameViewModel;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.R;

/**
 * Main activity while playing the game
 */
public class GameActivity extends BaseActivity {

    /**
     * View model for Game activity
     */
    private GameViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        viewModel = ViewModelProviders.of(this).get(GameViewModel.class);

        // Indicators
        TextView planetNameIndicator = findViewById(R.id.indicator_planet_name);
        TextView orbitRadiusIndicator = findViewById(
                R.id.indicator_orbit_radius);
        TextView techLevelIndicator = findViewById(R.id.indicator_tech_level);
        TextView resourceIndicator = findViewById(R.id.indicator_resources);
        TextView eventIndicator = findViewById(R.id.indicator_event);
        TextView speciesIndicator = findViewById(R.id.indicator_species);

        planetNameIndicator.setText(viewModel.getPlanetName());
        orbitRadiusIndicator.setText("Orbit Radius: "
                + String.valueOf(viewModel.getPlanetOrbitRadius()));
        techLevelIndicator.setText(
                "Tech Level: " + viewModel.getPlanetTechLevel());
        resourceIndicator.setText(
                "Resources: " + viewModel.getPlanetResources());
        speciesIndicator.setText("Species: " + viewModel.getPlanetSpecies());
        eventIndicator.setText("Event: " + viewModel.getPlanetEvent());

        // Set filter on planet Image
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setColorFilter(
                Color.parseColor(
                        viewModel.getPlanetColorHex()),
                PorterDuff.Mode.MULTIPLY); //Desert
    }

    /**
     * Opens the market activity.
     *
     * @param view the current view
     */
    public void openMarket(View view) {
        Intent intent = new Intent(view.getContext(), MarketActivity.class);
        startActivity(intent);
    }

}
