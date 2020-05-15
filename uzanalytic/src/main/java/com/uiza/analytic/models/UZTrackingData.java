package com.uiza.analytic.models;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.uiza.analytic.JacksonUtils;
import com.uiza.analytic.UZAnalytic;
import com.uiza.analytic.helps.JsonDateSerializer;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UZTrackingData {
    @JsonProperty("app_id")
    private String appId;
    @JsonProperty("entity_id")
    private String entityId;
    @JsonProperty("entity_source")
    private String entitySource;
    @JsonProperty("viewer_user_id")
    private String viewerUserId; // android id
    @JsonProperty("event_type")
    private UZEventType eventType;
    @JsonProperty("timestamp")
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date timestamp;

    public UZTrackingData() {
        this(UZAnalytic.getAppId());
    }

    public UZTrackingData(String appId) {
        this.appId = appId;
        this.timestamp = new Date();
        this.viewerUserId = UZAnalytic.getDeviceId();
    }

    public String getAppId() {
        return appId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityId() {
        return entityId;
    }

    /**
     * @param entitySource: source of entity. ex: live
     */
    public void setEntitySource(String entitySource) {
        this.entitySource = entitySource;
    }

    public String getEntitySource() {
        return entitySource;
    }

    public String getViewerUserId() {
        return viewerUserId;
    }

    /**
     * @param eventType: type of event. ex: watching
     */
    public void setEventType(UZEventType eventType) {
        this.eventType = eventType;
    }

    public UZEventType getEventType() {
        return eventType;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @NonNull
    @Override
    public String toString() {
        return JacksonUtils.toJson(this);
    }
}
