package io.uiza.apisdk;

import android.app.Application;

import com.uiza.api.UZApi;

import timber.log.Timber;

public class UZApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        UZApi.init(getApplicationContext(), "SampleAPI", BuildConfig.VERSION_NAME);
    }
}
