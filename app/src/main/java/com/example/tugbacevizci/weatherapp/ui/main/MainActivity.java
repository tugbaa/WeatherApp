package com.example.tugbacevizci.weatherapp.ui.main;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainView, CityListAdapter.CityListener {

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

        presenter = new MainPresenterImpl(this, new MainInteractorImpl());

        rvFavCities.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        setCurrentCity();

        cities = new ArrayList<>();
        String dummyCity = "Kayseri";
        String dummyWeather = "Clouds";
        cities.add(new City(dummyCity,dummyWeather));

        String dummyCity2 = "Konya";
        String dummyWeather2 = "Sunny";
        cities.add(new City(dummyCity2,dummyWeather2));

        String dummyCity3 = "Trabzon";
        String dummyWeather3 = "Rainy";
        cities.add(new City(dummyCity3, dummyWeather3));

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
        if (cities.isEmpty()) {
            rvFavCities.setVisibility(View.GONE);
        } else {
            rvFavCities.setVisibility(View.VISIBLE);
            rvFavCities.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            adapter = new CityListAdapter(cities, this);
            rvFavCities.setAdapter(adapter);

        }

    }
    @Override
    protected void onStop() {
        super.onStop();
    }

    private String getLocationName(double latitude, double longitude) {

        String cityName = "Not Found";
        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
        try {

            List<Address> addresses = gcd.getFromLocation(latitude, longitude,
                    1);

            for (Address adrs : addresses) {
                if (adrs != null) {
                    String city = adrs.getAdminArea();
                    if (city != null && !city.equals("")) {
                        cityName = city;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cityName;
    }

    @Override
    public void onCityClick(City clickedCity) {
        Intent intent = new Intent(this,CityWeatherDetailsActivity.class);
        intent.putExtra("city",clickedCity.cityName);
        startActivity(intent);

    }
}
