package com.carte.navigator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.carte.navigator.menu.adapters.Adapter_Destination_Options;
import com.carte.navigator.menu.interfaces.Interface_RecyclerView;
import com.carte.navigator.menu.models.Model_Image_Text;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Interface_RecyclerView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BottomSheetStuff
        setUpBottomSheet();


    }

    private void setUpBottomSheet()
    {
        //Variable Declarations

        ArrayList<Integer> tempUserCollections = new ArrayList<Integer>();//Will be changed later

        RecyclerView recyclerView_destinationOptions = findViewById(R.id.recyclerview_destination_filter_options);
        RecyclerView recyclerView_userCollections = findViewById(R.id.recyclerView_user_collections);
        Button button_newCollection = findViewById(R.id.button_newCollection);

        recyclerView_destinationOptions.setHasFixedSize(true);
        recyclerView_userCollections.setHasFixedSize(true);

        //Ensuring the recycler view layout contains 4 item in each row
        RecyclerView.LayoutManager layoutManagerDestination = new StaggeredGridLayoutManager(4, 1);//(Professor Sluiter, 2020).
        recyclerView_destinationOptions.setLayoutManager(layoutManagerDestination);

        RecyclerView.LayoutManager layoutManagerCollections = new StaggeredGridLayoutManager(4, 1);//(Professor Sluiter, 2020).
        recyclerView_userCollections.setLayoutManager(layoutManagerCollections);

        //Retrieving navigation option texts
        String[] navigationOptions = getResources().getStringArray(R.array.string_navigation_options);

        //Setting up adapters
        //Destination Options RecyclerView
        RecyclerView.Adapter<Adapter_Destination_Options.OptionViewHolder> mAdapter = new Adapter_Destination_Options(this, getApplicationContext(),navigationOptions);//(Professor Sluiter, 2020).
        recyclerView_destinationOptions.setAdapter(mAdapter);

        if(tempUserCollections.isEmpty())
        {
            recyclerView_userCollections.setVisibility(View.GONE);
            //button_newCollection.setVisibility(View.GONE);

        }


    }

    @Override
    public void onItemClick(int position) {
        switch (position)
        {
            //Remember to change string resource to an array
            //Also remove names from icons for Light mode xD
            case 0:
                Toast.makeText(MainActivity.this,
                        "Resrtuaranct", Toast.LENGTH_LONG).show();
                break;
            case 1:
                Toast.makeText(MainActivity.this,
                        "Supermarket", Toast.LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(MainActivity.this,
                        "Attractions", Toast.LENGTH_LONG).show();
                break;
            case 3:
                Toast.makeText(MainActivity.this,
                        "Fastfood", Toast.LENGTH_LONG).show();
                break;
        }
    }
}