//
//  ViewController.swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidAppear(_ animated : Bool) {
        super.viewDidAppear(animated)
        
        // Segue immediately
        self.performSegue(withIdentifier: "start", sender: self)
    }

}

