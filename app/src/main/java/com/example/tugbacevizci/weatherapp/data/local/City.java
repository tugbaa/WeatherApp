package com.example.tugbacevizci.weatherapp.data.local;

import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("cityName")
    public String cityName;

    @SerializedName("weather")
    public String weather;

    public City(String cityName, String weather) {
        this.cityName = cityName;
        this.weather = weather;
    }
}
