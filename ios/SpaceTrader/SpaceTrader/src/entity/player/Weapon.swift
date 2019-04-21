//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

struct Weapon {
    public static let PULSE_LASER = Weapon(10)
    public static let BEAM_LASER = Weapon(12)
    public static let MILITARY_LASER = Weapon(20)
    
    var strength : Int
    
    public static let values = [
        PULSE_LASER,
        BEAM_LASER,
        MILITARY_LASER
    ]
    
    init(_ strength : Int) {
        self.strength = strength
    }
    
    static func getRandomWeapon() -> Weapon {
        return values[0]
    }
}
