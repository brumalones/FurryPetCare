package br.com.furrypetcare.domain.services.dao;


import br.com.furrypetcare.domain.services.ServiceEntity;

import java.util.UUID;

public record SimpleServices(
        UUID id,
        String name,
        float price
) {
    public SimpleServices(ServiceEntity serviceEntity) {
        this(
                serviceEntity.getId(),
                serviceEntity.getName(),
                serviceEntity.getPrice()
        );
    }
}
