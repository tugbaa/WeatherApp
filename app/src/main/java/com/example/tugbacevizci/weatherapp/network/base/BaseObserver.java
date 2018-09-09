package com.example.tugbacevizci.weatherapp.network.base;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.adapter.rxjava2.HttpException;

public class BaseObserver<T extends BaseResponse> implements Observer<T> {

    String errorMessage;
    int errorCode;
    int networkError;

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        Log.e(getClass().getName(), "NEXT");
    }

    @Override
    public void onError(Throwable e) {
        if(e != null) {
            if(!TextUtils.isEmpty(e.getMessage())) {
                Log.e(getClass().getName(), e.getMessage());
            }
        }
        errorMessage = "Unexpected Network Error";

        if(e instanceof SocketTimeoutException){
            errorMessage = e.getMessage();
        } else if(e instanceof JsonSyntaxException){
            errorMessage = e.getMessage();
        } else {
            try {
                String response = ((HttpException) e).response().errorBody().string();
                networkError = ((HttpException) e).response().code();
                if (TextUtils.isEmpty(response) || TextUtils.equals(response, "")) {
                    errorMessage =((HttpException) e).response().message();  ; //BaseApplication.Instance().getString(R.string.com_facebook_image_download_unknown_error);
                } else {
                    JSONObject jObj = new JSONObject(response);
                    String errors = jObj.getString(jObj.keys().next());
                    try {
                        JSONObject object = new JSONObject(response);
                        errorMessage = object.getString("verbose");
                        errorCode = object.getInt("error_code");
                    } catch (JSONException exp){
                        JSONArray object = new JSONArray(errors);
                        errorMessage = object.get(0).toString();
                    }
                }
                Log.e(getClass().getName(), "onError : " + errorMessage);
            } catch (IOException e1) {
                e1.printStackTrace();
                errorMessage = ((HttpException) e).response().message();
            } catch (JSONException e1) {
                e1.printStackTrace();
                errorMessage = ((HttpException) e).response().message();
            } catch (Exception e1) {
                errorMessage = "Unexpected Network Error";
                e1.printStackTrace();
            }
        }
    }


    @Override
    public void onComplete() {
    }


}
