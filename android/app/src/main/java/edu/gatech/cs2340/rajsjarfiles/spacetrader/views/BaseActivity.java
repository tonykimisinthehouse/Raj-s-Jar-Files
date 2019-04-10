package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Base activity so that all activities will override onPause for saving.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    public void onPause() {
        super.onPause();
        Log.d("BaseActivity", "in on pause");
    }
}
