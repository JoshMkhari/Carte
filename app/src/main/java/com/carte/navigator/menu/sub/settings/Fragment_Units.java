package com.carte.navigator.menu.sub.settings;

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
import com.carte.navigator.dataAccessLayer.Database_Lite;
import com.carte.navigator.menu.adapters.Adapter_Account_Settings;
import com.carte.navigator.menu.interfaces.Interface_RecyclerView;
import com.carte.navigator.menu.models.Model_User;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Units extends Fragment implements Interface_RecyclerView {
    View _units;
    public static String[] _units_distance_sub, _units_temperature_sub;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _units = inflater.inflate(R.layout.fragment_units, container, false);

        _units_distance_sub = getResources().getStringArray(R.array.string_units_distance_sub);
        _units_temperature_sub = getResources().getStringArray(R.array.string_units_temperature_sub);


        RecyclerView recyclerView_units = _units.findViewById(R.id.recyclerView_units_distance);
        RecyclerView recyclerView_navigation = _units.findViewById(R.id.recyclerView_view_navigation);

        recyclerView_units.setHasFixedSize(true);
        recyclerView_navigation.setHasFixedSize(true);

        //Ensuring the recycler view layout contains 4 item in each row
        RecyclerView.LayoutManager layoutManagerGeneral = new StaggeredGridLayoutManager(1, 1);//(Professor Sluiter, 2020).
        recyclerView_units.setLayoutManager(layoutManagerGeneral);

        RecyclerView.LayoutManager layoutManagerNavigation = new StaggeredGridLayoutManager(1, 1);//(Professor Sluiter, 2020).
        recyclerView_navigation.setLayoutManager(layoutManagerNavigation);

        //Retrieving navigation option texts
        String[] units_distance = getResources().getStringArray(R.array.string_units_distance);
        String[] units_temperature = getResources().getStringArray(R.array.string_units_temperature);


        //Account Settings RecyclerView
        RecyclerView.Adapter<Adapter_Account_Settings.OptionViewHolder>  adapter_settings_distance = new Adapter_Account_Settings(this,getContext(),units_distance,5,true,true);//(Professor Sluiter, 2020).
        recyclerView_units.setAdapter(adapter_settings_distance);

        RecyclerView.Adapter<Adapter_Account_Settings.OptionViewHolder>  adapter_settings_temprature = new Adapter_Account_Settings(this,getContext(),units_temperature,6,true,true);//(Professor Sluiter, 2020).
        recyclerView_navigation.setAdapter(adapter_settings_temprature);
        return _units;
    }

    @Override
    public void onItemClick(int position, int source) {
        Database_Lite db = new Database_Lite(requireContext());

        if(source==5)
        {
            MainActivity._currentModelUser.setUnitOfMeasurement(position);
            db.updateUserUnit(MainActivity._currentModelUser);
            if(!db.getAllUsers().get(0).get_modelUser().getEmail().equals("DefaultUser"))
            {

                Model_User.uploadData(MainActivity._currentModelUser);
            }
            MainActivity._subMenu.dismiss();
        }else
        {
            switch (position)
            {
                case 0:
                    Toast.makeText(getContext(),
                            "N/A POE C", Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    Toast.makeText(getContext(),
                            "N/A POE F", Toast.LENGTH_LONG).show();
                    break;

            }
        }
    }
}