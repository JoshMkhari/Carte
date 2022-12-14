package com.carte.navigator.mapRelated;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.carte.navigator.menu.sub.directions.Fragment_nearby_info;
import com.carte.navigator.MainActivity;
import com.carte.navigator.R;
import com.carte.navigator.menu.sub.directions.Fragment_Direction_Options;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.OpeningHours;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class UserLandmarks {
    private Context _context;
    private Place _LocalLandmarks;
    private int _NumberOfDetailsAboutLandmarks;

    private LatLng _CurrentPositionCoOrdinates;
    private MarkerOptions _CurrentPositionOnMap, _LandmarksNearMe;

    public static PlacesClient _Landmarks;
    private List<Place.Field> _BasicDetailsOfLandmarks;
    public static List<Place.Field> _ExtraDetailsOfLandmarks;

    private String[] _NamesOfLandmarksNearMe;
    private String[] _TypeOfLandmarksNearMe;
    private LatLng[] _CoordinatesOfLandmarksNearMe;
    private String[] _IDsOfLandmarksNearMe;


    private Place  _LandmarkSpecifications;
    public String _AddressOfLandmark = "Unavailable";
    private OpeningHours _BusinessHoursOfLandmark;
    public String _RatingsOfLandmarks = "Unavailable";
    public String _ContactDetailsOfLandmark = "Unavailable";
    public String _Filter;

    private static final int _M_MAX_ENTRIES = 15;
    private boolean _SuccessfulFilter;

    public LatLng getCurrentPositionCoOrdinates() {
        return _CurrentPositionCoOrdinates;
    }

    public void setCurrentPositionCoOrdinates(LatLng currentPositionCoOrdinates) {
        _CurrentPositionCoOrdinates = currentPositionCoOrdinates;
    }

    public MarkerOptions getCurrentPositionOnMap() {
        return _CurrentPositionOnMap;
    }

    public void setCurrentPositionOnMap(MarkerOptions currentPositionOnMap) {
        _CurrentPositionOnMap = currentPositionOnMap;
    }

    public MarkerOptions getLandmarksNearMe() {
        return _LandmarksNearMe;
    }

    public void setLandmarksNearMe(MarkerOptions landmarksNearMe) {
        _LandmarksNearMe = landmarksNearMe;
    }

    public PlacesClient getLandmarks() {
        return _Landmarks;
    }

    public void setLandmarks(PlacesClient landmarks) {
        _Landmarks = landmarks;
    }

    public List<Place.Field> getBasicDetailsOfLandmarks() {
        return _BasicDetailsOfLandmarks;
    }

    public void setBasicDetailsOfLandmarks(List<Place.Field> basicDetailsOfLandmarks) {
        _BasicDetailsOfLandmarks = basicDetailsOfLandmarks;
    }

    public List<Place.Field> getExtraDetailsOfLandmarks() {
        return _ExtraDetailsOfLandmarks;
    }

    public void setExtraDetailsOfLandmarks(List<Place.Field> extraDetailsOfLandmarks) {
        _ExtraDetailsOfLandmarks = extraDetailsOfLandmarks;
    }

    public String[] getNamesOfLandmarksNearMe() {
        return _NamesOfLandmarksNearMe;
    }

    public void setNamesOfLandmarksNearMe(String[] namesOfLandmarksNearMe) {
        _NamesOfLandmarksNearMe = namesOfLandmarksNearMe;
    }

    public String[] getTypeOfLandmarksNearMe() {
        return _TypeOfLandmarksNearMe;
    }

    public void setTypeOfLandmarksNearMe(String[] typeOfLandmarksNearMe) {
        _TypeOfLandmarksNearMe = typeOfLandmarksNearMe;
    }

    public LatLng[] getCoordinatesOfLandmarksNearMe() {
        return _CoordinatesOfLandmarksNearMe;
    }

    public void setCoordinatesOfLandmarksNearMe(LatLng[] coordinatesOfLandmarksNearMe) {
        _CoordinatesOfLandmarksNearMe = coordinatesOfLandmarksNearMe;
    }

    public String[] getIDsOfLandmarksNearMe() {
        return _IDsOfLandmarksNearMe;
    }

    public void setIDsOfLandmarksNearMe(String[] IDsOfLandmarksNearMe) {
        this._IDsOfLandmarksNearMe = IDsOfLandmarksNearMe;
    }

    public Place getLandmarkSpecifications() {
        return _LandmarkSpecifications;
    }

    public void setLandmarkSpecifications(Place landmarkSpecifications) {
        _LandmarkSpecifications = landmarkSpecifications;
    }

    public String getAddressOfLandmark() {
        return _AddressOfLandmark;
    }

    public void setAddressOfLandmark(String addressOfLandmark) {
        _AddressOfLandmark = addressOfLandmark;
    }

    public OpeningHours getBusinessHoursOfLandmark() {
        return _BusinessHoursOfLandmark;
    }

    public void setBusinessHoursOfLandmark(OpeningHours businessHoursOfLandmark) {
        _BusinessHoursOfLandmark = businessHoursOfLandmark;
    }

    public String getRatingsOfLandmarks() {
        return _RatingsOfLandmarks;
    }

    public void setRatingsOfLandmarks(String ratingsOfLandmarks) {
        _RatingsOfLandmarks = ratingsOfLandmarks;
    }

    public String getContactDetailsOfLandmark() {
        return _ContactDetailsOfLandmark;
    }

    public void setContactDetailsOfLandmark(String contactDetailsOfLandmark) {
        _ContactDetailsOfLandmark = contactDetailsOfLandmark;
    }

    public String getFilter() {
        return _Filter;
    }

    public void setFilter(String filter) {
        _Filter = filter;
    }

    public boolean isSuccessfulFilter() {
        return _SuccessfulFilter;
    }

    public void setSuccessfulFilter(boolean successfulFilter) {
        _SuccessfulFilter = successfulFilter;
    }

    public UserLandmarks(Context context) {
        _context = context;
        //This programming statement was adapted from Google Maps Platform:
        //Link: https://developers.google.com/maps/documentation/places/android-sdk/current-place-tutorial
        //Author: Google Developers
        Places.initialize(context, "AIzaSyC0uNobmiiWCiGqciPYlcUdVvXTmZTHJZ4");

        //This programming statement was adapted from Google Maps Platform:
        //Link: https://developers.google.com/maps/documentation/places/android-sdk/current-place-tutorial
        //Author: Google Developers
        _Landmarks = Places.createClient(context);

        //This programming statement was adapted from Google Maps Platform:
        //Link: https://developers.google.com/maps/documentation/places/android-sdk/current-place-tutorial
        //Author: Google Developers
        _BasicDetailsOfLandmarks = Arrays.asList(Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.TYPES,Place.Field.ID);
        //This programming statement was adapted from Google Maps Platform:
        //Link: https://developers.google.com/maps/documentation/places/android-sdk/current-place-tutorial
        //Author: Google Developers
        _ExtraDetailsOfLandmarks = Arrays.asList(Place.Field.ID, Place.Field.RATING, Place.Field.PHONE_NUMBER, Place.Field.ADDRESS);
    }

    public void GetLandMarksNearMeAndFilter(Place.Type userFilter) {

        //First clear map
        MapsFragment._map.clear();
        MapsFragment._map.addMarker(new MarkerOptions()
                .position(Objects.requireNonNull(MapsFragment._hashMapMarker.get(0)).getPosition())
                .title("You")
                .icon(BitmapDescriptorFactory.fromBitmap(MapsFragment._smallMarker)));

        //This programming statement was adapted from Google Maps Platform:
        //Link: https://developers.google.com/maps/documentation/places/android-sdk/current-place-tutorial
        //Author: Google Developers
        FindCurrentPlaceRequest GetMyLocation = FindCurrentPlaceRequest.newInstance(_BasicDetailsOfLandmarks);
        //This programming statement was adapted from Google Maps Platform:
        //Link: https://developers.google.com/maps/documentation/places/android-sdk/current-place-tutorial
        //Author: Google Developers
        //This programming statement was adapted from Google Maps Platform:
        //Link: https://developers.google.com/maps/documentation/places/android-sdk/current-place-tutorial
        //Author: Google Developers
        @SuppressLint("MissingPermission") Task<FindCurrentPlaceResponse> FoundLocation = _Landmarks.findCurrentPlace(GetMyLocation);
        //This programming statement was adapted from Google Maps Platform:
        //Link: https://developers.google.com/maps/documentation/places/android-sdk/current-place-tutorial
        //Author: Google Developers
        FoundLocation.addOnCompleteListener(new OnCompleteListener<FindCurrentPlaceResponse>() {
            //This method was adapted from Google Maps Platform:
            //Link: https://developers.google.com/maps/documentation/places/android-sdk/current-place-tutorial
            //Author: Google Developers
            @Override
            public void onComplete(@NonNull Task<FindCurrentPlaceResponse> task) {
                //This programming statement was adapted from Google Maps Platform:
                //Link: https://developers.google.com/maps/documentation/places/android-sdk/current-place-tutorial
                //Author: Google Developers
                if (task.isSuccessful() && task.getResult() != null) {
                    //This programming statement was adapted from Google Maps Platform:
                    //Link: https://developers.google.com/maps/documentation/places/android-sdk/current-place-tutorial
                    //Author: Google Developers
                    FindCurrentPlaceResponse FoundLocationResponse = task.getResult();
                    int MaxNumberOfEntries;
                    //This programming statement was adapted from Google Maps Platform:
                    //Link: https://developers.google.com/maps/documentation/places/android-sdk/current-place-tutorial
                    //Author: Google Developers
                    if (FoundLocationResponse.getPlaceLikelihoods().size() < _M_MAX_ENTRIES) {
                        //This programming statement was adapted from Google Maps Platform:
                        //Link: https://developers.google.com/maps/documentation/places/android-sdk/current-place-tutorial
                        //Author: Google Developers
                        MaxNumberOfEntries = FoundLocationResponse.getPlaceLikelihoods().size();
                    } else {
                        //This programming statement was adapted from Google Maps Platform:
                        //Link: https://developers.google.com/maps/documentation/places/android-sdk/current-place-tutorial
                        //Author: Google Developers
                        MaxNumberOfEntries = _M_MAX_ENTRIES;
                    }
                    _NumberOfDetailsAboutLandmarks = 0;
                    //This programming statement was adapted from Google Maps Platform:
                    //Link: https://developers.google.com/maps/documentation/places/android-sdk/current-place-tutorial
                    //Author: Google Developers
                    _NamesOfLandmarksNearMe = new String[MaxNumberOfEntries];
                    //This programming statement was adapted from Google Maps Platform:
                    //Link: https://developers.google.com/maps/documentation/places/android-sdk/current-place-tutorial
                    //Author: Google Developers
                    _TypeOfLandmarksNearMe = new String[MaxNumberOfEntries];
                    //This programming statement was adapted from Google Maps Platform:
                    //Link: https://developers.google.com/maps/documentation/places/android-sdk/current-place-tutorial
                    //Author: Google Developers
                    _CoordinatesOfLandmarksNearMe = new LatLng[MaxNumberOfEntries];
                    //This programming statement was adapted from Google Maps Platform:
                    //Link: https://developers.google.com/maps/documentation/places/android-sdk/current-place-tutorial
                    //Author: Google Developers
                    _IDsOfLandmarksNearMe = new String[MaxNumberOfEntries];
                    //This programming statement was adapted from Google Maps Platform:
                    //Link: https://developers.google.com/maps/documentation/places/android-sdk/current-place-tutorial
                    //Author: Google Developers
                    int key = 3;
                    for (PlaceLikelihood PossibleLandmarksNearMe : FoundLocationResponse.getPlaceLikelihoods()) {
                        //This programming statement was adapted from Google Maps Platform:
                        //Link: https://developers.google.com/maps/documentation/places/android-sdk/place-details
                        //Author: Google Developers
                        _LocalLandmarks = PossibleLandmarksNearMe.getPlace();

                        if (_LocalLandmarks.getName() != null && _LocalLandmarks.getLatLng() != null && _LocalLandmarks.getTypes().contains(userFilter)) {
                            //This programming statement was adapted from Google Maps Platform:
                            //Link: https://developers.google.com/maps/documentation/places/android-sdk/current-place-tutorial
                            //Author: Google Developers
                            _NamesOfLandmarksNearMe[_NumberOfDetailsAboutLandmarks] = _LocalLandmarks.getName();
                            //This programming statement was adapted from Google Maps Platform:
                            //Link: https://developers.google.com/maps/documentation/places/android-sdk/current-place-tutorial
                            //Author: Google Developers
                            _TypeOfLandmarksNearMe[_NumberOfDetailsAboutLandmarks] = String.valueOf(_LocalLandmarks.getTypes());
                            //This programming statement was adapted from Google Maps Platform:
                            //Link: https://developers.google.com/maps/documentation/places/android-sdk/current-place-tutorial
                            //Author: Google Developers
                            _CoordinatesOfLandmarksNearMe[_NumberOfDetailsAboutLandmarks] = _LocalLandmarks.getLatLng();
                            //This programming statement was adapted from Google Maps Platform:
                            //Link: https://developers.google.com/maps/documentation/places/android-sdk/current-place-tutorial
                            //Author: Google Developers
                            int height = 100;
                            int width = 100;
                            _IDsOfLandmarksNearMe[_NumberOfDetailsAboutLandmarks] = _LocalLandmarks.getId();
                            BitmapDrawable bitmapdraw = null;

                            
                            if(userFilter.equals(Place.Type.RESTAURANT))
                            {
                                bitmapdraw = MapsFragment.bitmapdrawReast;
                            }
                            if(userFilter.equals(Place.Type.SUPERMARKET))
                            {
                                bitmapdraw = MapsFragment.bitmapdrawSuper;
                            }
                            if(userFilter.equals(Place.Type.POINT_OF_INTEREST))
                            {
                                bitmapdraw = MapsFragment.bitmapdrawAttrc;
                            }
                            if(userFilter.equals(Place.Type.FOOD))
                            {
                                bitmapdraw = MapsFragment.bitmapdrawFood;
                            }
                            Bitmap b = bitmapdraw.getBitmap();
                            Bitmap _smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                            //LocalLandmarks.getTypes().contains(Place.Type.PARK);
                            //This programming statement was adapted from Treehouse:
                            //Link: https://blog.teamtreehouse.com/beginners-guide-location-android
                            //Author: Ben Jakuben
                            //Author Profile Link: https://blog.teamtreehouse.com/author/benjakuben
                            _LandmarksNearMe = new MarkerOptions();

                            _LandmarksNearMe.icon(BitmapDescriptorFactory.fromBitmap(_smallMarker));

                            //This programming statement was adapted from Google Maps Platform:
                            //Link: https://developers.google.com/maps/documentation/android-sdk/marker
                            //Author: Google Developers
                            _LandmarksNearMe.position(_CoordinatesOfLandmarksNearMe[_NumberOfDetailsAboutLandmarks]).title(_NamesOfLandmarksNearMe[_NumberOfDetailsAboutLandmarks]).snippet(_TypeOfLandmarksNearMe[_NumberOfDetailsAboutLandmarks]);
                            //This programming statement was adapted from GeeksForGeeks:
                            //Link: https://www.geeksforgeeks.org/how-to-implement-current-location-button-feature-in-google-maps-in-android/
                            //Author: aashaypawar
                            //Author Profile Link: https://auth.geeksforgeeks.org/user/aashaypawar

                            Marker marker = MapsFragment._map.addMarker(_LandmarksNearMe);
                            Fragment_nearby_info.placeHashMap.put(key,_LocalLandmarks);
                            MapsFragment._hashMapMarker.put(key,marker);
                            key++;
                            MapsFragment._map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(@NonNull Marker marker) {
                                    if(Fragment_Direction_Options.routeDrawn)
                                    {
                                        MainActivity._subMenu.show();
                                    }else
                                    {
                                        findNavController(Objects.requireNonNull(MainActivity._fragmentManager.findFragmentById(R.id.fragment_container_view_sub_menu))).
                                                setGraph(R.navigation.navigation_info_nearby);//(developer Android NavController, n.d)
                                        ConstraintLayout constraintLayoutTitle = MainActivity._subMenu.findViewById(R.id.constraint_layout_title);
                                        assert constraintLayoutTitle != null;

                                        constraintLayoutTitle.setVisibility(View.GONE);


                                        for (HashMap.Entry<Integer, Marker> set :
                                                MapsFragment._hashMapMarker.entrySet()) {
                                            if(set.getValue().getPosition().equals(marker.getPosition()))
                                            {
                                                Fragment_nearby_info.GetLandmarkDetails(Objects.requireNonNull(Fragment_nearby_info.placeHashMap.get(set.getKey())));
                                                Fragment_Direction_Options.currentLocation = new LatLng(marker.getPosition().latitude,marker.getPosition().longitude);
                                                Fragment_Direction_Options.key = set.getKey();
                                                Fragment_Direction_Options.nearby= true;
                                                MainActivity._subMenu.show();
                                                return true ;
                                            }
                                        }
                                    }

                                    return true;
                                }
                            });
                            _SuccessfulFilter = true;
                        }
                        _NumberOfDetailsAboutLandmarks++;
                        //This programming statement was adapted from Google Maps Platform:
                        //Link: https://developers.google.com/maps/documentation/places/android-sdk/current-place-tutorial
                        //Author: Google Developers
                        if (_NumberOfDetailsAboutLandmarks > (MaxNumberOfEntries - 1)) {
                            break;
                        }
                    }
                    Toast.makeText(_context, "Landmarks have been identified successfully.", Toast.LENGTH_SHORT).show();
                    if(_SuccessfulFilter){
                        //change image to white halo
                        Toast.makeText(_context, "Filter has been applied successfully.", Toast.LENGTH_SHORT).show();
                    }else{
                        //Change image to red
                        Toast.makeText(_context, "Filter has been not applied successfully.", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }

    public static Place.Type returnLandmarkType(int type)
    {
        switch (type)
        {
            //Also remove names from icons for Light mode xD
            case 0:
                return Place.Type.RESTAURANT;
            case 1:
                return Place.Type.SUPERMARKET;
            case 2:
                return Place.Type.POINT_OF_INTEREST;
            default:
                return Place.Type.FOOD;
        }
    }


    public void GetLandmarkDetails(){
        String PlaceID = _LocalLandmarks.getId();
        //This programming statement was adapted from Google Maps Platform:
        //Link: https://developers.google.com/maps/documentation/places/android-sdk/place-details
        //Author: Google Developers
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
            }
        });

    }
}
