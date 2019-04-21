//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class AttackAction : PlayerEncounterAction {
    
    func doAction(player: Player, otherShip: Ship, es: EncounterState) -> String {
        var retString = ""
        
        let playerShip = player.ship!
        
        retString += "You attack the other ship.\n"
        let otherShipDead = playerShip.attackShip(otherShip)
        
        if (otherShipDead) {
            retString += "The other ship explodes.\n"
            es.conclude()
            return retString
        }
        
        retString += "The other ship attacks you.\n"
        let playerShipDead = otherShip.attackShip(playerShip)
        
        if (playerShipDead) {
            retString += "Your ship explodes. You died!\n"
            es.conclude()
            return retString
        }
        
        return retString
    }
    
}
