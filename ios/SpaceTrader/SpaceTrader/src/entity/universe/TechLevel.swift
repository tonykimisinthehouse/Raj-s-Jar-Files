//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

struct TechLevel {
    
    public static let PRE_AGRICULTURE = TechLevel()
    public static let AGRICULTURE = TechLevel()
    public static let MEDIEVAL = TechLevel()
    public static let RENAISSANCE = TechLevel()
    public static let EARLY_INDUSTRIAL = TechLevel()
    public static let INDUSTRIAL = TechLevel()
    public static let POST_INDUSTRIAL = TechLevel()
    public static let HI_TECH = TechLevel()
    
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
    
    init() {
        
    }
    
    static func getRandomTechLevel() -> TechLevel {
        let r = Int.random(in: 0..<values.count)
        return values[r]
    }
}
