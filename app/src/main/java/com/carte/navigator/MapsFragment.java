package com.carte.navigator;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;
import java.util.Objects;

public class MapsFragment extends Fragment {
//Need this later
    //Changing map types
    //    https://stackoverflow.com/questions/44646749/change-the-map-type-in-an-android-app

    //Markers
    // https://www.geeksforgeeks.org/how-to-add-custom-marker-to-google-maps-in-android/
    private static GoogleMap _map;
    public static Location _currentLocation;
    static Bitmap _smallMarker;
    public static HashMap<Integer, Marker> _hashMapMarker;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng sydney = new LatLng(30.5595, 22.9375);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            _map = googleMap;

//          https://stackoverflow.com/questions/35718103/how-to-specify-the-size-of-the-icon-on-the-marker-in-google-maps-v2-android
            int height = 100;
            int width = 100;

            //https://stackoverflow.com/questions/53811117/how-to-get-string-from-resources-strings-into-a-fragment
            BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.image_supermarket_icon);
            Bitmap b = bitmapdraw.getBitmap();
            _smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

            //https://stackoverflow.com/questions/42401131/add-marker-on-long-press-in-google-maps-api-v3
            _map.setOnMapLongClickListener(latLng -> {
                MainActivity._subMenu.dismiss();
                Marker marker = _map.addMarker(new MarkerOptions()
                        .position(latLng));
                if(_hashMapMarker.isEmpty())
                {
                    _hashMapMarker.put(0,marker);//0 will always refer to the long click on map
                }
                else
                {
                    Marker removeMarker = _hashMapMarker.get(0);
                    assert removeMarker != null;
                    removeMarker.remove();
                    _hashMapMarker.put(0,marker);//0 will always refer to the long click on map
                }

                ConstraintLayout constraintLayoutTitle = MainActivity._subMenu.findViewById(R.id.constraint_layout_title);
                assert constraintLayoutTitle != null;

                constraintLayoutTitle.setVisibility(View.GONE);

                findNavController(Objects.requireNonNull(MainActivity._fragmentManager.findFragmentById(R.id.fragment_container_view_sub_menu))).
                        setGraph(R.navigation.navigation_info_directions);//(developer Android NavController, n.d)
                MainActivity._subMenu.show();
            });
        }
    };


    public static void setUpMap()
    {
        LatLng currentLocation = new LatLng(_currentLocation.getLatitude(), _currentLocation.getLongitude());
        //https://stackoverflow.com/questions/14811579/how-to-create-a-custom-shaped-bitmap-marker-with-android-map-api-v2
        _map.addMarker(new MarkerOptions()
                .position(currentLocation)
                .title("Current location")
                .snippet("Population: 4,627,300")
                        .icon(BitmapDescriptorFactory.fromBitmap(_smallMarker)));
        _map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,20.0f));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //https://stackoverflow.com/questions/40436277/how-to-remove-specific-marker-on-android-googlemap
        _hashMapMarker = new HashMap<>();
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}