package com.alvarolongueira.bet.service.rest.api.client.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BetKafkaProducer {

    @Autowired private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${bet.kafka.topic.outcome}")
    private String topic;

    public void sendMessage(String json) {
        kafkaTemplate.send(topic, json);
    }
}
