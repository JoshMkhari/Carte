package com.carte.navigator.menu.sub.directions;

import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.carte.navigator.MainActivity;
import com.carte.navigator.mapRelated.MapsFragment;
import com.carte.navigator.R;
import com.carte.navigator.menu.models.Model_User_Collections;
import com.carte.navigator.menu.trueway_directions_json.EndPoint;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Marker_Information extends Fragment {
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View marker_information_view = inflater.inflate(R.layout.fragment_marker_information, container, false);
        //MapsFragment._hashMapMarker

        Marker marker = MapsFragment._hashMapMarker.get(1);

        Geocoder geocoder = new Geocoder(requireContext());

        ImageButton favourite = marker_information_view.findViewById(R.id.imageButtonFavourite);
        TextView marker_Title, location_data;

        marker_Title = marker_information_view.findViewById(R.id.textView_marker_Title);
        location_data = marker_information_view.findViewById(R.id.textView_location_marker_data);
        Button directions = marker_information_view.findViewById(R.id.button_information_directions);

        assert marker != null;
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(marker.getPosition().latitude, marker.getPosition().longitude, 1);
            location_data.setText(addresses.get(0).getAddressLine(0));//check other features to see what else can be retrieved
        }catch (Exception e)
        {
            location_data.setText(marker.getPosition().latitude + "," + marker.getPosition().longitude);
        }
        String title = "DROPPED PIN";
        marker_Title.setText(title);


        List<Address> finalAddresses = addresses;
        favourite.setOnClickListener(view -> {
            EndPoint endPoint = new EndPoint();
            endPoint.setLat(marker.getPosition().latitude);
            endPoint.setLng(marker.getPosition().longitude);
            Model_User_Collections model_user_collections = new Model_User_Collections(finalAddresses.get(0).getAddressLine(0),endPoint);
            if(MainActivity._currentModelUser.getModel_user_collections() == null)
                MainActivity._currentModelUser.initializeUserCollections();
            MainActivity._currentModelUser.getModel_user_collections().add(model_user_collections);
            Toast.makeText(requireContext(),
                    "Location has been stored ", Toast.LENGTH_LONG).show();
        });

        directions.setOnClickListener(view -> {
            Navigation.findNavController(marker_information_view).navigate(R.id.action_fragment_nearby_info_to_fragment_Direction_Options2);
        });
        return marker_information_view;
    }


}