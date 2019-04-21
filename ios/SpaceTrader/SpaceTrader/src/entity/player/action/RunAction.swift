//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class RunAction : PlayerEncounterAction {
    
    func doAction(player: Player, otherShip: Ship, es: EncounterState) -> String {
        let retString = "You escape to live another day!"
        es.conclude()
        return retString
    }
    
}
