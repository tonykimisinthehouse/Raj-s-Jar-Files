package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.battle;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action.EncounterState;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action.PlayerEncounterAction;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Ship;

/**
 * Handles an encounter between the player and another ship.
 */
public abstract class BattleManager {
    final Player player;

    final Ship otherShip;

    /**
     * Creates an encounter between the ship and another ship.
     *
     * @param player the player
     */
    public BattleManager(Player player) {
        this.player = player;
        Ship playerShip = player.getShip();

        this.otherShip = Ship.getRandomShipWithWeapons();
    }

    /**
     * Starts the battle.
     *
     * @return a string detailing what is happening in the game
     */
    public abstract String startBattle();

    /**
     * Executes one turn for a given player action.
     *
     * @param pea the player's action
     * @param es whether the encounter is over or not
     * @return a string detailing what is happening in the game
     */
    public abstract String executeTurn(
            PlayerEncounterAction pea, EncounterState es);

    /**
     * @return the other ship in the encounter
     */
    public Ship getOtherShip() {
        return otherShip;
    }

    public String getOtherShipString() {
        return otherShip.toStringShipType();
    }

    public int getOtherShipHealth() {
        return otherShip.getHealth();
    }
}
