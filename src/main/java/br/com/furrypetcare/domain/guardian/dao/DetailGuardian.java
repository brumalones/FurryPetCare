package br.com.furrypetcare.domain.guardian.dao;

import br.com.furrypetcare.domain.address.dto.DetailAddress;
import br.com.furrypetcare.domain.guardian.GuardianEntity;
import br.com.furrypetcare.domain.pet.dao.DetailPet;

import java.util.List;
import java.util.UUID;

public record DetailGuardian(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phone,
        DetailAddress address,
        List<DetailPet> pets
) {
    public DetailGuardian(GuardianEntity guardian) {
        this(
                guardian.getId(),
                guardian.getFirstName(),
                guardian.getLastName(),
                guardian.getEmail(),
                guardian.getPhone(),
                new DetailAddress(guardian.getAddress()),
                guardian.getPets().stream().map(DetailPet::new).toList()
        );
    }
}
