package com.uiza.api

import io.reactivex.Observable
import com.uiza.api.models.UZLiveCounter
import retrofit2.http.GET
import retrofit2.http.Query

interface UZLiveApi {
    // Get live viewers count
    @GET("/v1/analytics/live_viewers")
    fun getLiveViewers(@Query("app_id") appId: String,
                       @Query("entity_id") entityId: String): Observable<UZLiveCounter>
}