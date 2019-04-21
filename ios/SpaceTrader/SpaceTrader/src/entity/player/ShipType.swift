//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

struct ShipType {
    public static let Flea = ShipType(25, 0)
    public static let Gnat = ShipType(100, 1)
    public static let Firefly = ShipType(100, 1)
    public static let Mosquito = ShipType(100, 2)
    public static let Bumblebee = ShipType(100, 1)
    public static let Beetle = ShipType(50, 0)
    public static let Hornet = ShipType(150, 3)
    public static let Grasshopper = ShipType(150, 2)
    public static let Termite = ShipType(200, 1)
    public static let Wasp = ShipType(200, 3)
    
    var types = [
        Flea,
        Gnat,
        Firefly,
        Mosquito,
        Bumblebee,
        Beetle,
        Hornet,
        Grasshopper,
        Termite,
        Wasp
    ]
    
    var maxHealth : Int
    var maxWeaponSlots : Int
    
    init(_ maxHealth : Int, _ maxWeaponSlots : Int) {
        self.maxHealth = maxHealth
        self.maxWeaponSlots = maxWeaponSlots
    }
    
    func getShipsWithWeapons() -> [ShipType] {
        var ships : [ShipType] = []
        for s in types {
            if s.maxWeaponSlots > 0 {
                ships.append(s)
            }
        }
        return ships
    }
}
