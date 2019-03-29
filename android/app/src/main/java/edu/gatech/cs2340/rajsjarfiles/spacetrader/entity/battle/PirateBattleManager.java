package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.battle;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action.EncounterState;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action.PlayerEncounterAction;

public class PirateBattleManager extends BattleManager {

    public PirateBattleManager(Player player) {
        super(player);
    }

    @Override
    public String startBattle() {
        return "";
    }

    public String executeTurn(PlayerEncounterAction pea, EncounterState es) {
        return "";
    }
}
