//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class PirateBattleManager : BattleManager {
    
    override init(player: Player) {
        super.init(player: player)
    }
    
    override func startBattle() -> String {
        return "The pirates have found your ship and will be boarding soon! Run, attack, or surrender?"
    }
    
    override func executeTurn(pea: PlayerEncounterAction, es: EncounterState) -> String {
        let newES = EncounterState()
        let ret = pea.doAction(player, otherShip, newES)
        es.setOver(newES.isOver())
        return ret
    }
    
}
