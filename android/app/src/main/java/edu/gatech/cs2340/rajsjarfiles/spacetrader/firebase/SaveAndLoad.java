package edu.gatech.cs2340.rajsjarfiles.spacetrader.firebase;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.firestore.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.model.Model;

import static android.support.constraint.Constraints.TAG;

public class SaveAndLoad {

    private FirebaseFirestore mDatabase;
    private Model model = Model.getCurrent();
    private String documentId = model.getPlayer().getName();



    public void saveGameProgress() {
        mDatabase = FirebaseFirestore.getInstance();
        // Create a new user with a first and last name
        Map<String, Object> gameProgress = new HashMap<>();
        gameProgress.put("model", model);


        mDatabase.collection("gameProgress").document(model.getPlayer().getName())
                .set(gameProgress);




//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        mDatabase.child("progress").child("game").setValue(model.getGame());
//        mDatabase.child("progress").child("model").setValue(model);
    }

    public void createUserCredit() {
        mDatabase = FirebaseFirestore.getInstance();
        int currentCredit = model.getPlayer().getWallet().getCredits();

        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("user", model.getPlayer().getName());
        user.put("credit", currentCredit);


        mDatabase.collection("UserCredit")
                .document(documentId)
                .set(user);
    }

    public void updateUserCredit() {
        mDatabase = FirebaseFirestore.getInstance();
        int currentCredit = model.getPlayer().getWallet().getCredits();

        // Create a new user with a first and last name
        Map<String, Object> newCredit = new HashMap<>();
        newCredit.put("credit", currentCredit);


        mDatabase.collection("UserCredit")
                .document(documentId)
                .update(newCredit);
    }

}
