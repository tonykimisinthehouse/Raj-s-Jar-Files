package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.battle.BattleManager;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.model.Model;

/**
 * An activity for an encounter between a player and another ship.
 */
public abstract class EncounterActivity extends AppCompatActivity {

    protected TextView eventDescriptionText;

    protected Model model;
    protected Player player;
    protected BattleManager bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }

    /**
     * @return the encounter layout id
     */
    protected abstract int getLayoutId();

    /**
     * Opens a dialog describing what happened at the end of the encounter
     * and opens the game activity.
     */
    public void transitionToNextActivity() {
        String text = "";
        final Intent intent;
        if (player.getShip().getHealth() == 0) {
            text += "You died! Better luck next time :)";
            intent = new Intent(getApplicationContext(), StartActivity.class);
        } else {
            text += "You arrived relatively safely at your destination.";
            intent = new Intent(getApplicationContext(), GameActivity.class);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(text)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EncounterActivity.this.finish();
                        startActivity(intent);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

        Window window = alert.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
//        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
    }

    @Override
    public void onBackPressed() {
        //left blank so the player can't return
    }
}
