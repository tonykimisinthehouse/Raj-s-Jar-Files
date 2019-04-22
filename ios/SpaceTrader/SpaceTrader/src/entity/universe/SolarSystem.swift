//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class SolarSystem : Equatable {
    
    static func == (lhs: SolarSystem, rhs: SolarSystem) -> Bool {
        return lhs.name == rhs.name // TODO may not be an ideal equals() comparison
    }
    
    static let MIN_PLANETS : Int = 1
    static let MAX_PLANETS : Int = 10
    
    var planets : [Planet]
    var planetMap : [String : Planet]
    var name : String
    var coordinate : Coordinate
    
    init(name : String, coordinate : Coordinate) {
        self.name = name
        self.coordinate = coordinate
        self.planets = SolarSystem.generatePlanets(size: Int.random(in: 0..<SolarSystem.MAX_PLANETS))
        self.planetMap = [String : Planet]()
        self.computePlanetMap()
    }
    
    func assignWarpZone() {
        var maxRadius = Int.min
        var maxPlanet : Planet = self.planets[0]
        for planet in self.planets {
            if (planet.radius > maxRadius) {
                maxRadius = planet.radius
                maxPlanet = planet
            }
        }
        maxPlanet.isWarpZone = true
    }
    
    class func generatePlanets(size : Int) -> [Planet] {
        var planets : [Planet] = [Planet]()
        var nameList = PlanetNames.generateName(numOfNames: planets.count)
        
        var orbitRadius : Int = 0
        var orbitAngle : Int = 0
        for i in 0..<nameList.count {
            orbitRadius = Int.random(in: 0..<4)
            orbitAngle = Int.random(in: 0..<360)
            planets[i] = Planet.PlanetBuilder(name: nameList[i], orbitRadius: orbitRadius, orbitAngle: orbitAngle).build()
        }
        return planets
    }
    
    func computePlanetMap() {
        for planet in self.planets {
            self.planetMap[planet.name] = planet
        }
    }
    
    func getRandomPlanet() -> Planet {
        let r = Int.random(in: 0..<self.planets.count)
        return self.planets[r]
    }
    
    func getPlanetWithWarp() -> Planet {
        for planet in self.planets {
            if planet.isWarpZone {
                return planet
            }
        }
        
        return self.planets[0]
    }
    
}
