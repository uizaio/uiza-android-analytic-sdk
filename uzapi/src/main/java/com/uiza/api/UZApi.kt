package com.uiza.api

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.uiza.api.exts.bind
import com.uiza.api.exts.toLiveInfo
import com.uiza.api.models.UZLiveCounter
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


    @JvmStatic
    fun init(context: Context, sdkVersionName: String) {
        init(context, sdkVersionName, UZEnvironment.DEVELOPMENT)
    }

    @SuppressLint("HardwareIds")
    @JvmStatic
    fun init(context: Context, sdkVersionName: String, environment: UZEnvironment = UZEnvironment.DEVELOPMENT) {
        deviceId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        sourceName = String.format("UZData/AndroidSDK/%s", sdkVersionName)
        UZRestClient.instance.init(environment.getHost())
    }

    @JvmStatic
    @JvmOverloads
    fun getLiveViewers(linkPlay: String, onNext: Consumer<UZLiveCounter>,
                       onError: Consumer<Throwable> = Functions.ON_ERROR_MISSING,
                       onComplete: Action = Functions.EMPTY_ACTION): Disposable? {
        linkPlay.toLiveInfo()?.let {
            return@getLiveViewers UZRestClient.instance.createApiService()
                    .getLiveViewers(it.appId, it.entityId).bind(onNext, onError, onComplete)
        }
        return null
    }

    @JvmStatic
    @JvmOverloads
    fun getLiveViewers(appId: String, entityId: String, onNext: Consumer<UZLiveCounter>,
                       onError: Consumer<Throwable> = Functions.ON_ERROR_MISSING,
                       onComplete: Action = Functions.EMPTY_ACTION): Disposable {
        return UZRestClient.instance.createApiService()
                .getLiveViewers(appId, entityId).bind(onNext, onError, onComplete)
    }


}
