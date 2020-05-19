package com.uiza.api;

import io.reactivex.Observable;

import com.uiza.api.models.UZLiveCounter;

import retrofit2.http.Field;
import retrofit2.http.GET;

public interface UZLiveApi {
    // Get live viewers count
    @GET("/v1/analytics/live_viewers")
    Observable<UZLiveCounter> getLiveViewers(@Field("app_id") String appId,
                                             @Field("entity_id") String entityId);

}
