import firebase from 'firebase';

const config = {
    apiKey: "AIzaSyB8-Zvyh64ZeYslpFTMNNV9BymXaXxjFJg",
    authDomain: "raj-s-jar-files-space-trader.firebaseapp.com",
    databaseURL: "https://raj-s-jar-files-space-trader.firebaseio.com",
    projectId: "raj-s-jar-files-space-trader",
    storageBucket: "raj-s-jar-files-space-trader.appspot.com",
    messagingSenderId: "239048826728"
};

firebase.initializeApp(config);
const db = firebase.firestore();

export default db;
