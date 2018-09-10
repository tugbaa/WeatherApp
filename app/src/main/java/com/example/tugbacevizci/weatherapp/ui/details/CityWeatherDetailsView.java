package com.example.tugbacevizci.weatherapp.ui.details;

import com.example.tugbacevizci.weatherapp.data.remote.WeatherDayData;
import com.example.tugbacevizci.weatherapp.ui.base.BaseView;

import java.util.ArrayList;

public interface CityWeatherDetailsView extends BaseView {

    void setCityName(String cityName);

    void getWeatherByCityName(String cityName);

    void setWeather(String weather);

    void setWeatherDetail(double pressure, double humidity, double temp);

    void bindDayListSource(ArrayList<WeatherDayData> weatherDayData);

    void getDayWeather(int cityId);
}
