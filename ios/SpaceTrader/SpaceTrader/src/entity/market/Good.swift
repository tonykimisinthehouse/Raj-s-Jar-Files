//
//  .swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import Foundation

protocol Good : Codable {
    
    func getName() -> String
    func getMLTP() -> Int
    func getMLTU() -> Int
    func getTTP() -> Int
    func getBasePrice() -> Int
    func getIPL() -> Int
    func getVar() -> Int
    func getIE() -> Events
    
    func getCR() -> ResourceClassification
    func getER() -> ResourceClassification
    
    func getMTL() -> Int
    func getMTH() -> Int
    func isIllegal() -> Bool
    
}
