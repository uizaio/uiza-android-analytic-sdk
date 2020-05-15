package io.uiza.analytic;

import android.app.Application;

import com.uiza.analytic.UZAnalytic;

public class UZApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UZAnalytic.init(getApplicationContext(), "https://tracking-dev.uizadev.io", "29a8f302075c4992af80467b195ff427", BuildConfig.VERSION_NAME);
    }
}
