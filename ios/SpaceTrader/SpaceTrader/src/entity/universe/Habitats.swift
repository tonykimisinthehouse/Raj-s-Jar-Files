//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

struct Habitats {
    
    public static let DESERT = Habitats([Species.MACHINE, Species.REPTILIAN], [ResourceClassification.LOTS_OF_WATER, ResourceClassification.RICH_SOIL])
    public static let OCEAN = Habitats([Species.FUNGOID], [ResourceClassification.LOTS_OF_HERBS, ResourceClassification.RICH_SOIL])
    
    // TODO many habitats omitted
    
    public static var values = [
        Habitats.DESERT,
        Habitats.OCEAN
    ]
    
    var species = [Species]()
    var resources = [ResourceClassification]()
    
    init(_ species: [Species], _ resources: [ResourceClassification]) {
        self.species = species
        self.resources = resources
    }
    
    static func getRandomHabitat() -> Habitats {
        let r = Int.random(in: 0..<Habitats.values.count)
        return Habitats.values[r]
    }
}
