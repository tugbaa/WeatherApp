package com.example.tugbacevizci.weatherapp.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.tugbacevizci.weatherapp.WeatherApplication;

public final class SharedPrefsUtil {

    private SharedPreferences mPrefs;
    private static final String CITY="city";

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
}
