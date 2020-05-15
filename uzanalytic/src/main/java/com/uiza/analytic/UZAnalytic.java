package com.uiza.analytic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.NonNull;

public final class UZAnalytic {
    private static String appId;
    private static String sdkVersionName; //UZData/AndroidSDK/1.1.0
    private static String deviceId;

    private UZAnalytic() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static void init(String deviceId, String appId, String sdkVersionName){
        UZAnalytic.appId = appId;
        UZAnalytic.sdkVersionName = String.format("UZData/AndroidSDK/%s", sdkVersionName);
        UZAnalytic.deviceId = deviceId;
    }

    @SuppressLint("HardwareIds")
    public static void init(@NonNull Context context, String baseUrl, String appId, String sdkVersionName) {
        init(Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID), appId, sdkVersionName);
        UZRestClient.getInstance().init(baseUrl);
    }

    public static String getAppId() {
        return appId;
    }

    public static String getDeviceId() {
        return deviceId;
    }

    public static String getSourceName() {
        return sdkVersionName;
    }
}
