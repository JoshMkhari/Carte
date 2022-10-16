package com.carte.navigator.menu.sub.directions;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.carte.navigator.MainActivity;
import com.carte.navigator.MapsFragment;
import com.carte.navigator.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Start_Directions extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View navigating = inflater.inflate(R.layout.fragment_start_directions, container, false);

        TextView distance_left, distance_left_unit_of_measurement,time_remaining, time_remaining_unit_measurement, arrival_time;
        ImageButton imageButton_stop_navigation;

        distance_left = navigating.findViewById(R.id.textView_distance_left);
        time_remaining_unit_measurement = navigating.findViewById(R.id.textView_time_remaining_min_hour);
        time_remaining = navigating.findViewById(R.id.textView_time_remaining);
        arrival_time = navigating.findViewById(R.id.textView_arrival_time);
        distance_left_unit_of_measurement = navigating.findViewById(R.id.textView_distance_left_km_miles);
        imageButton_stop_navigation = navigating.findViewById(R.id.imageButton_stop_navigating_menu);

        /*
        _navigationLayout = findViewById(R.id.constraint_layout_navigating);
        _currentSpeed = findViewById(R.id.textView_currentSpeed_layout);
        _currentStreet = findViewById(R.id.textView_current_street);
        _volume_control = findViewById(R.id.volume_control);

        _volume_control.setOnClickListener(view -> {
            //Switch background
            Bitmap bmp;
            if(_muted)
            {
                _muted = false;
                //https://stackoverflow.com/questions/3035692/how-to-convert-a-drawable-to-a-bitmap
                bmp = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                        R.drawable.image_mute_icon);

            }else
            {
                //https://stackoverflow.com/questions/3035692/how-to-convert-a-drawable-to-a-bitmap
                _muted= true;
                bmp = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                        R.drawable.image_volume_mute_icon);
            }
            _volume_control.setImageBitmap(bmp);
        });
         */

        // Gets the layout params that will allow you to resize the layout
        ViewGroup.LayoutParams params = MainActivity._menu.getLayoutParams();
        // Changes the height and width to the specified *pixels*
        params.height = 0;
        MainActivity._menu.setLayoutParams(params);
        MapsFragment.startNavigation();
        return navigating;
    }
}