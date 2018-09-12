package com.example.tugbacevizci.weatherapp.ui.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.tugbacevizci.weatherapp.R;
import com.example.tugbacevizci.weatherapp.data.local.City;
import com.example.tugbacevizci.weatherapp.intentservices.LocationService;
import com.example.tugbacevizci.weatherapp.ui.base.BaseActivity;
import com.example.tugbacevizci.weatherapp.ui.details.CityWeatherDetailsActivity;
import com.example.tugbacevizci.weatherapp.ui.favoritecity.AddFavoriteCityActivity;
import com.example.tugbacevizci.weatherapp.util.LocationUtils;
import com.example.tugbacevizci.weatherapp.util.SharedPrefsUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainView, CityListAdapter.CityListener {

    private final String CITY_KEY = "city";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.txt_current_location)
    TextView tvCurrentLocation;

    @BindView(R.id.txt_weather)
    TextView tvWeather;

    @BindView(R.id.rv_fav_cities)
    RecyclerView rvFavCities;

    private MainPresenter presenter;
    private CityListAdapter adapter;
    private ArrayList<City> cities;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            SharedPrefsUtil.getInstance().setCity(bundle.getString(CITY_KEY));
        }

        presenter = new MainPresenterImpl(this, new MainInteractorImpl());

        rvFavCities.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        setCurrentCity();

        cities = new ArrayList<>();

        bindFavCityListSource(cities);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void startLocationService() {
        final Intent intent = new Intent(this, LocationService.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }
    }

    @Override
    public void setCurrentCity() {
        presenter.setCurrentCity();

        String cityName = getLocationName(LocationService.currentLat,LocationService.currentLng);
        tvCurrentLocation.setText(cityName);
        getWeatherByCityName(cityName);

    }

    @Override
    public void getWeatherByCityName(String cityName) {
        presenter.getWeatherByCityName(cityName);
    }

    @Override
    public void setWeather(String weather) {
        tvWeather.setText(weather);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void bindFavCityListSource(ArrayList<City> cities) {

        SharedPrefsUtil.getInstance().getFavCities(cities);

        if (cities.isEmpty()) {
            rvFavCities.setVisibility(View.GONE);
        } else {
            rvFavCities.setVisibility(View.VISIBLE);
            rvFavCities.setLayoutManager(new LinearLayoutManager(
                    this, LinearLayoutManager.VERTICAL, false));
            adapter = new CityListAdapter(cities, this);
            rvFavCities.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }

    }
    @Override
    protected void onStop() {
        super.onStop();
    }

    private String getLocationName(double latitude, double longitude) {
        return LocationUtils.convertLocationToCity(getApplicationContext(),latitude,longitude);
    }

    @Override
    public void onCityClick(City clickedCity) {
        Intent intent = new Intent(this, CityWeatherDetailsActivity.class);
        intent.putExtra(CITY_KEY, clickedCity.cityName);
        startActivity(intent);

    }

    @OnClick(R.id.btn_add_city)
    public void onAddCityButtonClicked() {
        Intent intent = new Intent(this, AddFavoriteCityActivity.class);
        startActivity(intent);
    }

}
