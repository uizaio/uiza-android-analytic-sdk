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
        UZApi.init(getApplicationContext(), "b963b465c34e4ffb9a71922442ee0dca", BuildConfig.VERSION_NAME);
    }
}
