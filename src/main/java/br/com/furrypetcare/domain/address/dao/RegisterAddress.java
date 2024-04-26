package br.com.furrypetcare.domain.address.dao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterAddress(
        String street,
        @NotNull
        int number,
        String country,
        String city,
        String state,
        @NotNull
        @Pattern(regexp = "^\\d{5}-\\d{3}$")
        String zip
) {
}
