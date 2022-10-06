package com.carte.navigator.menu.sub.account;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
public class Fragment_Login extends Fragment {

    private EditText email;
    private EditText password;
    private ImageButton login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View loginView = inflater.inflate(R.layout.fragment_login, container, false);

        email = loginView.findViewById(R.id.LoginEmail_EditText);
        password = loginView.findViewById(R.id.LoginPassword_EditText);
        login = loginView.findViewById(R.id.LoginEmail_Button);

        return loginView;
    }
}