//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

struct ResourceClassification {
    
    public static let NO_SPECIAL_RESOURCES = ResourceClassification(35)
    public static let MINERAL_RICH = ResourceClassification(10)
    public static let MINERAL_POOR = ResourceClassification(10)
    public static let LOTS_OF_WATER = ResourceClassification(10)
    public static let RICH_SOIL = ResourceClassification(10)
    public static let POOR_SOIL = ResourceClassification(10)
    public static let RICH_FAUNA = ResourceClassification(10)
    public static let WEIRD_MUSHROOMS = ResourceClassification(10)
    public static let LOTS_OF_HERBS = ResourceClassification(10)
    public static let ARTISTIC = ResourceClassification(10)
    public static let WARLIKE = ResourceClassification(30)
    public static let LIFELESS = ResourceClassification(5)
    public static let DESERT = ResourceClassification(10)
    
    public static let values = [
        NO_SPECIAL_RESOURCES,
        MINERAL_RICH,
        MINERAL_POOR,
        LOTS_OF_WATER,
        RICH_SOIL,
        POOR_SOIL,
        RICH_FAUNA,
        WEIRD_MUSHROOMS,
        LOTS_OF_HERBS,
        ARTISTIC,
        WARLIKE,
        LIFELESS,
        DESERT
    ]
    
    var probOfHavingResource : Int
    
    init(_ prob : Int) {
        self.probOfHavingResource = prob
    }
    
    static func getRandomResourceClass() -> ResourceClassification {
        let r = Int.random(in: 0..<ResourceClassification.values.count)
        return ResourceClassification.values[r]
    }
    
}
