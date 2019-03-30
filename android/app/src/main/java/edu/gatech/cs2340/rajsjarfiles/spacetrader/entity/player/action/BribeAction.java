package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Ship;

public class BribeAction implements PlayerEncounterAction {

    /**
     * Player bribes the police.
     *
     * @param player the player
     * @param otherShip the other ship
     * @param es the state of the encounter (over or not over)
     * @return a description of what's happening
     */
    public String doAction(Player player, Ship otherShip, EncounterState es) {
        String retString = "";

        retString += "You bribe the police with 50% of your money.\n";
        retString += "You had "
                + player.getWallet().getCredits() + " credits.\n";
        player.getWallet().setCredits(player.getWallet().getCredits() / 2);
        retString += "You now have " + player.getWallet().getCredits() + " credits.\n";

        es.conclude();

        return retString;
    }
}
