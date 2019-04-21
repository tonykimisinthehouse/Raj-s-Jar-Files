//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class SurrenderAction : PlayerEncounterAction {
    
    func doAction(player: Player, otherShip: Ship, es: EncounterState) -> String {
        var retString = ""
        
        if (player.ship!.hasGoods()) {
            retString += "The pirate crew plunders all of your cargo.\n"
            player.ship!.emptyCargo()
        } else {
            retString += "The pirate crew is angry at your lack of goods and takes your credits instead."
            retString += "You had \(player.wallet.credits) credits.\n"
            player.wallet.credits /= 10
            player.wallet.credits *= 8
            retString += "You now have \(player.wallet.credits) credits.\n"
        }
        
        es.conclude()
        return retString
    }
    
}
