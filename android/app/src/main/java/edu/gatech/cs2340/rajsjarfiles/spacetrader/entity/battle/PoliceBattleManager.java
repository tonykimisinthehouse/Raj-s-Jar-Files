package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.battle;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action.EncounterState;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action.PlayerEncounterAction;

public class PoliceBattleManager extends BattleManager {

    public PoliceBattleManager(Player player) {
        super(player);
    }

    @Override
    public String startBattle() {
        return "The police have found your ship and will be boarding soon! "
                + "You can choose to either attack, run, surrender, "
                + "or bribe the police to make it out alive.";
    }

    public String executeTurn(PlayerEncounterAction pea, EncounterState es) {
        EncounterState newES = new EncounterState();
        String ret = pea.doAction(player, otherShip, newES);
        es.setOver(newES.isOver());
        return ret;
    }
}