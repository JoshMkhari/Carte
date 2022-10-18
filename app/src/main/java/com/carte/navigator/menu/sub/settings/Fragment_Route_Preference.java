package com.carte.navigator.menu.sub.settings;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carte.navigator.MainActivity;
import com.carte.navigator.R;
import com.carte.navigator.dataAccessLayer.Database_Lite;
import com.carte.navigator.menu.adapters.Adapter_Account_Settings;
import com.carte.navigator.menu.interfaces.Interface_RecyclerView;
import com.carte.navigator.menu.models.Model_User;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Route_Preference extends Fragment implements Interface_RecyclerView {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View route_preference = inflater.inflate(R.layout.fragment_route_preference, container, false);


        RecyclerView recyclerView_pref = route_preference.findViewById(R.id.recyclerView_pref_user);

        recyclerView_pref.setHasFixedSize(true);

        //Ensuring the recycler view layout contains 4 item in each row
        RecyclerView.LayoutManager layoutManagerGeneral = new StaggeredGridLayoutManager(1, 1);//(Professor Sluiter, 2020).
        recyclerView_pref.setLayoutManager(layoutManagerGeneral);

        RecyclerView.LayoutManager layoutManagerOptions = new StaggeredGridLayoutManager(1, 1);//(Professor Sluiter, 2020).
        recyclerView_pref.setLayoutManager(layoutManagerOptions);

        //Retrieving navigation option texts
        String[] prefOptions = getResources().getStringArray(R.array.string_pref_options);

        //Account Settings RecyclerView

        RecyclerView.Adapter<Adapter_Account_Settings.OptionViewHolder>  adapter_settings_pref = new Adapter_Account_Settings(this,getContext(),prefOptions,7,true, false);//(Professor Sluiter, 2020).
        recyclerView_pref.setAdapter(adapter_settings_pref);

        return route_preference;
    }

    @Override
    public void onItemClick(int position, int source) {
        Database_Lite db = new Database_Lite(requireContext());
        if(source==7)
        {
            MainActivity._currentModelUser.setUserPreference(position);
            db.updateUserUnit(MainActivity._currentModelUser);
            Model_User.uploadData(MainActivity._currentModelUser);
            MainActivity._subMenu.dismiss();
        }
    }
}