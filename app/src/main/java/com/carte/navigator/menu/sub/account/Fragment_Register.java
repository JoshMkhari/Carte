package com.carte.navigator.menu.sub.account;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.carte.navigator.MainActivity;
import com.carte.navigator.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Register extends Fragment  {


    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private ImageButton signUp;
    private ImageButton loginWithEmail;

    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View register = inflater.inflate(R.layout.fragment_register, container, false);

        mAuth = FirebaseAuth.getInstance();

        email = register.findViewById(R.id.RegisterEmail_EditText);
        password = register.findViewById(R.id.RegisterPassword_EditText);
        confirmPassword = register.findViewById(R.id.RegisterConfirmPassword_EditText);

        loginWithEmail = register.findViewById(R.id.RegisterLoginEmail_Button);
        signUp = register.findViewById(R.id.SignEmail_Button);

        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                RegisterUser();
            }
        });


        loginWithEmail.setOnClickListener(v -> Navigation.findNavController(register).navigate(R.id.action_fragment_Register_to_fragment_Login));
        return register;
    }




    public void RegisterUser()
    {
        String userEmail = email.getText().toString().trim();
        String userPassword = password.getText().toString().trim();
        String conPassword = confirmPassword.getText().toString().trim();

        if(userEmail.isEmpty()){
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }
        if(userPassword.isEmpty()){
            password.setError("Password is required!");
            password.requestFocus();
            return;
        }
        if(conPassword.isEmpty()){
            confirmPassword.setError("Please confirm your password");
            confirmPassword.requestFocus();
            return;
        }
        if(userPassword.length() < 6 && conPassword.length() < 6){
            password.setError("Min password length should be 6 characters");
            password.requestFocus();
            return;
        }
        if(!userPassword.equals(conPassword)){
            confirmPassword.setError("Password does not match");
            confirmPassword.requestFocus();
            return;
        }else if(userPassword.equals(conPassword))
           // Toast.makeText(getActivity(), "Your password matches", Toast.LENGTH_LONG).show();


        createAccount(userEmail,userPassword);


    }

    //Create the users profile/account:
    private void createAccount( String userEmail, String userPassword)
    {
        mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                    public void onComplete(@NonNull Task<AuthResult> task){
                        if(task.isSuccessful()){
                            User user = new User(userEmail);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>(){
                                        public void onComplete(@NonNull Task<Void> task){
                                            if(task.isSuccessful()){
                                                HashMap<String, Object> hashMap = new HashMap<>();
                                                hashMap.put("Units","units");
                                                hashMap.put("Landmarks", "landmarks");

                                                HashMap<String, String> hashMap2 = new HashMap<>();
                                                hashMap2.put("Metric", "metric");
                                                hashMap2.put("Imperial", "imperial");

                                                HashMap<String, String> hashMap3 = new HashMap<>();
                                                hashMap3.put("Historical", "historical");
                                                hashMap3.put("Modern", "modern");
                                                hashMap3.put("Popular", "popular");

                                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users")
                                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Settings");
                                                ref.child("Units")
                                                        .setValue(hashMap2);
                                                ref.child("Landmarks")
                                                        .setValue(hashMap3);


                                                FirebaseUser user = mAuth.getCurrentUser();
                                                if(user.isEmailVerified()) {
                                                    Toast.makeText(getActivity(), "You email has been verified", Toast.LENGTH_LONG).show();

                                                }else{
                                                    user.sendEmailVerification();
                                                    Toast.makeText(getActivity(), "Check your email to verify your account!", Toast.LENGTH_LONG).show();
                                                }
                                            } else{
                                                Toast.makeText(getActivity(), "Failed to register! Try again", Toast.LENGTH_LONG).show();
                                            }
                                        }

                                    });
                            MainActivity._subMenu.hide();
                        }else{
                            Toast.makeText(getActivity(), "Failed to register", Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }



}