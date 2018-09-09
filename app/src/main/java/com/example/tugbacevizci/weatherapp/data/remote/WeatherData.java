package com.example.tugbacevizci.weatherapp.data.remote;

import com.google.gson.annotations.SerializedName;

public class WeatherData {

    @SerializedName("id")
    public int id;

    @SerializedName("main")
    public String main;

    @SerializedName("description")
    public String description;

    @SerializedName("icon")
    public String icon;
}
