package br.com.furrypetcare.domain.email.dto;

public record VerificationEmail(
        String email,
        String token
) {
}
