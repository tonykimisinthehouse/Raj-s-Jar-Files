package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.battle;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action.EncounterState;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action.PlayerEncounterAction;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Ship;

public abstract class BattleManager {
    protected Player player;
    protected Ship playerShip;

    protected Ship otherShip;

    public BattleManager(Player player) {
        this.player = player;
        this.playerShip = player.getShip();

        this.otherShip = Ship.getRandomShipWithWeapons();
    }

    /**
     * Starts the battle.
     *
     * @return a string detailing what is happening in the game
     */
    public abstract String startBattle();

    public abstract String executeTurn(PlayerEncounterAction pea, EncounterState es);

    public Ship getOtherShip() {
        return otherShip;
    }
}
