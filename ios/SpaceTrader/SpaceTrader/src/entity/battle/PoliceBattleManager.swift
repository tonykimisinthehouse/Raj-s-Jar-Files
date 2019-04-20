//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class PoliceBattleManager : BattleManager {
    
    override init(player : Player) {
        super.init(player: player)
    }
    
    override func startBattle() -> String {
        return "The police have found your ship and will be boarding soon! Attack, run, surrender, or bribe?"
    }
    
}
