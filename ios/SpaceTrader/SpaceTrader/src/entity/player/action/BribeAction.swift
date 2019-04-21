//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class BribeAction : PlayerEncounterAction {
    
    func doAction(player: Player, otherShip: Ship, es: EncounterState) -> String {
        var retString = ""
        
        retString += "You bribe the police with 50% of your money.\n"
        retString += "You had \(player.wallet.credits) credits.\n"
        player.wallet.credits /= 2
        retString += "You now have \(player.wallet.credits) credits.\n"
        
        es.conclude()
        
        return retString
    }
    
}
