//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class Location {
    
    var planet : Planet
    var solarSystem : SolarSystem
    
    convenience init() {
        self.init(solarSystem: Model.current.game.getUniverse().getRandomSolarSystem())
    }
    
    convenience init(solarSystem : SolarSystem) {
        self.init(solarSystem: solarSystem, planet: solarSystem.getRandomPlanet())
    }
    
    init(solarSystem : SolarSystem, planet : Planet) {
        self.setSolarSystem(solarSystem)
        self.setPlanet(planet)
    }
    
    func setSolarSystem(_ solarSystem : SolarSystem) {
        self.solarSystem = solarSystem
    }
    
    func checkIfTravelPossible(destinationSS : SolarSystem, destinationP : Planet) -> Int {
        if (self.checkIfTravelInSS(destinationSS: destinationSS)) {
            return 0
        } else {
            if (planet.isWarpZone) {
                return 1
            }
        }
        return -1
    }
    
    func checkIfTravelInSS(destinationSS : SolarSystem) -> Bool {
        return solarSystem == destinationSS
    }
    
    func calculateFuelRq(destinationP : Planet) -> Int {
        let distance = self.planet.getDist(other: destinationP)
        return distance + 5
    }
    
    func setPlanet(_ planet : Planet) {
        self.planet = planet
    }
    
    func getPlanet() -> Planet {
        return self.planet
    }
    
}
