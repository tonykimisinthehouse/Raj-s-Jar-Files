package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Ship;

/**
 * Represents an action the player can do during an encounter.
 */
public interface PlayerEncounterAction {

    /**
     * Executes an action based on the player's selection. Depends on the
     * action implementing this interface.
     *
     * @param player the player
     * @param otherShip the other ship
     * @param es the state of the encounter (over or not over)
     * @return a string detailing what is happening
     */
    public String doAction(Player player, Ship otherShip, EncounterState es);
}
