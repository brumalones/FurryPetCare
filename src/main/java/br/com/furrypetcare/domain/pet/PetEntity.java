package br.com.furrypetcare.domain.pet;

import br.com.furrypetcare.domain.guardian.GuardianEntity;
import br.com.furrypetcare.domain.pet.dto.RegisterPet;
import br.com.furrypetcare.domain.pet.dto.UpdatePet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "pet")
@Table(name = "pet")
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "guardian_id", referencedColumnName = "id")
    private GuardianEntity guardian;

    private String name;
    @Enumerated(EnumType.STRING)
    private PetCategory category;
    private String breed;
    private int age;
    @Enumerated(EnumType.STRING)
    private PetGender gender;
    private int weight;
    @Enumerated(EnumType.STRING)
    private PetBehavior behavior;

    public PetEntity(RegisterPet registerPet, GuardianEntity petGuardian) {
        guardian = petGuardian;
        name = registerPet.name();
        category = registerPet.category();
        breed = registerPet.breed();
        age = registerPet.age();
        gender = registerPet.gender();
        weight = registerPet.weight();
        behavior = registerPet.behavior();
    }

    public void updateData(UpdatePet pet) {
        if (pet.name(

        ) != null)
            name = pet.name();
        if (pet.category() != null)
            category = pet.category();
        if (pet.breed() != null)
            breed = pet.breed();
        age = pet.age();
        if (pet.gender() != null)
            gender = pet.gender();
        weight = pet.weight();
        if (pet.behavior() != null)
            behavior = pet.behavior();
    }
}
