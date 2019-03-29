package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action;

import java.util.Random;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Ship;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Weapon;

public class AttackAction implements PlayerEncounterAction {

    /**
     * Player attacks another ship
     *
     * @param player the player
     * @param otherShip the other ship
     * @param es the state of the encounter (over or not over)
     * @return
     */
    public String doAction(Player player, Ship otherShip, EncounterState es) {
        String retString = "";

        //attack ship
        Ship playerShip = player.getShip();

        retString += "You attack the other ship.\n";
        boolean otherShipDead = playerShip.attackShip(otherShip);
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
