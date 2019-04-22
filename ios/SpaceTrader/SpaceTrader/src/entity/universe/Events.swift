//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

struct Events {
    public static let DROUGHT = Events()
    public static let COLD = Events()
    public static let CROP_FAIL = Events()
    public static let WAR = Events()
    public static let BOREDOM = Events()
    public static let PLAGUE = Events()
    public static let LACK_OF_WORKERS = Events()
    
    init() {
        
    }
    
    static func getRandomEvent() -> Events {
        return Events.DROUGHT // TODO not random
    }
}
