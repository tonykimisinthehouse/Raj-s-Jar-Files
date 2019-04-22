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
    
    func makePurchase(good : TradeGoods, quantity : Int) -> Bool {
        var newTransactionOrder = TransactionOrder(good: good, quantity: quantity, initiator: owner, transactionType: TransactionType.BUY)
        var newTransactionResult = owner.location.planet.marketplace.validateTransaction(to: newTransactionOrder)
        
        if (newTransactionResult.isTransactionSuccess) {
            let item : Item = newTransactionResult.item
            owner.ship!.addGood(item: item)
            self.useCredits(amount: item.price * item.quantity)
        }
        
        return newTransactionResult.isTransactionSuccess
    }
    
    func makeSales(good : TradeGoods, quantity: Int) -> Bool {
        var newTransactionOrder = TransactionOrder(good: good, quantity: quantity, initiator: owner, transactionType: TransactionType.SELL)
        var newTransactionResult = owner.location.planet.marketplace.validateTransaction(to: newTransactionOrder)
        
        if (newTransactionResult.isTransactionSuccess) {
            let item = newTransactionResult.item
            owner.ship!.sellGood(item: item)
            earnCredits(amount: item.price * item.quantity)
        }
        
        return newTransactionResult.isTransactionSuccess
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
