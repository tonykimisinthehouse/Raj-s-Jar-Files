package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Ship;

public interface PlayerEncounterAction {

    /**
     * Executes an action based on the player's selection. Depends on the
     * action implementing this interface.
     *
     * @param player the player
     * @param otherShip the other ship
     * @param es the state of the encounter (over or not over)
     * @return
     */
    public String doAction(Player player, Ship otherShip, EncounterState es);
}
