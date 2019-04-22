//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class Player {
    
    let MAX_POINTS : Int = 16
    var name : String
    
    var points : [Int] {
        get {
            return self.points
        }
        set {
            var sum : Int = 0
            for i in 0..<newValue.count {
                if newValue[i] < 0 {
                    fatalError("Point at index \(i) is less than zero.")
                }
                sum += newValue[i]
            }
            
            if (sum != MAX_POINTS) {
                fatalError("Points do not sum to \(MAX_POINTS).")
            }
            
            self.points = newValue // TODO potential cyclic loop
        }
    }
    
    var ship : Ship?
    var wallet : Wallet
    var location : Location
    
    init(builder : PlayerBuilder) {
        self.name = builder.name
        self.points = builder.points
        self.wallet = builder.wallet
        self.ship = builder.ship
    }
    
    func checkCargoCapacityEnough(quantity : Int) -> Bool {
        return quantity <= self.ship!.availableCargoCapacity
    }
    
    func setNoShip() {
        self.ship = nil
    }
    
    func travel(destinationSS : SolarSystem, destinationP : Planet) -> Bool {
        var statusCode : Int = location.checkIfTravelPossible(destinationSS: destinationSS, destinationP: destinationP)
        
        if (statusCode == -1) {
            let travelFare : Int = 1000
            if (self.wallet.checkCreditEnough(marketPrice: travelFare)) {
                self.wallet.useCredits(amount: travelFare)
                self.location = Location(solarSystem: destinationSS, planet: destinationSS.getPlanetWithWarp())
            }
            return true
        }
    }
    
    class PlayerBuilder {
        var name : String
        var points : [Int]
        var ship : Ship
        var wallet : Wallet
        var location : Location!
        
        init(name : String) {
            self.name = name
            self.points = [4, 4, 4, 4]
            self.wallet = Wallet(credits: 9999)
            self.ship = Ship(type: ShipType.Gnat)
            self.location = nil
        }
        
        func points(points : [Int]) -> PlayerBuilder {
            self.points = points
            return self
        }
        
        func location(ss : SolarSystem) -> PlayerBuilder {
            self.location = Location(solarSystem: ss)
            return self
        }
        
        func credits(credits : Int) -> PlayerBuilder {
            self.wallet.credits = credits
            return self
        }
        
        func ship(ship : Ship) -> PlayerBuilder {
            self.ship = ship
            return self
        }
        
        func build() -> Player {
            return Player(builder: self)
        }
        
    }
    
}
