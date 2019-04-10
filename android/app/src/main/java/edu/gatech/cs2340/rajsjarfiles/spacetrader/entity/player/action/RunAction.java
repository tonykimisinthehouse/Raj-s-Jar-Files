package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action;

import java.util.Random;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Ship;

public class RunAction implements PlayerEncounterAction {

    @Override
    public String doAction(Player player, Ship otherShip, EncounterState es) {
        String retString = "";

        //chance that you escape

        //ability to escape I think depends on pilot skill
        Random rand = new Random();
        int escapeChance = rand.nextInt(10);
        if (escapeChance < 4) { //this is really arbitrary
            retString += "You escape to live another day!";
            es.conclude();
        } else {
            retString += "You can't seem to shake the other ship"
                    + " and it attacks you.";
            boolean dead = otherShip.attackShip(player.getShip());
            if (dead) {
                retString += "Your ship explodes. You died!";
            }
        }
        return retString;
    }
}
