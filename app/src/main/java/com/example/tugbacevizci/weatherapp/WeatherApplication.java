package com.example.tugbacevizci.weatherapp;

import android.app.Application;

public class WeatherApplication extends Application {
    private static WeatherApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static WeatherApplication Instance() {
        return application;
    }
}
