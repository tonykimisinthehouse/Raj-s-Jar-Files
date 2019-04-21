//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class Ship {
    
    var type : ShipType
    var cargo : [Good : Item]
    var totalCap : Int
    var usedCap : Int
    var fuel : Int
    var health : Int
    var weapons : [Weapon]
    
    init() {
        self.init(cargoSize: 10, type: ShipType.Gnat)
    }
    
    init(type : ShipType) {
        self.init(cargoSize: 10, type: type)
    }
    
    init(cargoSize : Int, type : ShipType) {
        self.cargo = [Good : Item]()
        self.type = type
        self.totalCap = cargoSize
        self.usedCap = 0
        self.health = type.maxHealth
        self.weapons = [Weapon]() // TODO initialize weapons array
        self.fuel = 999999
    }
    
    func hasFuels(requiredFuel : Int) -> Bool {
        return self.fuel >= requiredFuel
    }
    
    func subFuel(fuelUsed : Int) {
        if (self.hasFuels(requiredFuel: fuelUsed)) {
            self.fuel -= fuelUsed
        }
    }
    
    var availableCargoCapacity : Int {
        get {
            return self.totalCap - self.usedCap
        }
    }
    
    func hasGoods() -> Bool {
        return self.usedCap > 0
    }
    
    func addGood(item : Item) {
        let quantity = item.quantity
        let price = item.price
        let good = item.good
        
        if (self.availableCargoCapacity < quantity) {
            fatalError("Not enough cargo!")
        }
        
        if (self.cargo[good] != nil) {
            self.cargo[good].addQuantity(quantity: quantity)
        } else {
            self.cargo[good] = Item.ItemBuilder(tg: good).price(price).quantity(quantity).build()
        }
        
        self.usedCap += quantity
    }
    
    func sellGood(item : Item) {
        let good = item.good
        let quantity = item.quantity
        if (self.hasGoods()) {
            self.cargo[good].subQuantity(quantity: quantity)
            self.usedCap -= quantity
        }
    }
    
    func emptyCargo() {
        if (self.usedCap > 0) {
            self.cargo = [Good : Item]()
            self.usedCap = 0
        }
    }
    
    func getCargoGoods() -> [Item] {
        return Array(self.cargo.keys)
    }
    
    func hasIllegalGoods() -> Bool {
        for (g in Good.ILLEGAL_GOODS) {
            if (cargo[good] != nil) {
                if (cargo[good].quantity > 0) {
                    return true
                }
            }
        }
        return false
    }
    
    func removeIllegalGoods() {
        for (g in Good.ILLEGAL_GOODS) {
            if (cargo[good] != nil) {
                if (cargo[good].quantity > 0) {
                    cargo.remove(at: good)
                }
            }
        }
    }
    
    func takeDamage(w: Weapon) -> Bool {
        self.health -= w.strength
        if (self.health < 0) {
            self.health = 0
        }
        return health <= 0
    }
    
    func attackShip(other : Ship) -> Bool {
        for (w in self.getWeapons()) {
            let playerHit = 0
            let dead = other.takeDamage(w: w)
            if (dead) {
                return true
            }
        }
        return false
    }
    
    func addWeapon(w: Weapon) {
        self.weapons.append(w)
    }
    
    class func getRandomShipWithWeapons() -> Ship {
        return Ship(type: ShipType.Gnat)
    }
    
}
