package com.example.tugbacevizci.weatherapp.intentservices;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.example.tugbacevizci.weatherapp.R;
import com.example.tugbacevizci.weatherapp.WeatherApplication;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class LocationService extends Service implements OnSuccessListener<Location> {
    private static final String NOTIFICATION_ID = "id";
    private static final String NOTIFICATION_NAME = "name";

    //Default value for Istanbul
    public static Double currentLat = 41.066352;
    public static Double currentLng = 29.013120;

    private FusedLocationProviderClient client;
    private LocationRequest locationRequest;
    private LocationCallback callback;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        init();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            final Intent broadCast = BroadcastHelper.broadcastIntent(BroadcastHelper.PERMISSION);
            broadCast.putExtra(BroadcastHelper.KEY_PERMISSION_REQUIRED, Manifest.permission.ACCESS_FINE_LOCATION);
            sendBroadcast(broadCast);
            return;
        }
        client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    updateCurrLocation(location);
                }
            }
        });
        startLocationUpdates();
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            final Intent broadCast = BroadcastHelper.broadcastIntent(BroadcastHelper.PERMISSION);
            broadCast.putExtra(BroadcastHelper.KEY_PERMISSION_REQUIRED, Manifest.permission.ACCESS_FINE_LOCATION);
            sendBroadcast(broadCast);
            return;
        }
        client.requestLocationUpdates(locationRequest, callback, null);
    }

    private void init() {
        client = LocationServices.getFusedLocationProviderClient(WeatherApplication.Instance());
        initLocationRequest();

        callback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult.getLastLocation() != null) {
                    updateCurrLocation(locationResult.getLastLocation());
                }
                for (final Location location : locationResult.getLocations()) {
                    updateCurrLocation(location);

                }
            }

        };

    }

    private void initLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void updateCurrLocation(Location lastLocation) {
        currentLat = lastLocation.getLatitude();
        currentLng = lastLocation.getLongitude();
    }

    @Override
    public void onSuccess(Location location) {
        if (location != null) {
            currentLat = location.getLatitude();
            currentLng = location.getLongitude();
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_ID, NOTIFICATION_NAME, importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            notificationManager.createNotificationChannel(notificationChannel);

            Notification.Builder builder = new Notification.Builder(this, NOTIFICATION_ID)
                    .setContentTitle(getString(R.string.app_name))
                    .setSmallIcon(R.drawable.ic_add)
                    .setContentText(getString(R.string.location_service_running))
                    .setAutoCancel(true);

            Notification notification = builder.build();
            startForeground(1, notification);

        } else {
            startService(intent);
        }
        return START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        client.removeLocationUpdates(callback);
    }

}
