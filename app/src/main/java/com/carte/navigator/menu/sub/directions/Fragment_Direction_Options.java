package com.carte.navigator.menu.sub.directions;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carte.navigator.MainActivity;
import com.carte.navigator.MapsFragment;
import com.carte.navigator.R;
import com.carte.navigator.menu.trueway_directions_json.Root;
import com.google.android.gms.maps.model.Marker;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Direction_Options extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View direction_options =inflater.inflate(R.layout.fragment_direction_options, container, false);

        //button_direction_start
        //imageButton_close_direction_options_menu
        //autoCompleteTextView_destination
        //autoCompleteTextView_origin
        //textView_distance
        //textView_Time



        //https://blog.logrocket.com/a-complete-guide-to-okhttp/
        OkHttpClient client = new OkHttpClient();

        Marker marker = MapsFragment._hashMapMarker.get(0);
        assert marker != null;
        Request request = new Request.Builder()
                .url("https://trueway-directions2.p.rapidapi.com/FindDrivingRoute?stops="
                        +MapsFragment._currentLocation.getLatitude() + "," + MapsFragment._currentLocation.getLongitude()+";"
                        +marker.getPosition().latitude + "," + marker.getPosition().longitude)
                .get()
                .addHeader("X-RapidAPI-Key", "53834ed534msh54ac889e9f4d46dp160901jsn7121157456bc")
                .addHeader("X-RapidAPI-Host", "trueway-directions2.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //String responseBody = Objects.requireNonNull(response.body()).toString();
               // Log.d("thisResponse", "onResponse: " +responseBody );
                Gson gson = new Gson();
               // Root root = gson.fromJson(response.body(),Root.class);
               Root root = gson.fromJson(response.body().string(),Root.class);

               getActivity().runOnUiThread(new Runnable() {
                   @Override
                   public void run() {

                   }
               });
               //Log.d("thisResponse", "onResponse: this is distance "+ root.getRoute().getDistance());
            }
        });

        return direction_options;
    }


}