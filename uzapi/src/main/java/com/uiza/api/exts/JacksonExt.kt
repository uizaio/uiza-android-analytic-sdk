@file:JvmName("JacksonUtils")

package com.uiza.api.exts

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException
import java.io.Reader

private val mapper = ObjectMapper()

/**
 * @param <T> the type of the desired object
 * @param src the Object for which JSON representation is to be created
 * @return Json representation of `list`
</T> */
fun <T> toJson(src: T): String {
    return try {
        mapper.writeValueAsString(src)
    } catch (e: JsonProcessingException) {
        ""
    }
}

/**
 * [ObjectMapper.readValue]
 *
 * @param <T>      the type of the desired object
 * @param classOfT the class of T
 * @return an object of type T from the string. Returns `null` if
 * `json` is `null`.
</T> */
fun <T> String.fromJson(classOfT: Class<T>): T? {
    return try {
        mapper.readValue(this, classOfT)
    } catch (e: JsonProcessingException) {
        null
    }
}

/**
 * [ObjectMapper.readValue]
 *
 * @param <T>      the type of the desired object
 * @param classOfT the class of T
 * @return an object of type T from the string. Returns `null` if
 * `json` is `null`.
</T> */
fun <T> Reader.fromJson(classOfT: Class<T>): T? {
    return try {
        mapper.readValue(this, classOfT)
    } catch (e: IOException) {
        null
    }
}

/**
 * [ObjectMapper.readValue]
 *
 * @param <T>  the type of the desired object
 * @return an List of type T from the string. Returns `Collections#emptyList` if
 * `json` is `null`.
</T> */
fun <T> String.toList(): List<T>? {
    return try {
        mapper.readValue(this, object : TypeReference<List<T>?>() {})
    } catch (e: JsonProcessingException) {
        emptyList()
    }
}

/**
 * [ObjectMapper.readValue]
 *
 * @param <T>    the type of the desired object
 * @return an List of type T from the string. Returns `Collections#emptyList` if
 * `json` is `null`.
</T> */
fun <T> Reader.toList(): List<T>? {
    return try {
        mapper.readValue(this, object : TypeReference<List<T>?>() {})
    } catch (e: IOException) {
        emptyList()
    }
}