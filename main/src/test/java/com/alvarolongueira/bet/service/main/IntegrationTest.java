package com.alvarolongueira.bet.service.main;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import com.alvarolongueira.bet.service.manager.client.model.BetSettlement;
import com.alvarolongueira.bet.service.manager.client.rocketmq.BetRocketMQProducer;
import com.alvarolongueira.bet.service.rest.api.spec.model.NotificationRequest;
import com.alvarolongueira.bet.service.rest.api.spec.model.NotificationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@EmbeddedKafka(
        partitions = 1,
        brokerProperties = {"listeners=PLAINTEXT://localhost:29092", "port=29092"})
public class IntegrationTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean BetRocketMQProducer betRocketMQProducer;
    @LocalServerPort private int port;
    @Autowired private TestRestTemplate restTemplate;

    @Test
    void when_api_receives_event_then_data_is_populated_successfully() throws Exception {
        NotificationRequest request = new NotificationRequest("soccer", "soccer_match", "lion");
        restTemplate.postForEntity(getUrl(), request, NotificationResponse.class);

        List<BetSettlement> expectedList =
                List.of(
                        new BetSettlement(2L, "james", "soccer", "marketId", "lion", 20.23),
                        new BetSettlement(9L, "ana", "soccer", "marketId", "lion", 19.0),
                        new BetSettlement(11L, "clark", "soccer", "marketId", "lion", 61.0));

        for (BetSettlement expected : expectedList) {
            verify(betRocketMQProducer, timeout(5000))
                    .sendMessage(objectMapper.writeValueAsString(expected));
        }
    }

    private String getUrl() {
        return "http://localhost:" + port + "/event_winner";
    }
}
