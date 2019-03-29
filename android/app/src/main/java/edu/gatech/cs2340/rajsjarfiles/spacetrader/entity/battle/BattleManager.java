package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.battle;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Ship;

public class BattleManager {
    private Player player;
    private Ship playerShip;

    private Ship otherShip;

    public BattleManager(Player player) {
        this.player = player;
        this.playerShip = player.getShip();

        this.otherShip = Ship.getRandomShipWithWeapons();
    }
}
