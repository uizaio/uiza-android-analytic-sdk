package com.uiza.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UZLiveCounter {
    @JsonProperty("views")
    private int views;

    public UZLiveCounter() {}

    public int getViews() {
        return views;
    }
}
