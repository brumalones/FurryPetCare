package br.com.furrypetcare.domain.guardian.dto;

import br.com.furrypetcare.domain.address.dao.RegisterAddress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterGuardian(
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        @Email
        String email,
        @NotNull
        @Pattern(regexp = "^\\d{11}$")
        String phone,
        @Valid
        @NotNull
        RegisterAddress address
) {
}
