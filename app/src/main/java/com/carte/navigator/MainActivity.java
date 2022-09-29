package com.carte.navigator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
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


        //Passing data to list recycler view
        //Variable Declarations
        RecyclerView recyclerView = findViewById(R.id.recyclerview_destination_filter_options);
        //recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);

        //Ensuring the recycler view layout contains 4 item in each row
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(4, 1);//(Professor Sluiter, 2020).
        recyclerView.setLayoutManager(layoutManager);
        String[] navigationOptions = getResources().getStringArray(R.array.string_navigation_options);
        ArrayList<Model_Image_Text> optionsList = new ArrayList<>();
        for (int i=0;i<4;i++)
        {
            optionsList.add(new Model_Image_Text(navigationOptions[i],getApplicationContext(),i));
        }



        //Setting up adapters
        //recyclerView
        RecyclerView.Adapter<Adapter_Destination_Options.OptionViewHolder> mAdapter = new Adapter_Destination_Options(this, getApplicationContext(),optionsList);//(Professor Sluiter, 2020).
        recyclerView.setAdapter(mAdapter);
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