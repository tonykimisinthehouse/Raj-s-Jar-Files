//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class BattleManager {
    var player : Player
    var playerShip : Ship
    var otherShip : Ship
    
    init(player : Player) {
        self.player = player
        self.playerShip = player.ship!
        self.otherShip = Ship.getRandomShipWithWeapons()
    }
    
    func startBattle() -> String {
        // abstract
    }
    
    func executeTurn(pea : PlayerEncounterAction, es : EncounterState) -> String {
        // abstract
    }
    
    func getOtherShip() -> Ship {
        return self.otherShip
    }
    
}
