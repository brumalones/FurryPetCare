package br.com.furrypetcare.domain.pet.dao;


import br.com.furrypetcare.domain.guardian.dao.DetailGuardian;
import br.com.furrypetcare.domain.pet.PetBehavior;
import br.com.furrypetcare.domain.pet.PetCategory;
import br.com.furrypetcare.domain.pet.PetEntity;
import br.com.furrypetcare.domain.pet.PetGender;

import java.util.UUID;

public record DetailPet(
        UUID id,
        String name,
        PetCategory category,
        String breed,
        int age,
        PetGender gender,
        int weight,
        PetBehavior behavior,
        DetailGuardian guardian
) {
    public DetailPet(PetEntity pet) {
        this(
                pet.getId(),
                pet.getName(),
                pet.getCategory(),
                pet.getBreed(),
                pet.getAge(),
                pet.getGender(),
                pet.getWeight(),
                pet.getBehavior(),
                new DetailGuardian(pet.getGuardian())
        );
    }
}
