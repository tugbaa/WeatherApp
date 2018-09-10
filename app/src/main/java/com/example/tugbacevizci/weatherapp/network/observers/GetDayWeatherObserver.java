package com.example.tugbacevizci.weatherapp.network.observers;

import com.example.tugbacevizci.weatherapp.network.base.BaseObserver;
import com.example.tugbacevizci.weatherapp.network.responses.WeatherDayResponse;
import com.example.tugbacevizci.weatherapp.ui.details.CityWeatherDetailsInteractor;

public class GetDayWeatherObserver extends BaseObserver<WeatherDayResponse> {

    private CityWeatherDetailsInteractor.GetDayWeatherListener listener;

    public GetDayWeatherObserver(CityWeatherDetailsInteractor.GetDayWeatherListener listener) {
        this.listener = listener;
    }

    @Override
    public void onNext(WeatherDayResponse response) {
        super.onNext(response);
        listener.onGetDayWeatherSuccess(response);
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        listener.onGetDayWeatherFailed(e,e.getMessage());
    }
}
