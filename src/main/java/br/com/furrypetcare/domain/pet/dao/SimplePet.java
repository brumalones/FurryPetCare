package br.com.furrypetcare.domain.pet.dao;


import br.com.furrypetcare.domain.guardian.dao.SimpleGuardian;
import br.com.furrypetcare.domain.pet.PetEntity;
import br.com.furrypetcare.domain.pet.PetGender;

import java.util.UUID;

public record SimplePet(
        UUID id,
        String name,
        String breed,
        int age,
        PetGender gender,
        int weight,
        SimpleGuardian guardian
) {
    public SimplePet(PetEntity petEntity) {
        this(
                petEntity.getId(),
                petEntity.getName(),
                petEntity.getBreed(),
                petEntity.getAge(),
                petEntity.getGender(),
                petEntity.getWeight(),
                new SimpleGuardian(petEntity.getGuardian())
        );
    }
}
