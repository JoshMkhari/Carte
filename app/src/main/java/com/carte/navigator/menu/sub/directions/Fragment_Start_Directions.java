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

    boolean _muted;
    View _navigating;
    static TextView _distance_left, _distance_left_unit_of_measurement, _time_remaining, _time_remaining_unit_measurement, _arrival_time, _next_turn, _currentSpeed;
    static ImageButton _imageButton_stop_navigation, _imageButton_volume_control;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _navigating = inflater.inflate(R.layout.fragment_start_directions, container, false);
        _muted = false;


        _distance_left = _navigating.findViewById(R.id.textView_distance_left);
        _time_remaining_unit_measurement = _navigating.findViewById(R.id.textView_time_remaining_min_hour);
        _time_remaining = _navigating.findViewById(R.id.textView_time_remaining);
        _arrival_time = _navigating.findViewById(R.id.textView_arrival_time);
        _distance_left_unit_of_measurement = _navigating.findViewById(R.id.textView_distance_left_km_miles);
        _imageButton_stop_navigation = _navigating.findViewById(R.id.imageButton_stop_navigating_menu);
        _next_turn = _navigating.findViewById(R.id.textView_next_turn);
        _imageButton_volume_control = _navigating.findViewById(R.id.volume_control);
        _currentSpeed = _navigating.findViewById(R.id.textView_currentSpeed);


        _imageButton_volume_control.setOnClickListener(view -> {
            Bitmap bmp;
            if (_muted) {
                _muted = false;
                //https://stackoverflow.com/questions/3035692/how-to-convert-a-drawable-to-a-bitmap
                bmp = BitmapFactory.decodeResource(requireContext().getResources(),
                        R.drawable.image_mute_icon);

            } else {
                //https://stackoverflow.com/questions/3035692/how-to-convert-a-drawable-to-a-bitmap
                _muted = true;
                bmp = BitmapFactory.decodeResource(requireContext().getResources(),
                        R.drawable.image_volume_mute_icon);
            }
            _imageButton_volume_control.setImageBitmap(bmp);
        });

        MapsFragment.startNavigation();
        return _navigating;
    }

    public static void updateUiElements()
    {
        _currentSpeed.setText(Math.round(MapsFragment._currentLocation.getSpeed()));

        //_next_turn
    }
}