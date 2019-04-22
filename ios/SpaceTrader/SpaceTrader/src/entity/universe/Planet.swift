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
    var event : Events
    
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
        self.event = builder.event
    }
    
    func getDist(other : Planet) -> Int {
        let angleRaw = abs(orbitAngle - other.orbitAngle)
        let angle = (angleRaw <= 180 ? angleRaw : 360 - angleRaw)
        
        let a = Double(self.orbitRadius)
        let b = Double(other.orbitRadius)
        
        var sqrtVal = (a*a)+(b*b)
        sqrtVal -= (2.0*a*b*cos(Double(angle)))
        let c = Int(sqrt(sqrtVal))
        
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
        
        let MIN_RADIUS = 2
        let MAX_RADIUS = 5
        
        var name : String
        
        var radius : Int
        var orbitRadius : Int
        var orbitAngle : Int
        
        var techLevel : TechLevel
        var habitats : Habitats
        var species : Species
        var resourceClass : ResourceClassification
        var marketplace : Marketplace
        var event: Events
        
        init(name : String, orbitRadius : Int, orbitAngle : Int) {
            self.name = name
            self.radius = 0
            self.orbitRadius = orbitRadius
            self.orbitAngle = orbitAngle
            self.techLevel = TechLevel.AGRICULTURE
            self.habitats = Habitats.DESERT
            self.species = Species.MACHINE
            self.resourceClass = ResourceClassification.ARTISTIC
            self.marketplace = Marketplace(pn: "MarketPlace", techLevel: TechLevel.AGRICULTURE, event: Events.BOREDOM, resource: ResourceClassification.ARTISTIC)
            self.event = Events.BOREDOM
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
            self.resourceClass = ResourceClassification.getRandomResourceClass()
            self.species = Species.getRandomHabitableSpecies(habitats: self.habitats)
            self.techLevel = TechLevel.getRandomTechLevel()
            self.event = Events.getRandomEvent()
            self.marketplace = Marketplace(pn: self.name, techLevel: self.techLevel, event: self.event, resource: self.resourceClass)
            return Planet(builder: self)
        }
        
        func getRandomRadius() -> Int {
            return Int.random(in: self.MIN_RADIUS..<self.MAX_RADIUS)
        }
        
    }

    
}
