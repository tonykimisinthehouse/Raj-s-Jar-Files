//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class Item : CustomStringConvertible {
    
    private var good : Good
    private var quantity : Int
    private var price : Int
    
    init(builder: ItemBuilder) {
        self.good = builder.good
        self.quantity = builder.quantity
        self.price = builder.price
    }
    
    // Java-like Getters and Setters
    func getGoodName() -> String {
        return self.good.getName()
    }
    func getGood() -> Good {
        return self.good
    }
    func setPrice(price : Int) {
        self.price = price
    }
    func getPrice() -> Int {
        return self.price
    }
    func setQuantity(quantity : Int) {
        self.quantity = quantity
    }
    func addQuantity(quantity : Int) {
        self.quantity += quantity
    }
    func subQuantity(quantity : Int) {
        self.quantity -= quantity
    }
    
    var description: String {
        return self.quantity + " " + self.good.getName() + " at " + self.price + " units each."
    }
    
    class ItemBuilder {
        
        var good : Good
        var quantity : Int = -1
        var price : Int = 0
        
        init(tg : Good) {
            self.good = tg
        }
        
        func quantity(_ quantity : Int) -> ItemBuilder {
            self.quantity = quantity
            return self
        }
        
        func price(_ price : Int) -> ItemBuilder {
            self.price = price
            return self
        }
        
        func build() -> Item {
            return Item(builder: self)
        }
        
    }
    
}
