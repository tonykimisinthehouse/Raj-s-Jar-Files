//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class Marketplace : CustomStringConvertible {
    
    var tradeGoodsInMarketMap = [TradeGoods : Item]()
    
    var planetName : String
    var techLevel : TechLevel
    var resource : ResourceClassification
    var event : Events
    
    var description: String {
        get {
            return "Goods for Buying" // TODO add proper print-out
        }
    }
    
    init(pn : String, techLevel: TechLevel, event : Events, resource : ResourceClassification) {
        self.planetName = pn
        self.techLevel = techLevel
        self.resource = resource
        self.event = event
        
        self.tradeGoodsInMarketMap = [TradeGoods : Item]()
        
        for goods in TradeGoods.ALL_GOODS {
            let item = Item.ItemBuilder(tg: goods as! TradeGoods).quantity(self.calculateQuantity(good: goods as! TradeGoods)).price(self.calculatePrice(good: goods)).build()
            self.tradeGoodsInMarketMap[goods] = item
        }
    }
    
    func calculatePrice(good : TradeGoods) -> Int {
        let basePrice = good.BasePrice
        let dynamicPrice = good.IPL
        let variancePrice = Double(basePrice) * 1.1
        var finalPrice = Double(basePrice + dynamicPrice) + variancePrice
        
        // TODO 3 if statements were omitted here
        finalPrice *= 1.5
        
        return Int(finalPrice)
    }
    
    func calculateQuantity(good : TradeGoods) -> Int {
        let maxQ = 30
        let minQ = 1
        return Int.random(in: minQ..<maxQ)
    }
    
    func validateTransaction(to : TransactionOrder) -> TransactionResult {
        let validator : MarketTransactionValidator = MarketTransactionValidator(mp: self)
        return validator.validateNTransaction(order: to)
    }
    
}
