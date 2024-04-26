package br.com.furrypetcare.domain.pet.dto;

import br.com.furrypetcare.domain.pet.PetBehavior;
import br.com.furrypetcare.domain.pet.PetCategory;
import br.com.furrypetcare.domain.pet.PetGender;
import jakarta.validation.constraints.NotNull;

public record UpdatePet(
        String name,
        PetCategory category,
        String breed,
        @NotNull
        int age,
        PetGender gender,
        @NotNull
        int weight,
        PetBehavior behavior
) {
}
