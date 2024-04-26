package br.com.furrypetcare.domain.services.dto;

import jakarta.validation.constraints.NotNull;

public record RegisterService(
        @NotNull
        String name,
        @NotNull
        String description,
        @NotNull
        float price
) {
}
