package com.alvarolongueira.bet.service.manager.domain.usecase;

public interface FindBetsForEventsUseCase {

    void process(Request request);

    record Request(String eventId, String eventName, String winnerId) {}
}
