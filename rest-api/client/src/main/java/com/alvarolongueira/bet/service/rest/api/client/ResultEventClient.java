package com.alvarolongueira.bet.service.rest.api.client;

import com.alvarolongueira.bet.service.rest.api.client.model.SportEvent;

public interface ResultEventClient {

    String notify(SportEvent event);
}
