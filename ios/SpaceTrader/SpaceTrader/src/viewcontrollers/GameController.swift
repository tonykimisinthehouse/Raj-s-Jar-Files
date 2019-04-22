//
//  ViewController.swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import UIKit

class GameController: UIViewController {
    
    @IBOutlet weak var planetNameIndicator : UILabel!
    @IBOutlet weak var orbitRadiusIndicator : UILabel!
    @IBOutlet weak var techLevelIndicator : UILabel!
    @IBOutlet weak var resourceIndicator : UILabel!
    @IBOutlet weak var eventIndicator : UILabel!
    @IBOutlet weak var speciesIndicator : UILabel!

    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.updateData()
    }
    
    func updateData() {
        self.planetNameIndicator.text = Model.current.player!.location.planet.name
        self.orbitRadiusIndicator.text = "Orbit Radius: \(Model.current.player!.location.planet.orbitRadius)"
        self.techLevelIndicator.text = "Tech Level: \(Model.current.player!.location.planet.techLevel)"
        self.resourceIndicator.text = "Resources: \(Model.current.player!.location.planet.resourceClass)"
        self.eventIndicator.text = "Event: \(Model.current.player!.location.planet.event)"
        self.speciesIndicator.text = "Species: \(Model.current.player!.location.planet.species)"
    }

}

