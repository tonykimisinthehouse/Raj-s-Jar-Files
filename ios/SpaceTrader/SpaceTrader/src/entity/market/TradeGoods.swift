//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

struct TradeGoods : Good {
    
    static func == (lhs: TradeGoods, rhs: TradeGoods) -> Bool {
        return lhs.name == rhs.name // TODO may not be an ideal equals() comparison
    }
    
    public static let Water = TradeGoods("Water", 0, 0, 2, 30, 3, 4, Events.DROUGHT, ResourceClassification.LOTS_OF_WATER, ResourceClassification.DESERT, 30, 50)
    public static let Furs = TradeGoods("Desert", 0, 0, 2, 30, 3, 4, Events.DROUGHT, ResourceClassification.LOTS_OF_WATER, ResourceClassification.DESERT, 30, 50)
    public static let Food = TradeGoods("Food", 0, 0, 2, 30, 3, 4, Events.DROUGHT, ResourceClassification.LOTS_OF_WATER, ResourceClassification.DESERT, 30, 50)
    public static let Ore = TradeGoods("Ore", 0, 0, 2, 30, 3, 4, Events.DROUGHT, ResourceClassification.LOTS_OF_WATER, ResourceClassification.DESERT, 30, 50)
    public static let Games = TradeGoods("Games", 0, 0, 2, 30, 3, 4, Events.DROUGHT, ResourceClassification.LOTS_OF_WATER, ResourceClassification.DESERT, 30, 50)
    public static let Firearms = TradeGoods("Firearms", 0, 0, 2, 30, 3, 4, Events.DROUGHT, ResourceClassification.LOTS_OF_WATER, ResourceClassification.DESERT, 30, 50)
    public static let Medicine = TradeGoods("Medicine", 0, 0, 2, 30, 3, 4, Events.DROUGHT, ResourceClassification.LOTS_OF_WATER, ResourceClassification.DESERT, 30, 50)
    public static let Machines = TradeGoods("Machines", 0, 0, 2, 30, 3, 4, Events.DROUGHT, ResourceClassification.LOTS_OF_WATER, ResourceClassification.DESERT, 30, 50)
    public static let Narcotics = TradeGoods("Narcotics", 0, 0, 2, 30, 3, 4, Events.DROUGHT, ResourceClassification.LOTS_OF_WATER, ResourceClassification.DESERT, 30, 50)
    public static let Robots = TradeGoods("Robots", 0, 0, 2, 30, 3, 4, Events.DROUGHT, ResourceClassification.LOTS_OF_WATER, ResourceClassification.DESERT, 30, 50)
    
    static var ALL_GOODS = [
        Water,
        Furs,
        Food,
        Ore,
        Games,
        Firearms,
        Medicine
    ]
    
    static var ILLEGAL_GOODS = [TradeGoods.Firearms, TradeGoods.Narcotics]
    
    var name : String
    var MLTP : Int
    var MLTU : Int
    var TTP : Int
    var BasePrice : Int
    var IPL : Int
    var Var : Int
    var IE : Events
    var CR : ResourceClassification
    var ER : ResourceClassification
    var MTL : Int
    var MTH : Int
    
    init(_ name : String, _ MLTP : Int, _ MLTU : Int, _ TTP : Int, _ Var : Int, _ BasePrice : Int, _ IPL : Int, _ IE : Events, _ CR : ResourceClassification, _ ER : ResourceClassification, _ MTL : Int, _ MTH : Int) {
        self.name = name
        self.MLTP = MLTP
        self.MLTU = MLTU
        self.TTP = TTP
        self.BasePrice = BasePrice
        self.IPL = IPL
        self.Var = Var
        self.IE = IE
        self.CR = CR
        self.ER = ER
        self.MTL = MTL
        self.MTH = MTH
    }
    
    func hash(into hasher: inout Hasher) {
        hasher.combine(self.name.hashValue)
    }
    
    func isIllegal() -> Bool {
        for g in TradeGoods.ILLEGAL_GOODS {
            if self.name == g.name {
                return true
            }
        }
        return false
    }
}
