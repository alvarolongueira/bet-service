package com.alvarolongueira.bet.service.manager.domain.usecase;

import com.alvarolongueira.bet.service.manager.client.BetSettlementClient;
import com.alvarolongueira.bet.service.manager.client.model.BetSettlement;
import com.alvarolongueira.bet.service.manager.database.BetRepository;
import com.alvarolongueira.bet.service.manager.domain.model.DomainMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class FindBetsForEventsUseCaseImpl implements FindBetsForEventsUseCase {

    @Autowired BetSettlementClient client;
    @Autowired BetRepository repository;
    @Autowired DomainMapper mapper;

    @Override
    public void process(Request request) {
        log.info("CUSTOM-TRACE-LOG FindBetsForEventsUseCase processing: {}", request);
        Set<BetSettlement> betList =
                repository.findByEventId(request.eventId()).parallelStream()
                        .filter(current -> current.getWinnerId().equals(request.winnerId()))
                        .map(mapper::toBetSettlement)
                        .collect(Collectors.toSet());

        if (!CollectionUtils.isEmpty(betList)) {
            client.notify(betList);
        }
    }
}
