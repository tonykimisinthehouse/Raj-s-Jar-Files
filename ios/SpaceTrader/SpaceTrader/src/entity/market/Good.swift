//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

protocol Good : Hashable {
    
    func hash(into hasher: inout Hasher)
    
    func isIllegal() -> Bool
    
}
