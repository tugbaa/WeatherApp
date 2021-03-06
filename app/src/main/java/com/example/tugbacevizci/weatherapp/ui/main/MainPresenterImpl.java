package com.example.tugbacevizci.weatherapp.ui.main;

import com.example.tugbacevizci.weatherapp.network.responses.WeatherResponse;

public class MainPresenterImpl implements MainPresenter, MainInteractor.GetCityListener {

    private MainView view;
    private MainInteractor interactor;

    public MainPresenterImpl(MainView view, MainInteractor interactorr) {
        this.view = view;
        this.interactor = interactorr;
    }

    @Override
    public void setCurrentCity() {
        view.startLocationService();
    }

    @Override
    public void getWeatherByCityName(String cityName) {
        interactor.getWeatherByCityName(cityName,this);
    }

    @Override
    public void onGetWeatherByCityNameSuccess(WeatherResponse response) {
        view.setWeather(response.weatherData.get(0).main);
    }

    @Override
    public void onGetWeatherByCityNameFailed(Throwable e, String errorMessage) {
        if (view != null) {
            view.hideProgress();
            view.showErrorMessage(e.getMessage());
        }
    }
}
