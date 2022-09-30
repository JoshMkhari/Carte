package com.carte.navigator.menu.sub.offline;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.carte.navigator.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Download_Maps extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Log.d("latestBug", "onCreateView: ");
        View downloadMaps =  inflater.inflate(R.layout.fragment_download_maps, container, false);


        Button buttonTemp = downloadMaps.findViewById(R.id.button_temp);

        buttonTemp.setOnClickListener(view -> {
           Navigation.findNavController(downloadMaps).navigate(R.id.action_fragment_Download_Maps_to_fragment_Map_Options);
        });

        return downloadMaps;
    }
}