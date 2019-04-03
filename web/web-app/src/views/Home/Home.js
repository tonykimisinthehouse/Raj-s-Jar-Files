import React, {Component} from 'react';
import db from '../../firebase';
const docRef =  db.collection("players").doc("EHPIy485Y2KUvkVxJHy3");


class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {
            name: '',
        }
    }

    componentDidMount() {
        docRef.get().then(function(doc) {
            if (doc.exists) {
                this.setState({name: doc.data().name});
                console.log("Document data:", doc.data());
            } else {
                // doc.data() will be undefined in this case
                console.log("No such document!");
            }
        }).catch(function(error) {
            console.log("Error getting document:", error);
        });
    }

    render() {
        return(
            <div>
                <h1>this.state.name</h1>
            </div>
        )
    }
}
