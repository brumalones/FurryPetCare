package br.com.furrypetcare.domain.auth.email.dto;

public record VerificationEmail(
        String email,
        String token
) {
}
