//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

class EncounterState {
    
    var isOver : Bool
    
    init() {
        self.isOver = false
    }
    
    func conclude() {
        self.isOver = true
    }
    
}
