package com.example.tugbacevizci.weatherapp.network.responses;

import com.example.tugbacevizci.weatherapp.data.remote.WeatherData;
import com.example.tugbacevizci.weatherapp.network.base.BaseResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherResponse extends BaseResponse {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("cod")
    public int cod;

    @SerializedName("weather")
    public ArrayList<WeatherData> weatherData;

}
