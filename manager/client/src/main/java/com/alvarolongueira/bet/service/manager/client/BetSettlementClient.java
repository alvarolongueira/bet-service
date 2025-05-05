package com.alvarolongueira.bet.service.manager.client;

import com.alvarolongueira.bet.service.manager.client.model.BetSettlement;

import java.util.Set;

public interface BetSettlementClient {

    void notify(Set<BetSettlement> betList);
}
