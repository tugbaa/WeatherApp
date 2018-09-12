package com.example.tugbacevizci.weatherapp.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.tugbacevizci.weatherapp.WeatherApplication;
import com.example.tugbacevizci.weatherapp.data.local.City;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public final class SharedPrefsUtil {

    private SharedPreferences mPrefs;
    private static final String CITY="city";
    private static final String KEY_FAV_CITY="FavoriteCities";

    private static class SPHolder{
        private static final SharedPrefsUtil sInstance=new SharedPrefsUtil();
    }

    private SharedPrefsUtil(){
        mPrefs= WeatherApplication.Instance().getSharedPreferences(CITY, Context.MODE_PRIVATE);
    }

    public static SharedPrefsUtil getInstance(){
        return SPHolder.sInstance;
    }

    public SharedPrefsUtil putInt(String key,int value){
        mPrefs.edit().putInt(key,value).apply();
        return this;
    }

    public int getInt(String key, int defValue) {
        return mPrefs.getInt(key, defValue);
    }

    public SharedPrefsUtil putString(String key, String value) {
        mPrefs.edit().putString(key, value).apply();
        return this;
    }

    public String getString(String key, String defValue) {
        return mPrefs.getString(key, defValue);
    }

    public SharedPrefsUtil putBoolean(String key, boolean value) {
        mPrefs.edit().putBoolean(key, value).apply();
        return this;
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mPrefs.getBoolean(key, defValue);
    }

    public String getCity(){
        return mPrefs.getString(CITY,"Istanbul");
    }

    public void setCity(String name){
        mPrefs.edit().putString(CITY,name).apply();
    }

    public void addNewFavCity(String cityName,String weather){

        Set favCities = new HashSet();
        favCities = mPrefs.getStringSet(KEY_FAV_CITY, new HashSet<String>());
        Gson gson = new Gson();

        City city = new City(cityName, weather);
        String json = gson.toJson(city);

        for (Object cityJson : favCities) {
            City cityObj = gson.fromJson(cityJson.toString(), City.class);
        }

        favCities.add(json);

        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putStringSet(KEY_FAV_CITY, favCities);
        prefsEditor.commit();

    }

    public void getFavCities(ArrayList<City> cities){

        Gson gson = new Gson();

        Set favCities = new HashSet();
        favCities = mPrefs.getStringSet(KEY_FAV_CITY, new HashSet<String>());
        if (!favCities.isEmpty()) {
            cities.clear();

            for (Object cityJson : favCities) {
                SharedPrefsUtil.getInstance().getCity();

                City cityObj = gson.fromJson(cityJson.toString(), City.class);
                cities.add(new City(cityObj.cityName,  cityObj.weather));
            }
        }
    }

}
