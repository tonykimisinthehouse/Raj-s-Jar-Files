//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class Game: CustomStringConvertible {
    
    private var gameDiff : GameDifficulty
    private var universe : Universe
    
    init(gameDiff : GameDifficulty) {
        self.gameDiff = gameDiff;
        self.universe = Universe()
    }
    
    func getGameDiff() -> GameDifficulty {
        return self.gameDiff;
    }
    
    func getUniverse() -> Universe {
        return self.universe;
    }
    
    public var description: String {
        return "The game difficulty is set to " + getGameDiff()
    }
    
}
