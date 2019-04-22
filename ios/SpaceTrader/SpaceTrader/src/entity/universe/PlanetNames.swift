//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class PlanetNames {
    
    static var NAMES : [String] = [
        "Acamar",
        "Calondia"
        // TODO not all names are in here from the Android/Java version
    ]
    
    static let MIN_MOD = 1
    static let MAX_MOD = 5
    
    class func generateName() -> String {
        var name : String = ""
        let addMod : Int = Int.random(in: 0..<7)
        let randName : Int = Int.random(in: 0..<NAMES.count)
        if (addMod == 0) {
            let iter : Int = Int.random(in: MIN_MOD..<MAX_MOD)
            for _ in 0..<iter {
                let randLetter = Character(UnicodeScalar(Int.random(in: 65..<91))!)
                name += String(randLetter)
            }
            name += "-"
        }
        name += NAMES[randName]
        return name
    }
    
    class func generateName(numOfNames : Int) -> [String] {
        return NAMES
    }
    
}
