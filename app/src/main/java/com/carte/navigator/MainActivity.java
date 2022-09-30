package com.carte.navigator;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.carte.navigator.menu.adapters.Adapter_Destination_Options;
import com.carte.navigator.menu.interfaces.Interface_RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Objects;

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

        BottomSheetDialog subMenu = new BottomSheetDialog(MainActivity.this);
        subMenu.setContentView(R.layout.bottom_sheet_sub_menu_layout);
        subMenu.setCanceledOnTouchOutside(true);
        TextView _textView_sub_menu_title = subMenu.findViewById(R.id.textView_sub_menu_title);

        ArrayList<Integer> tempUserCollections = new ArrayList<Integer>();//Will be changed later

        RecyclerView recyclerView_destinationOptions = findViewById(R.id.recyclerview_destination_filter_options);
        RecyclerView recyclerView_userCollections = findViewById(R.id.recyclerView_user_collections);
        Button button_newCollection = findViewById(R.id.button_menu_newCollection);
        Button button_downloadMaps = findViewById(R.id.button_menu_download_maps);

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

        //Remember to code this
        if(tempUserCollections.isEmpty())
        {
            recyclerView_userCollections.setVisibility(View.GONE);
            //button_newCollection.setVisibility(View.GONE);

        }

        button_downloadMaps.setOnClickListener(view -> {


            assert _textView_sub_menu_title != null;
            _textView_sub_menu_title.setText("Download Maps");

            findNavController(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.fragment_container_view_sub_menu))).
                    setGraph(R.navigation.navigation_offline_maps);//(developer Android NavController, n.d)
            subMenu.show();

        });

        button_newCollection.setOnClickListener(view -> {
            assert _textView_sub_menu_title != null;
            _textView_sub_menu_title.setText("Collections");

            findNavController(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.fragment_container_view_sub_menu))).
                    setGraph(R.navigation.navigation_collections);//(developer Android NavController, n.d)
            subMenu.show();
        });


    }

    @Override
    public void onItemClick(int position) {
        switch (position)
        {
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