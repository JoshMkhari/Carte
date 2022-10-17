package com.carte.navigator;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.carte.navigator.mapRelated.LocationService;
import com.carte.navigator.menu.Constants;
import com.carte.navigator.menu.adapters.Adapter_Account_Settings;
import com.carte.navigator.menu.adapters.Adapter_Destination_Options;
import com.carte.navigator.menu.interfaces.Interface_RecyclerView;
import com.carte.navigator.menu.models.User;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements Interface_RecyclerView {

    private static final int _REQUEST_CODE_LOCATION_PERMISSION = 1;
    public static BottomSheetDialog _subMenu;
    public static FragmentManager _fragmentManager;
    public static TextView _textView_userName;
    //layout_menu

    public static FirebaseUser _currentUserAuth;
    public static User _currentUser;
    public static LinearLayout _menu;
    //Navigation Layout
    public static boolean _muted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _menu = findViewById(R.id.layout_menu);
        _textView_userName = findViewById(R.id.textView_profile_name);

        //map stuff
        //Fragment fragment = new Fragment();
        findNavController(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.fragment_container_view_main_activity_background))).
                setGraph(R.navigation.navigation_maps);//(developer Android NavController, n.d)

        LocationService.changed = false;
        //https://www.youtube.com/watch?v=4_RK_5bCoOY&t=929s
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    _REQUEST_CODE_LOCATION_PERMISSION
            );
        }else
        {
            startLocationService();
            setUpBottomSheet();
        }

    }

//    https://www.youtube.com/watch?v=4_RK_5bCoOY&t=929s
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == _REQUEST_CODE_LOCATION_PERMISSION && grantResults.length>0)
        {
            startLocationService();
            setUpBottomSheet();
        }else
        {
            Toast.makeText(MainActivity.this,
                    "Permission denied", Toast.LENGTH_LONG).show();
        }
    }

    private void setUpBottomSheet()
    {
        //Variable Declarations
        _subMenu = new BottomSheetDialog(MainActivity.this);

        //https://stackoverflow.com/questions/51302005/how-to-change-transparent-background-in-bottomsheetdialog
        if(_subMenu.getWindow() != null)
            _subMenu.getWindow().setDimAmount(0);


        _subMenu.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
               _subMenu.hide();
            }
        });
        _subMenu.setContentView(R.layout.bottom_sheet_sub_menu_layout);
        _subMenu.setCanceledOnTouchOutside(true);

        ImageButton _imageButton_close_sub_menu = _subMenu.findViewById(R.id.imageButton_close_sub_menu);
        assert _imageButton_close_sub_menu != null;
        _imageButton_close_sub_menu.setOnClickListener(view -> {
            _subMenu.dismiss();
        });

        ArrayList<Integer> tempUserCollections = new ArrayList<Integer>();//Will be changed later

        RecyclerView recyclerView_destinationOptions = findViewById(R.id.recyclerview_destination_filter_options);
        RecyclerView recyclerView_userCollections = findViewById(R.id.recyclerView_user_collections);
        RecyclerView recyclerView_account_settings = findViewById(R.id.recyclerView_account_settings);

        Button button_newCollection = findViewById(R.id.button_menu_newCollection);

        Button imageButton_set_up_profile = findViewById(R.id.button_setUp);

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
        String[] emptySubMenu = {"empty"};
        //Setting up adapters
        //Destination Options RecyclerView
        RecyclerView.Adapter<Adapter_Destination_Options.OptionViewHolder> adapter_destination_options = new Adapter_Destination_Options(this, getApplicationContext(),destination_navigationOptions);//(Professor Sluiter, 2020).
        recyclerView_destinationOptions.setAdapter(adapter_destination_options);

        //Account Settings RecyclerView
        RecyclerView.Adapter<Adapter_Account_Settings.OptionViewHolder>  adapter_account_settings = new Adapter_Account_Settings(this,getApplicationContext(),settings_navigationOptions,2,false);//(Professor Sluiter, 2020).
        recyclerView_account_settings.setAdapter(adapter_account_settings);

        //Remember to code this
        if(tempUserCollections.isEmpty())
        {
            recyclerView_userCollections.setVisibility(View.GONE);
            //button_newCollection.setVisibility(View.GONE);

        }

        _fragmentManager = getSupportFragmentManager();
    //Can turn into a method im sure
        button_newCollection.setOnClickListener(view -> {
            TextView _textView_sub_menu_title = _subMenu.findViewById(R.id.textView_sub_menu_title);
            assert _textView_sub_menu_title != null;

            _textView_sub_menu_title.setText("Collections");

            findNavController(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.fragment_container_view_sub_menu))).
                    setGraph(R.navigation.navigation_collections);//(developer Android NavController, n.d)
            _subMenu.show();
        });

        //Can turn into a method im sure
        imageButton_set_up_profile.setOnClickListener(view -> {
            //if logged in do nothing else show register/login pages
            TextView _textView_sub_menu_title = _subMenu.findViewById(R.id.textView_sub_menu_title);
            assert _textView_sub_menu_title != null;
            _textView_sub_menu_title.setText("Account");

            findNavController(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.fragment_container_view_sub_menu))).
                    setGraph(R.navigation.navigation_account);//(developer Android NavController, n.d)
            _subMenu.show();
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
                switch (position)
                {
                    case 0:
                        TextView _textView_sub_menu_title = _subMenu.findViewById(R.id.textView_sub_menu_title);

                        assert _textView_sub_menu_title != null;
                        _textView_sub_menu_title.setText("Settings");

                        findNavController(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.fragment_container_view_sub_menu))).
                                setGraph(R.navigation.navigation_settings);//(developer Android NavController, n.d)
                        _subMenu.show();
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this,
                                "Help", Toast.LENGTH_LONG).show();
                        break;
                }
                break;
        }

    }

    //https://www.youtube.com/watch?v=4_RK_5bCoOY&t=929s
    private boolean isLocationServiceRunning(){
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        Log.d("counttt", "isLocationServiceRunning: " + activityManager.getRunningServices(Integer.MAX_VALUE).size());
        for(ActivityManager.RunningServiceInfo service:
        activityManager.getRunningServices(Integer.MAX_VALUE))
        {
            Log.d("myresult", "isLocationServiceRunning: ");
            if(LocationService.class.getName().equals(service.service.getClassName())){
                if(service.foreground)
                {
                    return true;
                }
            }
        }
        return false;
    }
//https://www.youtube.com/watch?v=4_RK_5bCoOY&t=929s
    private void startLocationService(){
        Log.d("startLocationService", "the method: ");

            Intent intent = new Intent(getApplicationContext(), LocationService.class);
            intent.setAction(Constants.ACTION_START_LOCATION_SERVICE);
            startService(intent);
            Toast.makeText(MainActivity.this,
                    "Location service started", Toast.LENGTH_LONG).show();

    }

    //https://www.youtube.com/watch?v=4_RK_5bCoOY&t=929s
    private void stopLocationService(){
        if(isLocationServiceRunning()){
            Intent intent = new Intent(getApplicationContext(), LocationService.class);
            intent.setAction(Constants.ACTION_STOP_LOCATION_SERVICE);
            startService(intent);
            Toast.makeText(MainActivity.this,
                    "Location service stopped", Toast.LENGTH_LONG).show();
        }
    }
}