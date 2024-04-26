package br.com.furrypetcare.domain.pet;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PetRepository extends JpaRepository<PetEntity, UUID> {

}
