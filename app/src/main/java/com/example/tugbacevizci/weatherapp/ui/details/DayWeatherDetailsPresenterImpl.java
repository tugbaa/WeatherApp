package com.example.tugbacevizci.weatherapp.ui.details;

import com.example.tugbacevizci.weatherapp.data.remote.WeatherDayData;
import com.example.tugbacevizci.weatherapp.network.responses.WeatherDayResponse;
import com.example.tugbacevizci.weatherapp.network.responses.WeatherResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DayWeatherDetailsPresenterImpl implements CityWeatherDetailsPresenter,
        CityWeatherDetailsInteractor.GetFavCityListener, CityWeatherDetailsInteractor.GetDayWeatherListener {

    private CityWeatherDetailsView view;
    private CityWeatherDetailsInteractor interactor;
    private int cityId;

    public DayWeatherDetailsPresenterImpl(CityWeatherDetailsView view, CityWeatherDetailsInteractor interactorr) {
        this.view = view;
        this.interactor = interactorr;
    }

    @Override
    public void getWeatherByCityName(String cityName) {
        interactor.getWeatherByCityName(cityName, this);
    }

    @Override
    public void getDayWeather(int cityId) {
        this.cityId = cityId;
        interactor.getDayWeather(cityId, this);
    }

    @Override
    public void onGetFavCityWeatherSuccess(WeatherResponse response) {
        view.setWeather(response.weatherData.get(0).main);
        view.setWeatherDetail(response.weatherDetailData.pressure,
                response.weatherDetailData.humidity,
                response.weatherDetailData.temp);
        view.getDayWeather(response.id);
    }

    @Override
    public void onGetFavCityWeatherFailed(Throwable e, String errorMessage) {
        if (view != null) {
            view.hideProgress();
            view.showErrorMessage(e.getMessage());
        }
    }

    private ArrayList<WeatherDayData> weatherDayData;
    private int j = 0;

    @Override
    public void onGetDayWeatherSuccess(WeatherDayResponse response) {

        weatherDayData = new ArrayList<>();

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();

        String displayDate;
        SimpleDateFormat newDateFormatter = new SimpleDateFormat("EEE dd-MM-yyyy",Locale.ENGLISH);

        for (int i = 0; i < response.cnt; i++) {
            try {
                date = dateFormatter.parse(response.list.get(i).dt_txt);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            displayDate = newDateFormatter.format(date);
            response.list.get(i).dt_txt = displayDate;

            if (weatherDayData.isEmpty()) {
                weatherDayData.add(response.list.get(i));
            } else if (!weatherDayData.get(j).dt_txt.contains(response.list.get(i).dt_txt)) {
                weatherDayData.add(response.list.get(i));
                j++;
            }

        }
        view.bindDayListSource(weatherDayData);
    }

    @Override
    public void onGetDayWeatherFailed(Throwable e, String errorMessage) {
        if (view != null) {
            view.hideProgress();
            view.showErrorMessage(e.getMessage());
        }
    }
}
