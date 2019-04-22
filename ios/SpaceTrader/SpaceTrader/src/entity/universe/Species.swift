//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

struct Species {
    
    public static let HUMANOID = Species()
    public static let MACHINE = Species()
    public static let FUNGOID = Species()
    public static let REPTILIAN = Species()
    
    public static var values = [
        HUMANOID,
        MACHINE,
        FUNGOID,
        REPTILIAN
    ]
    
    init() {
        
    }
    
    static func getRandomHabitableSpecies(habitats : Habitats) -> Species {
        let r = Int.random(in: 0..<values.count)
        return values[r]
    }
}
