package com.example.tugbacevizci.weatherapp.ui.main;

import com.example.tugbacevizci.weatherapp.data.local.City;

import java.util.ArrayList;

public interface MainPresenter {
    void setCurrentCity();

    void getWeatherByCityName(String cityName);

    void getFavCities(ArrayList<City> favCities);
}
