package com.uiza.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;
import android.webkit.URLUtil;

import androidx.annotation.NonNull;

import com.uiza.api.models.UZLiveCounter;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public final class UZApi {
    private static String appId;
    private static String sdkVersionName; //UZData/AndroidSDK/1.1.0
    private static String deviceId;

    private UZApi() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static void init(String deviceId, String appId, String sdkVersionName) {
        UZApi.appId = appId;
        UZApi.sdkVersionName = String.format("UZData/AndroidSDK/%s", sdkVersionName);
        UZApi.deviceId = deviceId;
    }

    @SuppressLint("HardwareIds")
    public static void init(@NonNull Context context, String appId, String sdkVersionName) {
        init(Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID), appId, sdkVersionName);
        String baseLiveUrl = Utils.getMetaData(context, "uz_live_views_url");   // https://development-api.uizadev.io
        if (URLUtil.isValidUrl(baseLiveUrl)) {
            UZRestClient.getInstance().init(baseLiveUrl);
        } else {
            throw new IllegalArgumentException("base live url is invalid");
        }
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


    public static Disposable getLiveViewers(String entityId, Consumer<UZLiveCounter> onNext,
                                            Consumer<Throwable> onError) throws IllegalStateException {
        return RxBinder.bind(UZRestClient.getInstance().createLiveApi().getLiveViewers(appId, entityId), onNext, onError);
    }
}
