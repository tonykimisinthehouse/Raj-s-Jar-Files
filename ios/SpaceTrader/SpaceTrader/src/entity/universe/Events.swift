//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

struct Events : CustomStringConvertible {
    public static let DROUGHT = Events("Drought")
    public static let COLD = Events("Cold")
    public static let CROP_FAIL = Events("Crop Fail")
    public static let WAR = Events("War")
    public static let BOREDOM = Events("Boredom")
    public static let PLAGUE = Events("Plague")
    public static let LACK_OF_WORKERS = Events("Lack of Workers")
    
    static var values : [Events] = [
        DROUGHT,
        COLD,
        CROP_FAIL,
        WAR,
        BOREDOM,
        PLAGUE,
        LACK_OF_WORKERS
    ]
    
    var description: String {
        get {
            return self.name
        }
    }
    
    var name : String
    
    init(_ name : String) {
        self.name = name
    }
    
    static func getRandomEvent() -> Events {
        let r = Int.random(in: 0..<Events.values.count)
        return Events.values[r]
    }
}
