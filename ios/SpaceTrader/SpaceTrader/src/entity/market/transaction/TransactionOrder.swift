//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class TransactionOrder {
    
    private var item : Item
    private var initiator : Player
    private var transactionType : TransactionType
    
    init(good : Good, quantity : int, initiator : Player, transactionType : TransactionType) {
        self.item = Item.ItemBuilder(good).quantity(quantity).build()
        self.initiator = initiator
        self.transactionType = transactionType
    }
    
}
