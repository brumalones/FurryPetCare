package br.com.furrypetcare.domain.pet;

import br.com.furrypetcare.domain.guardian.GuardianRepository;
import br.com.furrypetcare.domain.pet.dto.RegisterPet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComponentPet {
    @Autowired
    GuardianRepository guardianRepository;

    public PetEntity registerPet(RegisterPet registerPet) {
        var guardian = guardianRepository.getReferenceById(registerPet.guardianId());
        return new PetEntity(registerPet, guardian);
    }
}
