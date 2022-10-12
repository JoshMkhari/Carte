package com.carte.navigator.menu.sub;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.carte.navigator.MainActivity;
import com.carte.navigator.R;
import com.carte.navigator.menu.adapters.Adapter_Account_Settings;
import com.carte.navigator.menu.interfaces.Interface_RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Settings extends Fragment implements Interface_RecyclerView {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View settings = inflater.inflate(R.layout.fragment_settings, container, false);

        RecyclerView recyclerView_general_settings = settings.findViewById(R.id.recyclerView_settings_general);
        RecyclerView recyclerView_navigation_settings = settings.findViewById(R.id.recyclerView_settings_navigation);

        recyclerView_general_settings.setHasFixedSize(true);
        recyclerView_navigation_settings.setHasFixedSize(true);

        //Ensuring the recycler view layout contains 4 item in each row
        RecyclerView.LayoutManager layoutManagerGeneral = new StaggeredGridLayoutManager(1, 1);//(Professor Sluiter, 2020).
        recyclerView_general_settings.setLayoutManager(layoutManagerGeneral);

        RecyclerView.LayoutManager layoutManagerNavigation = new StaggeredGridLayoutManager(1, 1);//(Professor Sluiter, 2020).
        recyclerView_navigation_settings.setLayoutManager(layoutManagerNavigation);

        //Retrieving navigation option texts
        String[] settings_general = getResources().getStringArray(R.array.string_settings_general);
        String[] settings_navigation = getResources().getStringArray(R.array.string_settings_navigation);


        //Account Settings RecyclerView
        RecyclerView.Adapter<Adapter_Account_Settings.OptionViewHolder>  adapter_settings_general = new Adapter_Account_Settings(this,getContext(),settings_general,3);//(Professor Sluiter, 2020).
        recyclerView_general_settings.setAdapter(adapter_settings_general);

        RecyclerView.Adapter<Adapter_Account_Settings.OptionViewHolder>  adapter_settings_navigation = new Adapter_Account_Settings(this,getContext(),settings_navigation,4);//(Professor Sluiter, 2020).
        recyclerView_navigation_settings.setAdapter(adapter_settings_navigation);
        return settings;
    }

    @Override
    public void onItemClick(int position, int source) {
        if(source==3)
        {
            switch (position)
            {
                case 0:
                    Toast.makeText(getContext(),
                            "Fuel Type", Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    Toast.makeText(getContext(),
                            "Appearance", Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    Toast.makeText(getContext(),
                            "Units", Toast.LENGTH_LONG).show();
                    break;

            }
        }else
        {
            switch (position)
            {
                case 0:
                    Toast.makeText(getContext(),
                            "Route preferences", Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    Toast.makeText(getContext(),
                            "Speed limit alerts", Toast.LENGTH_LONG).show();
                    break;

            }

        }
    }
}