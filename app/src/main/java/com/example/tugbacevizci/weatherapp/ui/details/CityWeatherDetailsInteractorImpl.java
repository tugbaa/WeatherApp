package com.example.tugbacevizci.weatherapp.ui.details;

import com.example.tugbacevizci.weatherapp.network.base.ApiConsts;
import com.example.tugbacevizci.weatherapp.network.base.NetworkService;
import com.example.tugbacevizci.weatherapp.network.observers.GetDayWeatherObserver;
import com.example.tugbacevizci.weatherapp.network.observers.GetFavCityWeatherObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CityWeatherDetailsInteractorImpl implements CityWeatherDetailsInteractor{

    @Override
    public void getWeatherByCityName(String cityName, GetFavCityListener listener) {
        NetworkService.Service().getWeatherByCityName(cityName, ApiConsts.OPEN_WEATHER_API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new GetFavCityWeatherObserver(listener));
    }

    @Override
    public void getDayWeather(int cityId, GetDayWeatherListener listener) {
        NetworkService.Service().getDayWeather(cityId, ApiConsts.OPEN_WEATHER_API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new GetDayWeatherObserver(listener));
    }
}
