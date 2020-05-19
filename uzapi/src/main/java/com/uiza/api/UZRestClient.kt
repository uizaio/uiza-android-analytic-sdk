package com.uiza.api

import android.text.TextUtils
import com.uiza.api.interceptors.RestRequestInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import timber.log.Timber
import java.security.InvalidParameterException
import java.util.concurrent.TimeUnit

class UZRestClient private constructor() {

    companion object {
        private const val CONNECT_TIMEOUT_TIME = 20 //20s
        val instance: UZRestClient by lazy { HOLDER.INSTANCE }
    }

    private object HOLDER {
        val INSTANCE = UZRestClient()
    }

    private lateinit var retrofit: Retrofit

    fun init(apiBaseUrl: String) {
        if (TextUtils.isEmpty(apiBaseUrl)) {
            throw InvalidParameterException("apiBaseUrl cannot null or empty")
        }
        retrofit = Retrofit.Builder()
                .baseUrl(apiBaseUrl)
                .client(provideHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
    }

    private fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .readTimeout(CONNECT_TIMEOUT_TIME.toLong(), TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT_TIME.toLong(), TimeUnit.SECONDS)
                .addInterceptor(RestRequestInterceptor())
                .retryOnConnectionFailure(true)
                .addInterceptor(provideLogging()) // <-- this is the important line!
                .build()
    }

    private fun provideLogging(): Interceptor {
        val logging = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message: String? -> Timber.d(message) })
        // set your desired log level
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    fun createLiveApi(): UZLiveApi {
        return retrofit.create(UZLiveApi::class.java)
    }
}