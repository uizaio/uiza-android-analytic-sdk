package com.uiza.analytic.models;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.uiza.analytic.BuildConfig;
import com.uiza.analytic.JacksonUtils;
import com.uiza.analytic.UZAnalytic;
import com.uiza.analytic.helps.JsonDateSerializer;

import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true, allowGetters = true, allowSetters = true)
public class UZTrackingBody<T> {
    @JsonProperty("specversion")
    private String specVersion;
    @JsonProperty("source")
    private String source;
    @JsonProperty("type")
    private String type;
    @JsonProperty("time")
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date time;
    @JsonProperty("data")
    private T data;

    public UZTrackingBody() {
        this.specVersion = BuildConfig.VERSION_NAME;
        this.source = UZAnalytic.getSourceName();
        this.time = new Date();
        this.type = "io.uiza.watchingevent";
    }

    public UZTrackingBody(T data) {
        this();
        this.data = data;
    }

    public static <T> UZTrackingBody create(T data) {
        return new UZTrackingBody<T>(data);
    }

    /**
     * @param type ex: io.uiza.watchingevent
     */
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public String getSpecVersion() {
        return specVersion;
    }

    public String getSource() {
        return source;
    }

    public Date getTime() {
        return time;
    }

    @NonNull
    @Override
    public String toString() {
        return JacksonUtils.toJson(this);
    }
}
