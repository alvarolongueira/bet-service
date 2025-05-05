package com.alvarolongueira.bet.service.rest.api.controller;

import com.alvarolongueira.bet.service.rest.api.client.ResultEventClient;
import com.alvarolongueira.bet.service.rest.api.client.model.SportEvent;
import com.alvarolongueira.bet.service.rest.api.controller.mapping.RequestMapper;
import com.alvarolongueira.bet.service.rest.api.spec.BetEventApi;
import com.alvarolongueira.bet.service.rest.api.spec.model.NotificationRequest;
import com.alvarolongueira.bet.service.rest.api.spec.model.NotificationResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class BetController implements BetEventApi {

    @Autowired private RequestMapper requestMapper;
    @Autowired private ResultEventClient resultEventClient;

    @Override
    public NotificationResponse event(NotificationRequest request) {
        log.info("CUSTOM-TRACE-LOG Received notification request: {}", request);
        SportEvent sportEvent = requestMapper.toEvent(request);
        String status = resultEventClient.notify(sportEvent);
        return requestMapper.toResponse(status);
    }
}
