//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class Wallet {
    
    var credits : Int
    var owner : Player! = nil
    
    init(credits : Int) {
        self.credits = credits
    }
    
    func makePurchase(good : Good, quantity : Int) -> Bool {
        var newTransactionOrder = TransactionOrder(good: good, quantity: quantity, owner: owner, type: TransactionType.BUY)
        var newTransactionResult = owner.getLocation().getPlanet().getMarketplace().validateTransaction(newTransactionOrder)
        
        if (newTransactionResult.getIsTransactionSuccess()) {
            var item : Item = newTransactionResult.getItem()
            owner.getShip().addGood(item)
            self.useCredits(amount: item.price * item.quantity)
        }
        
        return newTransactionResult.getIsTransactionSuccess()
    }
    
    func makeSales(good : Good, quantity: Int) {
        var newTransactionOrder = TransactionOrder(good: good, quantity: quantity, owner: owner, type: TransactionType.SELL).validateTransaction(newTransactionOrder)
        
        if (newTransactionOrder.getIsTransactionSuccess()) {
            let item = newTransactionResult.getItem()
            owner.getShip().sellGood(item)
            earnCredits(amount: item.price * item.quantity)
        }
        
        return newTransactionResult.getIsTransactionSuccess()
    }
    
    func earnCredits(amount : Int) {
        self.credits += amount
    }
    
    func useCredits(amount : Int) {
        self.credits -= amount
    }
    
    func checkCreditEnough(marketPrice : Int) -> Bool {
        return marketPrice <= self.credits
    }
    
}
