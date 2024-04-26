package br.com.furrypetcare.domain.cart.dao;


import br.com.furrypetcare.domain.cart.CartEntity;
import br.com.furrypetcare.domain.pet.dao.SimplePet;
import br.com.furrypetcare.domain.services.dao.SimpleServices;

import java.util.List;
import java.util.UUID;

public record SimpleCart(
        UUID id,
        List<SimpleServices> services,
        SimplePet pet
) {
    public SimpleCart(CartEntity cartEntity) {
        this(
                cartEntity.getId(),
                cartEntity.getServices().stream().map(SimpleServices::new).toList(),
                new SimplePet(cartEntity.getPet())
        );
    }
}
