package com.uiza.api.exts

import android.util.Base64
import com.uiza.api.models.UZLiveInfo
import timber.log.Timber

fun String.toLiveInfo(): UZLiveInfo? {
    val json = this.parserInfo()
    json?.let {
        try {
            return@toLiveInfo it.fromJson(UZLiveInfo::class.java)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
    return null
}

@Throws(java.lang.Exception::class)
fun String.parserInfo(): String? {
    val fromIndex = this.indexOf("?cm=")
    if (fromIndex > 0) {
        val toIndex = this.indexOf("&", fromIndex)
        val cm = if (toIndex > 0) this.substring(fromIndex + 4, toIndex) else this.substring(fromIndex + 4)
        return String(Base64.decode(cm, Base64.DEFAULT))
    }
    return null
}