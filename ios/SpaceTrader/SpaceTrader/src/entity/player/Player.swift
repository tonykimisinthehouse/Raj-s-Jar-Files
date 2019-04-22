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
    
    var _points : [Int]
    
    var points : [Int] {
        get {
            return _points
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
            
            self._points = newValue // TODO potential cyclic loop
        }
    }
    
    var ship : Ship?
    var wallet : Wallet
    var location : Location
    
    init(builder : PlayerBuilder) {
        self.name = builder.name
        self._points = builder.points
        self.ship = builder.ship
        self.wallet = builder.wallet
        self.location = builder.location
    }
    
    func checkCargoCapacityEnough(quantity : Int) -> Bool {
        return quantity <= self.ship!.availableCargoCapacity
    }
    
    func setNoShip() {
        self.ship = nil
    }
    
    func travel(destinationSS : SolarSystem, destinationP : Planet) -> Bool {
        let statusCode : Int = location.checkIfTravelPossible(destinationSS: destinationSS, destinationP: destinationP)
        
        if (statusCode == -1) {
            let travelFare : Int = 1000
            if (self.wallet.checkCreditEnough(marketPrice: travelFare)) {
                self.wallet.useCredits(amount: travelFare)
                self.location = Location(solarSystem: destinationSS, planet: destinationSS.getPlanetWithWarp())
            }
            return true
        }
        
        return false
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
            if (self.location == nil) {
                self.location = Location()
            }
            
            return Player(builder: self)
        }
        
    }
    
}
