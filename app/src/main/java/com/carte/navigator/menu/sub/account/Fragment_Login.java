package com.carte.navigator.menu.sub.account;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
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

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Login extends Fragment {

    private EditText email;
    private EditText password;
    private ImageButton login;

    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View loginView = inflater.inflate(R.layout.fragment_login, container, false);

        mAuth = FirebaseAuth.getInstance();


        email = loginView.findViewById(R.id.LoginEmail_EditText);
        password = loginView.findViewById(R.id.LoginPassword_EditText);
        login = loginView.findViewById(R.id.LoginEmail_Button);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                LoginUser();
            }

        });

        return loginView;
    }

    private void LoginUser()
    {
        String userEmail = email.getText().toString().trim();
        String userPassword = password.getText().toString().trim();

        if(userEmail.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            email.setError("Please provide a valid email!");
            email.requestFocus();
            return;
        }
        if(userPassword.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if(userPassword.length() < 6){
            password.setError("Min password length should be 6 characters ");
            password.requestFocus();
            return;
        }

        signIn(userEmail, userPassword);

    }

    private void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        if(task.isSuccessful()){
                          Toast.makeText(getActivity(), "You have logged in successfully", Toast.LENGTH_SHORT).show();
                            MainActivity._textView_userName.setText(email);
                            MainActivity._subMenu.hide();
                        }else{
                            Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

}