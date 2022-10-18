package com.carte.navigator.menu.sub.directions;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.carte.navigator.mapRelated.MapsFragment;
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

        Marker marker = MapsFragment._hashMapMarker.get(1);

        TextView marker_Title, location_data;

        marker_Title = marker_information_view.findViewById(R.id.textView_marker_Title);
        location_data = marker_information_view.findViewById(R.id.textView_location_marker_data);
        Button directions = marker_information_view.findViewById(R.id.button_information_directions);

        assert marker != null;
        String title = marker.getPosition().latitude + "," + marker.getPosition().longitude;
        marker_Title.setText(title);

        directions.setOnClickListener(view -> {
            Navigation.findNavController(marker_information_view).navigate(R.id.action_fragment_nearby_info_to_fragment_Direction_Options2);
        });
        return marker_information_view;
    }


}