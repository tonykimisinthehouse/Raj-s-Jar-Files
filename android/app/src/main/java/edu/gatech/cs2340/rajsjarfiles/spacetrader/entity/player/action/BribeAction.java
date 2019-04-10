package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Ship;

/**
 * Represents a player's bribe action.
 */
public class BribeAction implements PlayerEncounterAction {

    @Override
    public String doAction(Player player, Ship otherShip, EncounterState es) {
        String retString = "";

        retString += "You bribe the police with 50% of your money.\n";
        retString += "You had "
                + player.getCredits() + " credits.\n";
        int newCredits = player.setCredits(0.5f);
        retString += "You now have "
                + newCredits + " credits.\n";

        es.conclude();

        return retString;
    }
}
