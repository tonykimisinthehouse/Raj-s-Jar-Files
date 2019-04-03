import React from 'react';
import './Login.css';
import db from '../../firebase';


const DEFAULT_CREDIT = 1000;
class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state ={
            name: '',
            pilotPoints: 0,
            fighterPoints: 0,
            traderPoints: 0,
            engineerPoints: 0,
            difficulty: 'Normal'
        }
    }

    handleInputChange = (event) => {
        const target = event.target;
        const value = target.value;
        const name = target.name;

        this.setState({
            [name]: value
        });
    };

    handleSelectChange = (event) => {
        const target = event.target;
        const value = target.value;
        const name = target.name;

        this.setState({
            [name]: value
        });
    };

   onSubmit = (event) => {
       const {name} = this.state;
       const {pilotPoints, fighterPoints, traderPoints, engineerPoints } = this.state;
       const {difficulty} = this.state;
       db.collection("players").add({
           name: name,
           points: [pilotPoints, fighterPoints, traderPoints, engineerPoints],
           difficulty: difficulty,
           credit: DEFAULT_CREDIT,
       })
           .then(function(docRef) {
               console.log("Document written with ID: ", docRef.id);
           })
           .catch(function(error) {
               console.error("Error adding document: ", error);
           });
       alert(`${this.state.name} embarked his journey.` );
       event.preventDefault();
   };

    render() {
        const {name} = this.state;
        const {pilotPoints, fighterPoints, traderPoints, engineerPoints } = this.state;
        const {difficulty} = this.state;

        return (
            <div className="Login">
                <h1>Embark on your journey</h1>
                <form onSubmit={this.onSubmit}>
                    <label>Your Name</label><br/>
                    <input
                        name="name"
                        type="text"
                        value={name}
                        onChange={this.handleInputChange}
                    /><br/>

                    <label>Skill Points (Total 16 pts): </label><br/>
                    <div className="Horizontal">
                        <div>
                            <label>Pilot</label> <br/>
                            <input
                                name="pilotPoints"
                                type="text"
                                value={pilotPoints}
                                onChange={this.handleInputChange}
                            />
                        </div>
                        <div>
                            <label>Fighter</label><br/>
                            <input
                                name="fighterPoints"
                                type="text"
                                value={fighterPoints}
                                onChange={this.handleInputChange}
                            />
                        </div>
                    </div>

                    <div className="Horizontal">
                        <div>
                            <label>Trader</label><br/>
                            <input
                                name="traderPoints"
                                type="text"
                                value={traderPoints}
                                onChange={this.handleInputChange}
                            />
                        </div>
                        <div>
                            <label>Engineer</label><br/>
                            <input
                                name="engineerPoints"
                                type="text"
                                value={engineerPoints}
                                onChange={this.handleInputChange}
                            />
                        </div>
                    </div>

                    <div>
                        <label>Difficulty</label>
                        <select value={difficulty} name="difficulty" onChange={this.handleSelectChange}>
                            <option value="Begineer">Begineer</option>
                            <option value="Easy">Easy</option>
                            <option selected value="Normal">Normal</option>
                            <option value="Hard">Hard</option>
                            <option value="Impossible">Impossible</option>
                        </select>
                    </div>
                    <input type="submit" value="Submit" />
                </form>
            </div>
        )
    }
}

export default Login;
