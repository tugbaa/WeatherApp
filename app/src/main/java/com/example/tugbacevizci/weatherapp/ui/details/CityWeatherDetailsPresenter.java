package com.example.tugbacevizci.weatherapp.ui.details;

public interface CityWeatherDetailsPresenter {

    void getWeatherByCityName(String cityName);

    void getDayWeather(int cityId);
}
