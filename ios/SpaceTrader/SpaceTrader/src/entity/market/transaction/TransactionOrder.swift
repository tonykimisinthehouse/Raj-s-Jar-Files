//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class TransactionOrder {
    
    var item : Item
    var initiator : Player
    var transactionType : TransactionType
    
    init(good : TradeGoods, quantity : Int, initiator : Player, transactionType : TransactionType) {
        self.item = Item.ItemBuilder(tg: good).quantity(quantity).build()
        self.initiator = initiator
        self.transactionType = transactionType
    }
    
}
