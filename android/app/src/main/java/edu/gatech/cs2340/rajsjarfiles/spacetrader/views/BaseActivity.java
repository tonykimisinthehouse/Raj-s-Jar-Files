package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class BaseActivity extends AppCompatActivity {
    @Override
    public void onPause() {
        super.onPause();
        Log.d("BaseActivity", "in on pause");
    }
}
