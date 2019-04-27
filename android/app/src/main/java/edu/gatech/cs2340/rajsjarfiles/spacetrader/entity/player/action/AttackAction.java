package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Ship;

/**
 * Represents player attack action.
 */
public class AttackAction implements PlayerEncounterAction {

    @Override
    public String doAction(Player player, Ship otherShip, EncounterState es) {
        String retString = "";

        //attack ship
        Ship playerShip = player.getShip();

        retString += "You attack the other ship.\n";
        boolean otherShipDead = player.attackShip(otherShip);
        if (otherShipDead) {
            retString += "The other ship explodes.\n";
            es.conclude();
            return retString;
        }

        retString += "The other ship attacks you.\n";
        boolean playerShipDead = otherShip.attackShip(playerShip);
        if (playerShipDead) {
            retString += "Your ship explodes. You died!\n";
            es.conclude();
            return retString;
        }

        return retString;
    }
}
