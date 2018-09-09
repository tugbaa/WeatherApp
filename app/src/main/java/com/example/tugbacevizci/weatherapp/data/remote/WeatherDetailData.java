package com.example.tugbacevizci.weatherapp.data.remote;

import com.google.gson.annotations.SerializedName;

class WeatherDetailData {

    @SerializedName("temp")
    public int temp;

    @SerializedName("pressure")
    public int pressure;

    @SerializedName("humidity")
    public int humidity;

    @SerializedName("temp_min")
    public int temp_min;

    @SerializedName("temp_max")
    public int temp_max;
}
