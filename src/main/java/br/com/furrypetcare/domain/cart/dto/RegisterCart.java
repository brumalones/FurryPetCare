package br.com.furrypetcare.domain.cart.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record RegisterCart(

        @NotNull
        List<UUID> serviceId,
        @NotNull
        UUID petId

) {
}
