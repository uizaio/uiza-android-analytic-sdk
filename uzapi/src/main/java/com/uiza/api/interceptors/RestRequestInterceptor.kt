package com.uiza.api.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RestRequestInterceptor : Interceptor {
    companion object {
        private const val CONTENT_TYPE = "application/json"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("Content-Type", CONTENT_TYPE)
        return chain.proceed(builder.build())
    }
}