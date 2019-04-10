package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.os.Bundle;
import android.view.View;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.R;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.battle.PirateBattleManager;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action.AttackAction;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action.EncounterState;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action.RunAction;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action.SurrenderAction;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.model.Model;

/**
 * Represents an encounter with a pirate.
 */
public class PirateActivity extends EncounterActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pirate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        eventDescriptionText = findViewById(R.id.pirate_event_description);

        model = Model.getModel();
        player = model.getPlayer();
        bm = new PirateBattleManager(player);

        String startText = bm.startBattle();
        eventDescriptionText.setText(startText);
    }

    /**
     * Updates the description TextView with an extra string.
     *
     * @param extra the new description
     */
    public void updateDescription(String extra) {
        String text = "Your ship health: "
                + player.getShip().getHealth() + "\n";
        text += "The pirate "
                + bm.getOtherShip().getShipType().toString() + ": "
                + bm.getOtherShip().getHealth() + "\n"
                + "----------------------\n" + "\n\n";
        text += extra;
        eventDescriptionText.setText(text);
    }

    /**
     * This method runs when the attack button is clicked in the pirate
     * activity.
     *
     * @param view the context
     */
    public void attackPirateButton(View view) {
        EncounterState es = new EncounterState();
        String description = bm.executeTurn(new AttackAction(), es);
        updateDescription(description);
        if (es.isOver()) {
            transitionToNextActivity();
        }
    }

    /**
     * This method runs when the run button is clicked in the pirate activity.
     *
     * @param view the context
     */
    public void runPirateButton(View view) {
        EncounterState es = new EncounterState();
        String description = bm.executeTurn(new RunAction(), es);
        updateDescription(description);
        if (es.isOver()) {
            transitionToNextActivity();
        }
    }

    /**
     * This method runs when the surrender button is clicked in the pirate
     * activity.
     *
     * @param view the context
     */
    public void surrenderPirateButton(View view) {
        EncounterState es = new EncounterState();
        String description = bm.executeTurn(new SurrenderAction(), es);
        updateDescription(description);
        if (es.isOver()) {
            transitionToNextActivity();
        }
    }
}
