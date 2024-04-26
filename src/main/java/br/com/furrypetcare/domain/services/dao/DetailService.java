package br.com.furrypetcare.domain.services.dao;


import br.com.furrypetcare.domain.services.ServiceEntity;

import java.util.UUID;

public record DetailService(
        UUID id,
        String name,
        String description,
        float price
) {

    public DetailService(ServiceEntity service) {
        this(
                service.getId(),
                service.getName(),
                service.getDescription(),
                service.getPrice()
        );
    }
}


