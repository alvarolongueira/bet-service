package com.alvarolongueira.bet.service.rest.api.controller.mapping;

import com.alvarolongueira.bet.service.rest.api.client.model.SportEvent;
import com.alvarolongueira.bet.service.rest.api.spec.model.NotificationRequest;
import com.alvarolongueira.bet.service.rest.api.spec.model.NotificationResponse;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    SportEvent toEvent(NotificationRequest request);

    NotificationResponse toResponse(String status);
}
