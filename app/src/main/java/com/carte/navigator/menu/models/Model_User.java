package com.carte.navigator.menu.models;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.carte.navigator.MainActivity;
import com.carte.navigator.dataAccessLayer.Database_Lite;
import com.carte.navigator.menu.trueway_directions_json.EndPoint;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Model_User {

    private String email;
    private int unitOfMeasurement;
    private int userPreference;
    private List<Model_User_Collections> model_user_collections;

    public Model_User(String email, int unit, int place)
    {
        this.email = email;
        this.userPreference = place;
        this.unitOfMeasurement = unit;
    }

    public List<Model_User_Collections> getModel_user_collections() {
        Log.d("myMan", "getModel_user_collections: ");
        return model_user_collections;
    }

    public static void checkDuplicates(Model_User_Collections newCollection )
    {
        for (Model_User_Collections currentCollection:MainActivity._currentModelUser.getModel_user_collections()
             ) {
            if(currentCollection.placeShortName.equals(newCollection.placeShortName))
            {
                return;
            }
        }
        MainActivity._currentModelUser.getModel_user_collections().add(newCollection);
        uploadData(MainActivity._currentModelUser);
    }
    public void updateDatabase(){

    }
    public void setModel_user_collections(List<Model_User_Collections> model_user_collections) {
        this.model_user_collections = model_user_collections;
    }


    public void initializeUserCollections()
    {
        this.model_user_collections = new ArrayList<>();
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(int unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public int getUserPreference() {
        return userPreference;
    }

    public void setUserPreference(int userPreference) {
        this.userPreference = userPreference;
    }

    public static void mergeData(Context context, String pass)
    {
        // creating a variable for
        // our Firebase Database.
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        // creating a variable for our
        // Database Reference for Firebase.
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        Query myUserQuery = databaseReference.child("Users").child(MainActivity._currentUserAuth.getUid());

        myUserQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                MainActivity._currentModelUser = new Model_User(snapshot.child("email").getValue(String.class)
                        ,(snapshot.child("unitOfMeasurement").getValue(int.class))
                        ,(snapshot.child("userPreference").getValue(int.class)));
                List<Model_User_Collections> model_user_collectionsList = new ArrayList<>();
                for (DataSnapshot postSnapshot: snapshot.child("model_user_collections").getChildren()
                     ) {
                    Model_User_Collections model_user_collections = new Model_User_Collections(postSnapshot.child("placeShortName").getValue(String.class),postSnapshot.child("endPoints").getValue(EndPoint.class));
                    model_user_collectionsList.add(model_user_collections);
                }
                MainActivity._currentModelUser.setModel_user_collections(model_user_collectionsList);
                Database_Lite db = new Database_Lite(context);
                MainActivity._textView_userName.setText(MainActivity._currentModelUser.getEmail());
                db.addUser(MainActivity._currentModelUser,pass);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void uploadData(Model_User model_user){
        try
        {
            FirebaseDatabase.getInstance().getReference("Users")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .setValue(model_user);
        }catch (Exception e){
            Log.d("myMan", "uploadData: ");
        }

    }
}
