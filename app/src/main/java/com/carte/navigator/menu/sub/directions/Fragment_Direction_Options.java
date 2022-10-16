package com.carte.navigator.menu.sub.directions;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
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

    TextView _distance, _time;
    AutoCompleteTextView _origin, _destination;
    ImageButton _close;
    Button _start_Directions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View direction_options =inflater.inflate(R.layout.fragment_direction_options, container, false);

        //button_direction_start

        _distance = direction_options.findViewById(R.id.textView_distance);
        _time = direction_options.findViewById(R.id.textView_Time);
        _origin = direction_options.findViewById(R.id.autoCompleteTextView_origin);
        _destination = direction_options.findViewById(R.id.autoCompleteTextView_destination);
        _close = direction_options.findViewById(R.id.imageButton_close_direction_options_menu);
        _start_Directions = direction_options.findViewById(R.id.button_direction_start);

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

               requireActivity().runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       String time, distance;
                       int duration = root.getRoute().getDuration();
                       if(duration<60)
                       {
                           time = "< 1 min" ;
                       }else
                       {
                           float dur = duration/60f;
                           time = Math.round(dur) + " min";
                       }
                       _time.setText(time);

                       //Check if user is using miles or metres
                       distance = root.getRoute().getDistance() + "m";
                       _distance.setText(distance);
                   }
               });
               //Log.d("thisResponse", "onResponse: this is distance "+ root.getRoute().getDistance());
            }
        });

        return direction_options;
    }


}