//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class Coordinate : Hashable {
    
    static func == (lhs: Coordinate, rhs: Coordinate) -> Bool {
        if (lhs == rhs) {
            return true
        }
        return (lhs.x == rhs.x) && (lhs.y == rhs.y)
    }
    
    func hash(into hasher: inout Hasher) {
        var h : Int = 0
        h = (h * 397) ^ self.x
        h += (h * 397) ^ self.y
        hasher.combine(h)
    }
    
    var x : Int
    var y : Int
    
    static let MAX_X : Int = 150
    static let MAX_Y : Int = 100
    
    init() {
        self.x = Int.random(in: (Coordinate.MAX_X / 2)..<Coordinate.MAX_X)
        self.y = Int.random(in: (Coordinate.MAX_Y / 2)..<Coordinate.MAX_Y)
    }
    
    init(x : Int, y : Int) {
        self.x = x
        self.y = y
    }
    
    func distTo(c : Coordinate) -> Double {
        let dx : Double = Double(c.x - x)
        let dy : Double = Double(c.y - y)
        return abs( sqrt( pow(dx, 2.0) + pow(dy, 2.0) ) )
    }
    
}
