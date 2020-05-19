@file:JvmName("UZUtils")

package com.uiza.api.utils

import android.content.Context
import android.content.pm.PackageManager

fun Context.getMetaData(key: String): String? {
    return try {
        val info = this.packageManager.getApplicationInfo(this.packageName, PackageManager.GET_META_DATA)
        info.metaData.getString(key)
    } catch (e: PackageManager.NameNotFoundException) {
        null
    }
}