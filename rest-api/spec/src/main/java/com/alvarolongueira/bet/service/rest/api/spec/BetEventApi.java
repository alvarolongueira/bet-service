package com.alvarolongueira.bet.service.rest.api.spec;

import com.alvarolongueira.bet.service.rest.api.spec.model.NotificationRequest;
import com.alvarolongueira.bet.service.rest.api.spec.model.NotificationResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface BetEventApi {

    // TODO
    // The idea of this module is to have an open api spec definition so
    // all the classes in this module are auto generated

    @PostMapping("/event_winner")
    NotificationResponse event(@RequestBody NotificationRequest request);
}
