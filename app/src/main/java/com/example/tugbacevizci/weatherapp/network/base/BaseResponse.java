package com.example.tugbacevizci.weatherapp.network.base;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseResponse implements Parcelable {

    public BaseResponse(){

    }

    protected BaseResponse(Parcel in) {
    }

    public static final Creator<BaseResponse> CREATOR = new Creator<BaseResponse>() {
        @Override
        public BaseResponse createFromParcel(Parcel in) {
            return new BaseResponse(in);
        }

        @Override
        public BaseResponse[] newArray(int size) {
            return new BaseResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
