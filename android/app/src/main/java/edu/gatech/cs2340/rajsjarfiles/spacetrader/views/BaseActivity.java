package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.model.ModelSaver;

public class BaseActivity extends AppCompatActivity {
    @Override
    public void onPause() {
        super.onPause();
        Log.d("BaseActivity", "in on pause");
        ModelSaver.saveBinaryModel(this.getFilesDir().toString());
    }
}
