package com.uiza.api

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.webkit.URLUtil
import com.uiza.api.models.UZLiveCounter
import com.uiza.api.utils.bind
import com.uiza.api.utils.getMetaData
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.internal.functions.Functions

object UZApi {
    private var appId: String? = null
    private var sourceName //UZData/AndroidSDK/1.1.0
            : String? = null
    private var deviceId: String? = null

    private fun init(deviceId: String, appId: String, sdkVersionName: String) {
        UZApi.appId = appId
        sourceName = String.format("UZData/AndroidSDK/%s", sdkVersionName)
        UZApi.deviceId = deviceId
    }

    @SuppressLint("HardwareIds")
    @JvmStatic
    fun init(context: Context, appId: String, sdkVersionName: String) {
        init(Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID), appId, sdkVersionName)
        val baseLiveUrl: String? = context.getMetaData("uz_live_views_url") // https://development-api.uizadev.io
        if (URLUtil.isValidUrl(baseLiveUrl)) {
            UZRestClient.instance.init(baseLiveUrl!!)
        } else {
            throw IllegalArgumentException("base live url is invalid")
        }
    }

    @Throws(IllegalStateException::class)
    @JvmStatic
    @JvmOverloads
    fun getLiveViewers(entityId: String, onNext: Consumer<UZLiveCounter>,
                       onError: Consumer<Throwable> = Functions.ON_ERROR_MISSING, onComplete: Action = Functions.EMPTY_ACTION): Disposable {
        checkNotNull(appId) { "Must call init() before using" }
        return UZRestClient.instance.createLiveApi().getLiveViewers(appId!!, entityId).bind(onNext, onError, onComplete)
    }

}
