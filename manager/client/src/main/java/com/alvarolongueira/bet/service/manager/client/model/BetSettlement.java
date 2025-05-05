package com.alvarolongueira.bet.service.manager.client.model;

public record BetSettlement(
        Long betId,
        String userId,
        String eventId,
        String eventMarketId,
        String winnerId,
        Double amount) {}
