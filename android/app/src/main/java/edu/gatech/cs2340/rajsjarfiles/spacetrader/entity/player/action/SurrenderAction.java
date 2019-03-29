package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Ship;

public class SurrenderAction implements PlayerEncounterAction {

    /**
     * Player surrenders to a pirate.
     *
     * @param player the player
     * @param otherShip the other ship
     * @param es the state of the encounter (over or not over)
     * @return
     */
    public String doAction(Player player, Ship otherShip, EncounterState es) {
        String retString = "";

        //You lose cargo, and if you don't have cargo, you lose 80% of your cash

        return "";
    }
}
