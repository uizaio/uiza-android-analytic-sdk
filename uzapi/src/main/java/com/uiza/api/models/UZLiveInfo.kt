package com.uiza.api.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class UZLiveInfo constructor(@JsonProperty("app_id") var appId: String = "",
                                  @JsonProperty("entity_id") var entityId: String = "",
                                  @JsonProperty("entity_source") var entitySource: String = "") : Serializable