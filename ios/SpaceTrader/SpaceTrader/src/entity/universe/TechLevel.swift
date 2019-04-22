//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

struct TechLevel : CustomStringConvertible {
    
    public static let PRE_AGRICULTURE = TechLevel("Pre-Agriculture")
    public static let AGRICULTURE = TechLevel("Agriculture")
    public static let MEDIEVAL = TechLevel("Medieval")
    public static let RENAISSANCE = TechLevel("Renaissance")
    public static let EARLY_INDUSTRIAL = TechLevel("Early Industrial")
    public static let INDUSTRIAL = TechLevel("Industrial")
    public static let POST_INDUSTRIAL = TechLevel("Post-Industrial")
    public static let HI_TECH = TechLevel("Hi-Tech")
    
    public static var values = [
        PRE_AGRICULTURE,
        AGRICULTURE,
        MEDIEVAL,
        RENAISSANCE,
        EARLY_INDUSTRIAL,
        INDUSTRIAL,
        POST_INDUSTRIAL,
        HI_TECH
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
    
    static func getRandomTechLevel() -> TechLevel {
        let r = Int.random(in: 0..<values.count)
        return values[r]
    }
}
