package br.com.furrypetcare.domain.cart.dto;


import br.com.furrypetcare.domain.services.ServiceEntity;

import java.util.List;

public record UpdateCart(
        List<ServiceEntity> services
) {
}
