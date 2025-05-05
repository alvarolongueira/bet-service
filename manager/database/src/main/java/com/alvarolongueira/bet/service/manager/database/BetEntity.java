package com.alvarolongueira.bet.service.manager.database;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long betId;

    String userId;
    String eventId;
    String eventMarketId;
    String winnerId;
    Double amount;
}
