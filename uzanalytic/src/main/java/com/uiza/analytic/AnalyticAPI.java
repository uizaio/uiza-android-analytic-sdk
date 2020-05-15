package com.uiza.analytic;

import io.reactivex.Observable;

import com.uiza.analytic.models.UZTrackingBody;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AnalyticAPI {
    @GET("/v1/analytics/{app_id}/usage/daily")
    Observable<String> usageDaily(@Path("app_id") String appId,
                                  @Field("start_date") String startDate,
                                  @Field("end_date") String endDate,
                                  @Field("page_size") int pageSize,
                                  @Field("page_number") int pageNumber);

    @GET("/v1/analytics/usage/summary")
    Observable<String> usageSummary(@Header("Authorization") String authorization,
                                    @Header("X-Customer-Custom-ID") String appId);

    //Uiza Tracking API
    @POST("/v1/events")
    Observable<ResponseBody> pushEvents(@Body UZTrackingBody trackingBody);

    // Get live viewers count
    @GET("/v1/analytics/live_viewers")
    Observable<String> getLiveViewers(@Field("app_id") String appId,
                                      @Field("entity_id") String entityId);

}
