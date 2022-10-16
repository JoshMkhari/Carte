package com.carte.navigator.menu.sub.directions;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carte.navigator.MapsFragment;
import com.carte.navigator.R;
import com.google.android.gms.maps.model.Marker;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Marker_Information extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View marker_information_view = inflater.inflate(R.layout.fragment_marker_information, container, false);
        //MapsFragment._hashMapMarker

        Marker marker = MapsFragment._hashMapMarker.get(0);

        TextView marker_Title, location_data;

        marker_Title = marker_information_view.findViewById(R.id.textView_marker_Title);
        location_data = marker_information_view.findViewById(R.id.textView_location_marker_data);

        assert marker != null;
        String title = marker.getPosition().longitude + "," + marker.getPosition().latitude;
        marker_Title.setText(title);
        //textView_marker_Title
        //textView_location_data
        return marker_information_view;
    }


}