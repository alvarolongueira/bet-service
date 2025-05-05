package com.alvarolongueira.bet.service.rest.api.spec.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonSerialize
public class NotificationRequest {

    @JsonProperty("eventId")
    String eventId;

    @JsonProperty("eventName")
    String eventName;

    @JsonProperty("winnerId")
    String winnerId;
}
