package com.alvarolongueira.bet.service.manager.client.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BetRocketMQProducer {

    @Autowired private RocketMQTemplate rocketMQTemplate;

    @Value("${bet.rocketmq.topic.outcome}")
    private String topic;

    public void sendMessage(String json) {
        rocketMQTemplate.asyncSend(
                topic,
                new GenericMessage<>(json),
                new SendCallback() {

                    @Override
                    public void onSuccess(SendResult sendResult) {
                        log.info("CUSTOM-TRACE-LOG Success SendResult {}", sendResult);
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        log.error("CUSTOM-TRACE-LOG Exception", throwable);
                    }
                });
    }
}
