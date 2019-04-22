//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class TransactionResult {
    
    var isTransactionSuccess : Bool
    var item : Item
    
    init(isTranS : Bool, item : Item) {
        self.isTransactionSuccess = isTranS
        self.item = item
    }
    
}
