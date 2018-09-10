package com.example.tugbacevizci.weatherapp.ui.details;

import com.example.tugbacevizci.weatherapp.network.responses.WeatherDayResponse;
import com.example.tugbacevizci.weatherapp.network.responses.WeatherResponse;

public interface CityWeatherDetailsInteractor {

    void getWeatherByCityName(String cityName, GetFavCityListener listener);

    interface GetFavCityListener {

        void onGetFavCityWeatherSuccess(WeatherResponse response);

        void onGetFavCityWeatherFailed(Throwable e, String errorMessage);
    }

    void getDayWeather(int cityId, GetDayWeatherListener listener);


    interface GetDayWeatherListener {

        void onGetDayWeatherSuccess(WeatherDayResponse response);

        void onGetDayWeatherFailed(Throwable e, String errorMessage);
    }

}
