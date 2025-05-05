package com.alvarolongueira.bet.service.manager.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BetRepository extends CrudRepository<BetEntity, Long> {

    List<BetEntity> findByEventId(String eventId);
}
