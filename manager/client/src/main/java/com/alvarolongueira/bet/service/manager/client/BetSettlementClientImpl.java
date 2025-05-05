package com.alvarolongueira.bet.service.manager.client;

import com.alvarolongueira.bet.service.manager.client.model.BetSettlement;
import com.alvarolongueira.bet.service.manager.client.rocketmq.BetRocketMQProducer;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class BetSettlementClientImpl implements BetSettlementClient {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired BetRocketMQProducer producer;

    @Override
    public void notify(Set<BetSettlement> betList) {
        betList.forEach(this::notify);
    }

    private void notify(BetSettlement betSettlement) {
        log.info("CUSTOM-TRACE-LOG Notifying event {}", betSettlement);
        try {
            String json = objectMapper.writeValueAsString(betSettlement);
            producer.sendMessage(json);
        } catch (Exception e) {
            // TODO create handler, exception and retries
        }
    }
}
