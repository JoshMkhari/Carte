package com.carte.navigator.mapRelated;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.carte.navigator.MainActivity;
import com.carte.navigator.R;
import com.carte.navigator.menu.Constants;
import com.carte.navigator.menu.sub.directions.Fragment_Start_Directions;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.libraries.places.api.model.Place;

import java.util.concurrent.CountDownLatch;
//https://www.youtube.com/watch?v=4_RK_5bCoOY&t=929s
public class LocationService extends Service {
    public final int REQUEST_CODE = 0;
    public static Location _currentLocation;
    public static CountDownLatch latch = new CountDownLatch(1);
    public static boolean changed = false;
    private final LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            super.onLocationResult(locationResult);
            if(locationResult.getLastLocation() != null)
            {
                MapsFragment._currentLocation = locationResult.getLastLocation();
                if(!changed)
                {
                    MapsFragment.setUpMap(UserLandmarks.returnLandmarkType(MainActivity._currentModelUser.getUserPreference()));
                    Log.d("please", "onLocationResult: "+ MainActivity._currentModelUser.getUserPreference());
                    changed = true;
                }
                if(MapsFragment._currentlyNavigating)
                {
                    Fragment_Start_Directions.updateUiElements();
                }
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //@SuppressLint("MissingPermission")
    @SuppressLint("MissingPermission")
    private void startLocationService(){
        String channelID = "location_notification_channel";
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent resultIntent = new Intent();
        PendingIntent pendingIntent;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getBroadcast(
                    getApplication(),
                    REQUEST_CODE,
                    resultIntent,
                    PendingIntent.FLAG_MUTABLE
            );
        } else {
            pendingIntent = PendingIntent.getBroadcast(
                    getApplication(),
                    REQUEST_CODE,
                    resultIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                getApplicationContext(),
                channelID
        );
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Location Service");
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setContentText("Running");
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(false);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            if(notificationManager != null && notificationManager.getNotificationChannel(channelID) == null)
            {
                NotificationChannel notificationChannel= new NotificationChannel(
                        channelID,
                        "Location Service",
                        NotificationManager.IMPORTANCE_HIGH
                );
                notificationChannel.setDescription("This channel is used byo location service");
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        LocationRequest locationRequest = LocationRequest.create(); //new LocationRequest is deprecated
        locationRequest.setInterval(4000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(100);//High Accuracy

        LocationServices.getFusedLocationProviderClient(this)
                .requestLocationUpdates(locationRequest,locationCallback, Looper.getMainLooper());
        startForeground(com.carte.navigator.menu.Constants.LOCATION_SERVICE_ID, builder.build());
    }

    private void stopLocationService(){
        LocationServices.getFusedLocationProviderClient(this).
                removeLocationUpdates(locationCallback);
        stopForeground(true);
        stopSelf();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null){
            String action = intent.getAction();
            if(action != null)
            {
                if(action.equals(Constants.ACTION_START_LOCATION_SERVICE)){
                    startLocationService();
                }else if (action.equals(Constants.ACTION_STOP_LOCATION_SERVICE)){
                    stopLocationService();
                }
            }
        }
        return super.onStartCommand(intent,flags,startId);
    }
}
