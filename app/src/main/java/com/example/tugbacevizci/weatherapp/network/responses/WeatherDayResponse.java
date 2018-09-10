package com.example.tugbacevizci.weatherapp.network.responses;

import com.example.tugbacevizci.weatherapp.data.remote.WeatherDayData;
import com.example.tugbacevizci.weatherapp.network.base.BaseResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherDayResponse extends BaseResponse {

    @SerializedName("message")
    public double message;

    @SerializedName("cod")
    public String cod;

    @SerializedName("cnt")
    public int cnt;

    @SerializedName("list")
    public ArrayList<WeatherDayData> list;

}
