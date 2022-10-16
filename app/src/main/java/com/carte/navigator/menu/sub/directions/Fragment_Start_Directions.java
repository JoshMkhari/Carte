package com.carte.navigator.menu.sub.directions;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carte.navigator.MainActivity;
import com.carte.navigator.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Start_Directions extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View navigating = inflater.inflate(R.layout.fragment_start_directions, container, false);

        MainActivity._navigationLayout.setVisibility(View.VISIBLE);
        return navigating;
    }
}