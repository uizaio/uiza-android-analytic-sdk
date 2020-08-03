package io.uiza.apisdk;

import android.app.Application;

import com.uiza.api.UZApi;
import com.uiza.api.UZEnvironment;

import timber.log.Timber;

public class UZApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        UZApi.init(getApplicationContext(), BuildConfig.VERSION_NAME, UZEnvironment.PRODUCTION);
    }
}
