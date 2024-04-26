package br.com.furrypetcare.domain.services.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateService(
        String name,
        String description,
        @NotNull
        float price
) {
}
