package com.example.tugbacevizci.weatherapp.network;

import com.example.tugbacevizci.weatherapp.data.OpenWeatherApi;
import com.example.tugbacevizci.weatherapp.data.remote.ApiConsts;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static OpenWeatherApi service;

    private NetworkService() {}

    public static OpenWeatherApi Service(){

        if(service == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConsts.BASE_URL)
                    .client(httpClient.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(OpenWeatherApi.class);
        }

        return service;
    }
}