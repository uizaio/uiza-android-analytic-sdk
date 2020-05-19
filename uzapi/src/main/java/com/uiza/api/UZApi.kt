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

/**
 * UZApi
 */
object UZApi {
    private var sourceName //UZData/AndroidSDK/1.1.0
            : String? = null
    private var deviceId: String? = null

    @SuppressLint("HardwareIds")
    @JvmStatic
    fun init(context: Context, sdkName: String, sdkVersionName: String) {
        deviceId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        sourceName = String.format("UZData/%s/%s", sdkName, sdkVersionName)
        val baseLiveUrl: String? = context.getMetaData("uz_live_views_url") // https://development-api.uizadev.io
        if (URLUtil.isValidUrl(baseLiveUrl)) {
            UZRestClient.instance.init(baseLiveUrl!!)
        } else {
            throw IllegalArgumentException("base live url is invalid")
        }
    }

    @JvmStatic
    @JvmOverloads
    fun getLiveViewers(appId: String, entityId: String, onNext: Consumer<UZLiveCounter>,
                       onError: Consumer<Throwable> = Functions.ON_ERROR_MISSING, onComplete: Action = Functions.EMPTY_ACTION): Disposable {
        return UZRestClient.instance.createLiveApi().getLiveViewers(appId, entityId).bind(onNext, onError, onComplete)
    }

}
