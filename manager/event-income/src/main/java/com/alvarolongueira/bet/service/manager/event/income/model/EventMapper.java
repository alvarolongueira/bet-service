package com.alvarolongueira.bet.service.manager.event.income.model;

import com.alvarolongueira.bet.service.manager.domain.usecase.FindBetsForEventsUseCase;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

    FindBetsForEventsUseCase.Request toRequest(SportEvent sportEvent);
}
