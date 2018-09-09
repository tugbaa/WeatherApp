package com.example.tugbacevizci.weatherapp.ui.main;

import com.example.tugbacevizci.weatherapp.network.responses.WeatherResponse;

public interface MainInteractor {

    void getWeatherByCityName(String cityName,GetCityListener listener);

    interface GetCityListener {

        void onGetWeatherByCitySuccess(WeatherResponse response);

        void onGetWeatherByFailed(Throwable e, String errorMessage);
    }
}
