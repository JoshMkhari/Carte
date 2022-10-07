package com.carte.navigator.menu.sub.account;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.carte.navigator.R;
import com.google.firebase.auth.FirebaseAuth;

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
        }else if(userPassword.equals(userPassword)){
            Toast.makeText(getActivity(), "You have registered successfully", Toast.LENGTH_LONG).show();
        }


    }



}