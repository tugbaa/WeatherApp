package com.example.tugbacevizci.weatherapp.ui.favoritecity;

import com.example.tugbacevizci.weatherapp.network.responses.WeatherResponse;
import com.example.tugbacevizci.weatherapp.ui.main.MainInteractor;

public class AddFavoriteCityPresenterImpl implements AddFavoriteCityPresenter,
        MainInteractor.GetCityListener {

    private AddFavoriteCityView view;
    private MainInteractor interactor;

    public AddFavoriteCityPresenterImpl(AddFavoriteCityView view,MainInteractor interactor){
        this.view = view;
        this.interactor = interactor;

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
