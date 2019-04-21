//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class SubmitAction : PlayerEncounterAction {
    
    func doAction(player: Player, otherShip: Ship, es: EncounterState) -> String {
        var retString = ""
        
        if (player.ship!.hasIllegalGoods()) {
            retString += "The police find illegal goods in your cargo hold and confiscate all your goods.\n"
            retString += "You pay a fine.\n"
            
            player.wallet.credits /= 2
            
            retString += "Your ship was \(player.ship!).\n"
            player.ship!.removeIllegalGoods()
            retString += "Your ship is now \(player.ship!).\n"
            
            retString += "You had \(player.wallet.credits) credits.\n"
            player.wallet.credits /= 2
            retString += "You now have \(player.wallet.credits) credits.\n"
            
        } else {
            retString += "The police find nothing illegal in your cargo hold and bid you a nice day."
        }
        
        es.conclude()
        return retString
    }
    
}
