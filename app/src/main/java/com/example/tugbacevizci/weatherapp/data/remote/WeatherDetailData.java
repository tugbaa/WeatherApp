package com.example.tugbacevizci.weatherapp.data.remote;

import com.google.gson.annotations.SerializedName;

public class WeatherDetailData {

    @SerializedName("temp")
    public double temp;

    @SerializedName("pressure")
    public double pressure;

    @SerializedName("humidity")
    public double humidity;

    @SerializedName("temp_min")
    public double temp_min;

    @SerializedName("temp_max")
    public double temp_max;
}
