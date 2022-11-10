package com.carte.navigator.menu.sub;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.carte.navigator.MainActivity;
import com.carte.navigator.R;
import com.carte.navigator.mapRelated.MapsFragment;
import com.carte.navigator.menu.adapters.Adapter_Account_Settings;
import com.carte.navigator.menu.interfaces.Interface_RecyclerView;
import com.carte.navigator.menu.models.Model_User_Collections;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Collection extends Fragment implements Interface_RecyclerView {

    EditText editTextCollectionName;
    String[] allUserCollectionNames;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View collection = inflater.inflate(R.layout.fragment_collection, container, false);

        ImageButton close = collection.findViewById(R.id.imageButton_close_direction_options_menu);

        close.setOnClickListener(view -> {
            MainActivity._subMenu.dismiss();
        });

        RecyclerView recyclerView_general_settings = collection.findViewById(R.id.recyclerView_collections_user_collections);

        recyclerView_general_settings.setHasFixedSize(true);

        List<String> userCollectionName = new ArrayList<>();

        if(MainActivity._currentModelUser.getModel_user_collections()!=null)
        {
            for (Model_User_Collections name: MainActivity._currentModelUser.getModel_user_collections()
            ) {
                userCollectionName.add(name.getPlaceShortName());
            }
        }

        if(userCollectionName.size()==0)
        {
            allUserCollectionNames = new String[0];
        }else
        {
            allUserCollectionNames = userCollectionName.toArray(new String[userCollectionName.size()]);
        }
        //Ensuring the recycler view layout contains 4 item in each row
        RecyclerView.LayoutManager layoutManagerGeneral = new StaggeredGridLayoutManager(1, 1);//(Professor Sluiter, 2020).
        recyclerView_general_settings.setLayoutManager(layoutManagerGeneral);

        //Account Settings RecyclerView
        RecyclerView.Adapter<Adapter_Account_Settings.OptionViewHolder> adapter_collections = new Adapter_Account_Settings(this,getContext(),allUserCollectionNames,8,false,false);//(Professor Sluiter, 2020).
        recyclerView_general_settings.setAdapter(adapter_collections);
        return collection;
    }

    @Override
    public void onItemClick(int position, int source) {

        Toast.makeText(requireContext(),
                "Navigating to this location is a POE feature N/A", Toast.LENGTH_LONG).show();
        MapsFragment.moveMapToLocation(allUserCollectionNames[position]);
    }
}