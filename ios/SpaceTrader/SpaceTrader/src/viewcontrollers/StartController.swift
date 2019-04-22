//
//  ViewController.swift
//  SpaceTrader
//
//  Created by Thomas Suarez on 4/20/19.
//  Copyright © 2019 RajsJarFiles. All rights reserved.
//

import UIKit

class StartController: UIViewController {
    
    var difficultyArray : [GameDifficulty] = [
        .BEGINNER,
        .EASY,
        .NORMAL,
        .HARD,
        .IMPOSSIBLE
    ]
    
    @IBOutlet weak var indicatorSkillPoints : UILabel!
    @IBOutlet weak var editPlayerName : UITextField!
    @IBOutlet weak var editPilotPoints : UITextField!
    @IBOutlet weak var editFighterPoints : UITextField!
    @IBOutlet weak var editTraderPoints : UITextField!
    @IBOutlet weak var editEngineerPoints : UITextField!
    @IBOutlet weak var difficultySpinner : UIPickerView!

    override func viewDidLoad() {
        super.viewDidLoad()
        
        // TODO apply difficultyArray -> UIPickerView
    }
    
    @IBAction func letsGo() {
        if (self.validateCreditEntriesInternal()) {
            let index = self.difficultySpinner.selectedRow(inComponent: 0)
            let diff = self.difficultyArray[index]
            Model.current = Model(name: self.editPlayerName.text!, points: self.getDifficultyArray(), difficulty: diff)
            self.performSegue(withIdentifier: "game", sender: self)
        }
    }

    @IBAction func validateCreditEntries() {
        _ = self.validateCreditEntriesInternal()
    }
    
    func getDifficultyArray() -> [Int] {
        let val0 = Int(self.editPilotPoints.text ?? "0") ?? 0
        let val1 = Int(self.editFighterPoints.text ?? "0") ?? 0
        let val2 = Int(self.editTraderPoints.text ?? "0") ?? 0
        let val3 = Int(self.editEngineerPoints.text ?? "0") ?? 0
        return [ val0, val1, val2, val3 ]
    }
    
    func validateCreditEntriesInternal() -> Bool {
        
        let diff = getDifficultyArray()
        let sum = diff[0] + diff[1] + diff[2] + diff[3]
        
        self.indicatorSkillPoints.text = "Skill Points (\(sum)/16):"
        
        // Change text color (normal / error, white / red)
        var color = UIColor.white
        if (sum != 16) {
            color = UIColor.red
        }
        self.indicatorSkillPoints.textColor = color
        
        // base case
        return sum == 16
    }

}

