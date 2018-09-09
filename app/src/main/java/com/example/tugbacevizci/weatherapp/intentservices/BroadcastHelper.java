package com.example.tugbacevizci.weatherapp.intentservices;

import android.content.Intent;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class BroadcastHelper {

    public static final String BROADCAST_ACTION = "broadcast_action";
    public static final String KEY_REASON = "broadcast_reason";
    public static final String PERMISSION = "permission";
    public static final String KEY_PERMISSION_REQUIRED = "permission-required";
    public static final String LOCATION_UPDATE = "location-update";

    @StringDef({LOCATION_UPDATE, PERMISSION})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Reason {
    }
    public static Intent broadcastIntent(@Reason String reason){
        final Intent intent = new Intent();
        intent.setAction(BROADCAST_ACTION);
        intent.putExtra(KEY_REASON , reason);
        return intent;
    }
}
