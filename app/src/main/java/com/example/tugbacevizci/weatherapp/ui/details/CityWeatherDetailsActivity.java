package com.example.tugbacevizci.weatherapp.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.tugbacevizci.weatherapp.R;
import com.example.tugbacevizci.weatherapp.data.remote.WeatherDayData;
import com.example.tugbacevizci.weatherapp.ui.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;

public class CityWeatherDetailsActivity extends BaseActivity implements CityWeatherDetailsView, DayListAdapter.DayListener {


    @BindView(R.id.txt_city_name)
    TextView tvCityName;

    @BindView(R.id.txt_city_weather)
    TextView tvCityWeather;

    @BindView(R.id.txt_pressure)
    TextView tvPressure;

    @BindView(R.id.txt_humidity)
    TextView tvHumidity;

    @BindView(R.id.txt_temp)
    TextView tvTemp;

    @BindView(R.id.rv_days)
    RecyclerView rvDays;

    private CityWeatherDetailsPresenter presenter;
    private DayListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String value = intent.getStringExtra("city");

        presenter = new DayWeatherDetailsPresenterImpl(this, new CityWeatherDetailsInteractorImpl());

        setCityName(value);
        getWeatherByCityName(value);

        rvDays.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_city_weather_details;
    }

    @Override
    public void setCityName(String cityName) {
        tvCityName.setText(cityName);
    }

    @Override
    public void getWeatherByCityName(String cityName) {
        presenter.getWeatherByCityName(cityName);
    }

    @Override
    public void setWeather(String weather) {
        tvCityWeather.setText(weather);
    }

    @Override
    public void setWeatherDetail(double pressure, double humidity, double temp) {
        tvPressure.setText(String.format("pressure: %s", pressure));
        tvHumidity.setText(String.format("humidity: %s", humidity));
        tvTemp.setText(String.format("temp: %s", temp));
    }

    @Override
    public void bindDayListSource(ArrayList<WeatherDayData> weatherDayData) {
        if (weatherDayData.isEmpty()) {
            rvDays.setVisibility(View.GONE);
        } else {
            rvDays.setVisibility(View.VISIBLE);
            rvDays.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            adapter = new DayListAdapter(weatherDayData, this);
            rvDays.setAdapter(adapter);
        }
    }

    @Override
    public void getDayWeather(int cityId) {
        presenter.getDayWeather(cityId);
    }
}
