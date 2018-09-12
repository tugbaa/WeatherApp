package com.example.tugbacevizci.weatherapp.ui.favoritecity;

import com.example.tugbacevizci.weatherapp.ui.base.BaseView;

public interface AddFavoriteCityView extends BaseView{

    void zoomToCurrentLocation();

    void setWeather(String main);
}
