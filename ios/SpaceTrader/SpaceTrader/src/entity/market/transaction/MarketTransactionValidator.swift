//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class MarketTransactionValidator : TransactionValidator {
    
    private var marketplace : Marketplace
    
    init(mp : Marketplace) {
        self.marketplace = mp
    }
    
    func validate(order : TransactionOrder) -> Bool {
        // TODO stubbed
        return true
    }
    
    func validateNTransaction(order : TransactionOrder) -> TransactionResult {
        let isTransSuccess = validate(order: order)
        let result = TransactionResult(isTranS: isTransSuccess, item: order.getItem())
    }
    
}
