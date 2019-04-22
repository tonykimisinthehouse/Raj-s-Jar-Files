//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class Model {
    
    public static var current : Model!
    
    var player : Player?
    var game : Game?
    
    init(name : String, points : [Int], difficulty : GameDifficulty) {
        self.player = nil
        self.game = nil
        Model.current = self
        
        self.game = Game(gameDiff: difficulty)
        self.player = Player.PlayerBuilder(name: name).points(points: points).build()
        self.player!.ship!.addWeapon(w: Weapon.PULSE_LASER)
    }
    
}
