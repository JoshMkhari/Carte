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

import com.carte.navigator.menu.adapters.Adapter_Account_Settings;
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
        RecyclerView recyclerView_account_settings = findViewById(R.id.recyclerView_account_settings);
        Button button_newCollection = findViewById(R.id.button_menu_newCollection);
        Button button_downloadMaps = findViewById(R.id.button_menu_download_maps);

        recyclerView_destinationOptions.setHasFixedSize(true);
        recyclerView_userCollections.setHasFixedSize(true);
        recyclerView_account_settings.setHasFixedSize(true);

        //Ensuring the recycler view layout contains 4 item in each row
        RecyclerView.LayoutManager layoutManagerDestination = new StaggeredGridLayoutManager(4, 1);//(Professor Sluiter, 2020).
        recyclerView_destinationOptions.setLayoutManager(layoutManagerDestination);

        RecyclerView.LayoutManager layoutManagerCollections = new StaggeredGridLayoutManager(4, 1);//(Professor Sluiter, 2020).
        recyclerView_userCollections.setLayoutManager(layoutManagerCollections);

        RecyclerView.LayoutManager layoutManagerSettings = new StaggeredGridLayoutManager(1,1);
        recyclerView_account_settings.setLayoutManager(layoutManagerSettings);

        //Retrieving navigation option texts
        String[] destination_navigationOptions = getResources().getStringArray(R.array.string_navigation_options);
        String[] settings_navigationOptions = getResources().getStringArray(R.array.string_settings_options);

        //Setting up adapters
        //Destination Options RecyclerView
        RecyclerView.Adapter<Adapter_Destination_Options.OptionViewHolder> adapter_destination_options = new Adapter_Destination_Options(this, getApplicationContext(),destination_navigationOptions);//(Professor Sluiter, 2020).
        recyclerView_destinationOptions.setAdapter(adapter_destination_options);

        //Account Settings RecyclerView
        RecyclerView.Adapter<Adapter_Account_Settings.OptionViewHolder>  adapter_account_settings = new Adapter_Account_Settings(this,getApplicationContext(),settings_navigationOptions);//(Professor Sluiter, 2020).
        recyclerView_account_settings.setAdapter(adapter_account_settings);

        //Remember to code this
        if(tempUserCollections.isEmpty())
        {
            recyclerView_userCollections.setVisibility(View.GONE);
            //button_newCollection.setVisibility(View.GONE);

        }

    //Can turn into a method im sure
        button_downloadMaps.setOnClickListener(view -> {
            assert _textView_sub_menu_title != null;
            _textView_sub_menu_title.setText("Download Maps");

            findNavController(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.fragment_container_view_sub_menu))).
                    setGraph(R.navigation.navigation_offline_maps);//(developer Android NavController, n.d)
            subMenu.show();

        });
    //Can turn into a method im sure
        button_newCollection.setOnClickListener(view -> {
            assert _textView_sub_menu_title != null;
            _textView_sub_menu_title.setText("Collections");

            findNavController(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.fragment_container_view_sub_menu))).
                    setGraph(R.navigation.navigation_collections);//(developer Android NavController, n.d)
            subMenu.show();
        });


    }

    @Override
    public void onItemClick(int position, int source) {

        switch (source)
        {
            //Also remove names from icons for Light mode xD
            case 0://Navigation options menu
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
                break;
            case 1://Collections menu
                Toast.makeText(MainActivity.this,
                        "User collections", Toast.LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(MainActivity.this,
                        "Account settings", Toast.LENGTH_LONG).show();
                break;
        }

    }
}