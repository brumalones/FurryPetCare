package br.com.furrypetcare.domain.login.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record SignUp(
        @NotNull
        String username,
        @NotNull
        String password,
        @NotNull
        @Email
        String email
) {
}
