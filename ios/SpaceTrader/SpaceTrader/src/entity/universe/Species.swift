//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

struct Species : CustomStringConvertible {
    
    public static let HUMANOID = Species("Humanoid")
    public static let MACHINE = Species("Machine")
    public static let FUNGOID = Species("Fungoid")
    public static let REPTILIAN = Species("Reptilian")
    
    public static var values = [
        HUMANOID,
        MACHINE,
        FUNGOID,
        REPTILIAN
    ]
    
    var description: String {
        get {
            return self.name
        }
    }
    
    var name : String
    
    init(_ name : String) {
        self.name = name
    }
    
    static func getRandomHabitableSpecies(habitats : Habitats) -> Species {
        let r = Int.random(in: 0..<values.count)
        return values[r]
    }
}
