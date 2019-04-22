//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class Universe : CustomStringConvertible {
    
    static var MAX_SOLAR_SYSTEMS : Int = 20
    
    var solarSystem : [SolarSystem]
    
    init() {
        self.solarSystem = [SolarSystem]()
        self.createUniverse()
    }
    
    var description: String {
        get {
            return "The universe!"
        }
    }
    
    func createUniverse() {
        for i in 0..<self.solarSystem.count {
            var nameSet : [String] = [String]()
            var coordSet : [Coordinate] = [Coordinate]()
            let name : String = PlanetNames.generateName()
            let coordinate : Coordinate = Coordinate(x: 0, y: 0)
            solarSystem[i] = SolarSystem(name: name, coordinate: coordinate)
        }
    }
    
    func getRandomSolarSystem() -> SolarSystem {
        let r = Int.random(in: 0..<self.solarSystem.count)
        return self.solarSystem[r]
    }
    
}
