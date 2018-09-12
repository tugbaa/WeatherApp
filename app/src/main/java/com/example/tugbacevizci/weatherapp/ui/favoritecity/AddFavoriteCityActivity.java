package com.example.tugbacevizci.weatherapp.ui.favoritecity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.example.tugbacevizci.weatherapp.R;
import com.example.tugbacevizci.weatherapp.intentservices.LocationService;
import com.example.tugbacevizci.weatherapp.ui.base.BaseActivity;
import com.example.tugbacevizci.weatherapp.ui.main.MainActivity;
import com.example.tugbacevizci.weatherapp.ui.main.MainInteractorImpl;
import com.example.tugbacevizci.weatherapp.util.SharedPrefsUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.OnClick;

public class AddFavoriteCityActivity extends BaseActivity implements AddFavoriteCityView,
        OnMapReadyCallback, OnMarkerDragListener {

    private static final float DEFAULT_ZOOM = 3;
    private static final int MAX_RESULT = 1;

    private GoogleMap googleMap;
    private AddFavoriteCityPresenter presenter;
    private String city;
    private String weather;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);

        presenter = new AddFavoriteCityPresenterImpl(this, new MainInteractorImpl());

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_favorite_city;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        this.googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);

        googleMap.getUiSettings().setMyLocationButtonEnabled(true);

        googleMap.setOnMarkerDragListener(this);

        zoomToCurrentLocation();

    }

    @Override
    public void zoomToCurrentLocation() {
        LatLng currenLocation = new LatLng(LocationService.currentLat, LocationService.currentLng);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(LocationService.currentLat, LocationService.currentLng), DEFAULT_ZOOM));
        googleMap.addMarker(new MarkerOptions().position(currenLocation).draggable(true));
    }

    @Override
    public void setWeather(String main) {
        this.weather = main;
    }

    @Override
    public void onMarkerDragStart(Marker marker) { }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        setAddress(marker.getPosition().latitude, marker.getPosition().longitude);
    }

    private void setAddress(double latitude, double longitude) {

        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, MAX_RESULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String city;
        if (addresses != null) {
            city = addresses.get(0).getAdminArea();
            this.city = city;
        }

    }

    @OnClick(R.id.btn_save)
    public void saveButtonClicked() {

        SharedPrefsUtil.getInstance().addNewFavCity(this.city,this.weather);

        presenter.getWeatherByCityName(this.city);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.btn_cancel)
    public void cancelButtonClicked() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
