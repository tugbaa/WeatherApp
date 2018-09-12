package com.example.tugbacevizci.weatherapp.network.base;

import com.example.tugbacevizci.weatherapp.network.responses.WeatherDayResponse;
import com.example.tugbacevizci.weatherapp.network.responses.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherApi {

    @GET("weather/")
    Observable<WeatherResponse> getWeatherByCityName(@Query("q") String cityName,
                                                     @Query("appid") String appId);
    @GET("forecast/")
    Observable<WeatherDayResponse> getDayWeather(@Query("id") int cityId,
                                                 @Query("appid") String appId);
}
