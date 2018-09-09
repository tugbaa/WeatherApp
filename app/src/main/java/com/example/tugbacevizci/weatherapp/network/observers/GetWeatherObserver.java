package com.example.tugbacevizci.weatherapp.network.observers;

import com.example.tugbacevizci.weatherapp.network.responses.WeatherResponse;
import com.example.tugbacevizci.weatherapp.network.base.BaseObserver;
import com.example.tugbacevizci.weatherapp.ui.main.MainInteractor;

public class GetWeatherObserver  extends BaseObserver<WeatherResponse> {

    private MainInteractor.GetCityListener listener;

    public GetWeatherObserver(MainInteractor.GetCityListener listener) {
        this.listener = listener;
    }

    @Override
    public void onNext(WeatherResponse response) {
        super.onNext(response);
        listener.onGetWeatherByCitySuccess(response);
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        listener.onGetWeatherByFailed(e,e.getMessage());
    }
}
