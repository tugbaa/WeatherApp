package com.example.tugbacevizci.weatherapp.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public final class LocationUtils {

    public static String convertLocationToCity(Context context,double latitude,double longitude){
        String cityName = null;
        Geocoder gcd = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = gcd.getFromLocation(latitude, longitude,
                    1);

            for (Address adrs : addresses) {
                if (adrs != null) {
                    String city = adrs.getAdminArea();
                    if (city != null && !city.equals("")) {
                        cityName = city;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cityName;
    }
}
