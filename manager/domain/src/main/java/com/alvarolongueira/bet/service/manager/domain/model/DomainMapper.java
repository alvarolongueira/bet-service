package com.alvarolongueira.bet.service.manager.domain.model;

import com.alvarolongueira.bet.service.manager.client.model.BetSettlement;
import com.alvarolongueira.bet.service.manager.database.BetEntity;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DomainMapper {

    BetSettlement toBetSettlement(BetEntity entity);
}
