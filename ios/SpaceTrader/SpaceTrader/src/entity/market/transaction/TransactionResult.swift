//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class TransactionResult {
    
    private var isTransactionSuccess : Bool
    private var item : Item
    
    init(isTranS : Bool, item : Item) {
        self.isTransactionSuccess = isTranS
        self.item = item
    }
    
    func getIsTransactionSuccess() {
        return self.isTransactionSuccess
    }
    
    func getItem() -> Item {
        return self.item
    }
    
}
