package com.uiza.api.exts

import android.util.Base64
import com.uiza.api.models.UZLiveInfo
import timber.log.Timber

fun String.toLiveInfo(): UZLiveInfo? {
    val index = this.indexOf("?cm=")
    if (index > 0) {
        try {
            val json = String(Base64.decode(this.substring(index + 4), Base64.DEFAULT))
            return json.fromJson(UZLiveInfo::class.java)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
    return null
}