package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.R;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.battle.BattleManager;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.battle.PoliceBattleManager;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action.AttackAction;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action.BribeAction;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action.EncounterState;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action.RunAction;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action.SubmitAction;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.model.Model;

public class PoliceActivity extends EncounterActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_police;
    }

    private TextView eventDescriptionText;

    private Model model;
    private Player player;
    private BattleManager bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        eventDescriptionText = findViewById(R.id.police_event_description);

        model = Model.getCurrent();
        player = model.getPlayer();
        bm = new PoliceBattleManager(player);

        String startText = bm.startBattle();
        eventDescriptionText.setText(startText);
    }

    /**
     * Opens a dialog describing what happened at the end of the encounter
     * and opens the game actvitiy.
     */
    public void transitionToNextActivity() {
        String text;
        final Intent intent;
        if (player.getShip().getHealth() == 0) {
            text = "You died! Better luck next time :)";
            intent = new Intent(getApplicationContext(), StartActivity.class);
        } else {
            text = "You arrived relatively safely at your destination.";
            intent = new Intent(getApplicationContext(), GameActivity.class);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(text)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(intent);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * Updates the description TextView with an extra string.
     *
     * @param extra the new description
     */
    public void updateDescription(String extra) {
        String text = "Your ship health: "
                + player.getShip().getHealth() + "\n";
        text += "The police "
                + bm.getOtherShip().getShipType().toString() + ": "
                + bm.getOtherShip().getHealth() + "\n"
                + "----------------------\n" + "\n\n";
        text += extra;
        eventDescriptionText.setText(text);
    }

    /**
     * This method runs when the attack button is clicked in
     * the police activity.
     * @param view the context
     */
    public void attackPoliceButton(View view) {
        EncounterState es = new EncounterState();
        String description = bm.executeTurn(new AttackAction(), es);
        updateDescription(description);
        if (es.isOver()) {
            transitionToNextActivity();
        }
    }

    /**
     * This method runs when the run button is clicked in the police activity.
     *
     * @param view the context
     */
    public void runPoliceButton(View view) {
        EncounterState es = new EncounterState();
        String description = bm.executeTurn(new RunAction(), es);
        updateDescription(description);
        if (es.isOver()) {
            transitionToNextActivity();
        }
    }

    /**
     * This method runs when the surrender button is clicked in the police
     * activity.
     *
     * @param view the context
     */
    public void submitPoliceButton(View view) {
        EncounterState es = new EncounterState();
        String description = bm.executeTurn(new SubmitAction(), es);
        updateDescription(description);
        if (es.isOver()) {
            transitionToNextActivity();
        }
    }

    /**
     * This method runs when the bribe button is clicked in the police activity.
     *
     * @param view the context
     */
    public void bribePoliceButton(View view) {
        EncounterState es = new EncounterState();
        String description = bm.executeTurn(new BribeAction(), es);
        updateDescription(description);
        if (es.isOver()) {
            transitionToNextActivity();
        }
    }
}
