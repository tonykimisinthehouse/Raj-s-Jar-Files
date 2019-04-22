//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class Planet : CustomStringConvertible {
    
    var name : String
    var radius : Int
    var orbitRadius : Int
    var orbitAngle : Int
    var isWarpZone : Bool = false
    
    var techLevel : TechLevel
    var habitats : Habitats
    var species : Species
    var resourceClass : ResourceClassification
    var marketplace : Marketplace
    
    init(builder : PlanetBuilder) {
        self.name = builder.name
        self.radius = builder.radius
        self.orbitRadius = builder.orbitRadius
        self.orbitAngle = builder.orbitAngle
        self.techLevel = builder.techLevel
        self.habitats = builder.habitats
        self.species = builder.species
        self.resourceClass = builder.resourceClass
        self.marketplace = builder.marketplace
    }
    
    func getDist(other : Planet) -> Int {
        let angleRaw = abs(orbitAngle - other.orbitAngle)
        let angle = (angleRaw <= 180 ? angleRaw : 360 - angleRaw)
        
        let a = self.orbitRadius
        let b = other.orbitRadius
        
        let sqrtVal = (a*a)+(b*b)-(2*a*b*cos(Double(angle)))
        var c = sqrt(sqrtVal)
        return c
    }
    
    class func distBetween(p1 : Planet, p2 : Planet) -> Int {
        return p1.getDist(other: p2)
    }
    
    var description: String {
        get {
            return "Planet" // TODO not full description implementation
        }
    }
    
    class PlanetBuilder {
        
        static let MIN_RADIUS = 2
        static let MAX_RADIUS = 5
        
        var name : String
        
        var radius : Int
        var orbitRadius : Int
        var orbitAngle : Int
        
        var techLevel : TechLevel
        var habitats : Habitats
        var species : Species
        var resourceClass : ResourceClassification
        var marketplace : Marketplace
        
        init(name : String, orbitRadius : Int, orbitAngle : Int) {
            self.name = name
            self.orbitRadius = orbitRadius
            self.orbitAngle = orbitAngle
        }
        
        func radius(r : Int) -> PlanetBuilder {
            self.radius = r
            return self
        }
        
        func techLevel(tl : TechLevel) -> PlanetBuilder {
            self.techLevel = tl
            return self
        }
        
        func habitats(ha : Habitats) -> PlanetBuilder {
            self.habitats = ha
            return self
        }
        
        func species(sp : Species) -> PlanetBuilder {
            self.species = sp
            return self
        }
        
        func resourceClass(rc : ResourceClassification) -> PlanetBuilder {
            self.resourceClass = rc
            return self
        }
        
        func build() -> Planet {
            if (self.radius == 0) {
                self.radius = self.getRandomRadius()
            }
            self.habitats = Habitats.getRandomHabitat()
            self.resourceClass = ResourceClassification.getRandomResourceClass(self.habitats)
            self.species = Species.getRandomHabitableSpecies(self.habitats)
            self.techLevel = TechLevel.getRandomTechLevel()
            self.event = Events.getRandomEvent()
            self.marketplace = Marketplace(self.name, self.techlevel, self.event, self.resourceClass)
            return Planet(builder: self)
        }
        
    }

    
}
