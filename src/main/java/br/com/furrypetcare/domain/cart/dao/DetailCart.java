package br.com.furrypetcare.domain.cart.dao;


import br.com.furrypetcare.domain.cart.CartEntity;
import br.com.furrypetcare.domain.pet.dao.DetailPet;
import br.com.furrypetcare.domain.services.dao.DetailService;

import java.util.List;
import java.util.UUID;

public record DetailCart(
        UUID id,
        DetailPet pet,
        List<DetailService> services
) {

    public DetailCart(CartEntity cart) {
        this(
                cart.getId(),
                new DetailPet(cart.getPet()),
                cart.getServices().stream().map(DetailService::new).toList()
        );
    }
}
