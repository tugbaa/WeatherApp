package com.example.tugbacevizci.weatherapp.data;

import com.example.tugbacevizci.weatherapp.network.responses.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherApi {

    @GET("weather")
    Observable<WeatherResponse> getWeatherByCityName(@Query("q") String cityName, @Query("appid") String appId);
}
