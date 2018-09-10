package com.example.tugbacevizci.weatherapp.data.remote;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherDayData {

    @SerializedName("dt_txt")
    public String dt_txt;

    @SerializedName("weather")
    public ArrayList<WeatherData> weather;

}
