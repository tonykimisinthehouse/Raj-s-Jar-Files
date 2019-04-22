//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

struct GameDifficulty : Equatable, CustomStringConvertible {

    public static let BEGINNER = GameDifficulty(0)
    public static let EASY = GameDifficulty(1)
    public static let NORMAL = GameDifficulty(2)
    public static let HARD = GameDifficulty(3)
    public static let IMPOSSIBLE = GameDifficulty(4)
    
    var level : Int
    
    init(_ level : Int) {
        self.level = level
    }
    
    var description: String {
        get {
            if (self == GameDifficulty.BEGINNER) {
                return "BEGINNER"
            }
            switch self {
            case GameDifficulty.BEGINNER:
                return "BEGINNER"
            case GameDifficulty.EASY:
                return "EASY"
            case GameDifficulty.NORMAL:
                return "NORMAL"
            case GameDifficulty.HARD:
                return "HARD"
            case GameDifficulty.IMPOSSIBLE:
                return "IMPOSSIBLE"
            default:
                return "UNKNOWN"
            }
        }
    }
}
