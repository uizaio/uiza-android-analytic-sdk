package com.uiza.analytic;

import android.text.TextUtils;

import com.uiza.analytic.interceptors.RestRequestInterceptor;

import java.security.InvalidParameterException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import timber.log.Timber;

public class UZRestClient {
    private Interceptor restRequestInterceptor;
    private static final int CONNECT_TIMEOUT_TIME = 20;//20s
    private Retrofit retrofit;

    private static class UizaRestClientHelper {
        private static final UZRestClient INSTANCE = new UZRestClient();
    }

    public static UZRestClient getInstance() {
        return UizaRestClientHelper.INSTANCE;
    }

    private UZRestClient() {

    }

    void init(String apiBaseUrl) {
        if (TextUtils.isEmpty(apiBaseUrl)) {
            throw new InvalidParameterException("apiBaseUrl cannot null or empty");
        }
        retrofit = new Retrofit.Builder()
                .baseUrl(apiBaseUrl)
                .client(provideHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    private OkHttpClient provideHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(CONNECT_TIMEOUT_TIME, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT_TIME, TimeUnit.SECONDS)
                .addInterceptor(provideInterceptor())
                .retryOnConnectionFailure(true)
                .addInterceptor(provideLogging())  // <-- this is the important line!
                .build();
    }

    private Interceptor provideLogging() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(Timber::d);
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    private Interceptor provideInterceptor() {
        if (restRequestInterceptor == null)
            restRequestInterceptor = new RestRequestInterceptor();
        return restRequestInterceptor;
    }

    public AnalyticAPI createAnalyticAPI() {
        if (retrofit == null) {
            throw new IllegalStateException("Must call init() before using");
        }
        return retrofit.create(AnalyticAPI.class);
    }
}
