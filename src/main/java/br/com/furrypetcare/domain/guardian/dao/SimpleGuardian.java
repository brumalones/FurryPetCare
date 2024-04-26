package br.com.furrypetcare.domain.guardian.dao;

import br.com.furrypetcare.domain.guardian.GuardianEntity;

import java.util.UUID;

public record SimpleGuardian(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phone
) {
    public SimpleGuardian(GuardianEntity guardian) {
        this(
                guardian.getId(),
                guardian.getFirstName(),
                guardian.getLastName(),
                guardian.getEmail(),
                guardian.getPhone()
        );
    }
}
