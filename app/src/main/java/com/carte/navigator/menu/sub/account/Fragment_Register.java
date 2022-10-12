package com.carte.navigator.menu.sub.account;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.carte.navigator.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Register extends Fragment {


    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private ImageButton signUp;
    private ImageButton loginWithEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View register = inflater.inflate(R.layout.fragment_register, container, false);

        email = register.findViewById(R.id.RegisterEmail_EditText);
        password = register.findViewById(R.id.RegisterPassword_EditText);
        confirmPassword = register.findViewById(R.id.RegisterConfirmPassword_EditText);

        loginWithEmail = register.findViewById(R.id.RegisterLoginEmail_Button);
        signUp = register.findViewById(R.id.SignEmail_Button);


        loginWithEmail.setOnClickListener(v -> Navigation.findNavController(register).navigate(R.id.action_fragment_Register_to_fragment_Login));
        return register;
    }
}