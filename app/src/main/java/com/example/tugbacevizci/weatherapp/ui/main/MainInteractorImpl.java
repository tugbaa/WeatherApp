package com.example.tugbacevizci.weatherapp.ui.main;

import com.example.tugbacevizci.weatherapp.data.remote.ApiConsts;
import com.example.tugbacevizci.weatherapp.network.base.NetworkService;
import com.example.tugbacevizci.weatherapp.network.observers.GetWeatherObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainInteractorImpl implements MainInteractor{

    @Override
    public void getWeatherByCityName(String cityName, GetCityListener listener) {
        NetworkService.Service().getWeatherByCityName(cityName, ApiConsts.OPEN_WEATHER_API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new GetWeatherObserver(listener));

    }
}
