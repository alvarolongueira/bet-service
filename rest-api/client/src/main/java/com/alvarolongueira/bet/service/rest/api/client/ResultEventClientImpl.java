package com.alvarolongueira.bet.service.rest.api.client;

import com.alvarolongueira.bet.service.rest.api.client.kafka.BetKafkaProducer;
import com.alvarolongueira.bet.service.rest.api.client.model.SportEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ResultEventClientImpl implements ResultEventClient {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired private BetKafkaProducer producer;

    @Override
    public String notify(SportEvent event) {
        log.info("CUSTOM-TRACE-LOG Notifying event {}", event);
        try {
            String json = objectMapper.writeValueAsString(event);
            producer.sendMessage(json);
            return "OK";
        } catch (Exception e) {
            // TODO create handler, exception and retries
            return "ERROR";
        }
    }
}
