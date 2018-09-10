package com.example.tugbacevizci.weatherapp.network.observers;

import com.example.tugbacevizci.weatherapp.network.base.BaseObserver;
import com.example.tugbacevizci.weatherapp.network.responses.WeatherResponse;
import com.example.tugbacevizci.weatherapp.ui.details.CityWeatherDetailsInteractor;

public class GetFavCityWeatherObserver extends BaseObserver<WeatherResponse> {

    private CityWeatherDetailsInteractor.GetFavCityListener listener;

    public GetFavCityWeatherObserver(CityWeatherDetailsInteractor.GetFavCityListener listener) {
        this.listener = listener;
    }

    @Override
    public void onNext(WeatherResponse response) {
        super.onNext(response);
        listener.onGetFavCityWeatherSuccess(response);
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        listener.onGetFavCityWeatherFailed(e,e.getMessage());
    }
}
