package com.alvarolongueira.bet.service.manager.event.income;

import com.alvarolongueira.bet.service.manager.domain.usecase.FindBetsForEventsUseCase;
import com.alvarolongueira.bet.service.manager.event.income.model.EventMapper;
import com.alvarolongueira.bet.service.manager.event.income.model.SportEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class BetKafkaConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired private FindBetsForEventsUseCase useCase;
    @Autowired private EventMapper mapper;

    @KafkaListener(groupId = "${bet.kafka.group-id}", topics = "${bet.kafka.topic.outcome}")
    public void consume(String message) {
        try {
            log.info("CUSTOM-TRACE-LOG Received message {}", message);
            SportEvent sportEvent = objectMapper.readValue(message, SportEvent.class);
            useCase.process(mapper.toRequest(sportEvent));
        } catch (Exception e) {
            // TODO create handler and exception
        }
    }
}
