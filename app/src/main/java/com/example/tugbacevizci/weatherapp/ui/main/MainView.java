package com.example.tugbacevizci.weatherapp.ui.main;

import com.example.tugbacevizci.weatherapp.data.local.City;
import com.example.tugbacevizci.weatherapp.ui.base.BaseView;

import java.util.ArrayList;

public interface MainView extends BaseView {
    void startLocationService();

    void setCurrentCity();

    void getWeatherByCityName(String cityName);

    void setWeather(String weather);

    void bindFavCityListSource(ArrayList<City> response);
}
