package com.carte.navigator;

import static com.carte.navigator.mapRelated.UserLandmarks._ExtraDetailsOfLandmarks;
import static com.carte.navigator.mapRelated.UserLandmarks._Landmarks;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.carte.navigator.mapRelated.MapsFragment;
import com.carte.navigator.mapRelated.UserLandmarks;
import com.carte.navigator.menu.sub.directions.Fragment_Direction_Options;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.model.OpeningHours;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_nearby_info extends Fragment {

    public static Marker _marker;
    public static Place _place;

    private static Place  _LandmarkSpecifications;
    public static String _AddressOfLandmark = "Unavailable";
    private static OpeningHours _BusinessHoursOfLandmark;
    public static String _RatingsOfLandmarks = "Unavailable";
    public static String _ContactDetailsOfLandmark = "Unavailable";
     static TextView hours;
     static TextView rating;
     static TextView details;
     static TextView marker_Title;
     static TextView location_data;
    public static HashMap<Integer,Place> placeHashMap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //MapsFragment._hashMapMarker
        View nearbyInfoView = inflater.inflate(R.layout.fragment_nearby_info, container, false);

        hours = nearbyInfoView.findViewById(R.id.nearbyHours);
        rating = nearbyInfoView.findViewById(R.id.nearbyRating);
        details = nearbyInfoView.findViewById(R.id.nearbyDetails);
        marker_Title = nearbyInfoView.findViewById(R.id.textView_marker_Title_nearby);
        location_data = nearbyInfoView.findViewById(R.id.textView_location_marker_data_nearby);
        Button directions = nearbyInfoView.findViewById(R.id.button_information_directions_nearby);

        //assert _marker != null;
        //String title = _marker.getPosition().latitude + "," + _marker.getPosition().longitude;
        //marker_Title.setText(title);
        directions.setOnClickListener(view -> {
            for (HashMap.Entry<Integer, Marker> set :
                    MapsFragment._hashMapMarker.entrySet()) {
                if(set.getKey()!=0&& set.getKey()!=Fragment_Direction_Options.key)
                {
                    Marker removeMarker = MapsFragment._hashMapMarker.get(set.getKey());
                    if(removeMarker ==null)
                    {
                        continue;
                    }
                    removeMarker.remove();
                }
            }
            Navigation.findNavController(nearbyInfoView).navigate(R.id.action_fragment_nearby_info_to_fragment_Direction_Options2);
        });


        return nearbyInfoView;
    }

    public static void GetLandmarkDetails(Place currentPlace){
        String PlaceID = currentPlace.getId();
        //This programming statement was adapted from Google Maps Platform:
        //Link: https://developers.google.com/maps/documentation/places/android-sdk/place-details
        //Author: Google Developers
        assert PlaceID != null;
        FetchPlaceRequest CurrentPlace = FetchPlaceRequest.newInstance(PlaceID,_ExtraDetailsOfLandmarks);

        //This programming statement was adapted from Google Maps Platform:
        //Link: https://developers.google.com/maps/documentation/places/android-sdk/place-details
        //Author: Google Developers
        _Landmarks.fetchPlace(CurrentPlace).addOnSuccessListener(new OnSuccessListener<FetchPlaceResponse>() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onSuccess(FetchPlaceResponse fetchPlaceResponse) {
                //This programming statement was adapted from Google Maps Platform:
                //Link: https://developers.google.com/maps/documentation/places/android-sdk/place-details
                //Author: Google Developers
                _LandmarkSpecifications=fetchPlaceResponse.getPlace();

                _BusinessHoursOfLandmark =  _LandmarkSpecifications.getOpeningHours();
                if(_AddressOfLandmark== null || _AddressOfLandmark.equals("null")){
                    _AddressOfLandmark = "Unavailable";
                }else{
                    _AddressOfLandmark =  _LandmarkSpecifications.getAddress();
                }

                if(_RatingsOfLandmarks== null || _RatingsOfLandmarks.equals("null")){
                    _RatingsOfLandmarks =  "Unavailable";
                }else{
                    _RatingsOfLandmarks = String.valueOf(_LandmarkSpecifications.getRating());
                }

                if(_ContactDetailsOfLandmark == null || _ContactDetailsOfLandmark.equals("null")){
                    _ContactDetailsOfLandmark  = "Unavailable";
                }else{
                    _ContactDetailsOfLandmark = _LandmarkSpecifications.getPhoneNumber();
                }

                rating.setText(_RatingsOfLandmarks);
                details.setText(_ContactDetailsOfLandmark);
                if(_LandmarkSpecifications.getName() == null)
                {
                    marker_Title.setText(UserLandmarks.returnLandmarkType(MainActivity._currentModelUser.getUserPreference()).toString());
                }else
                {
                    marker_Title.setText(_LandmarkSpecifications.getName());
                }

                location_data.setText(_LandmarkSpecifications.getAddress());

                if(_BusinessHoursOfLandmark == null)
                {
                    hours.setText("Unavailable");
                }else
                {
                    hours.setText(_BusinessHoursOfLandmark.getWeekdayText().get(0));
                }

            }
        });

    }
}