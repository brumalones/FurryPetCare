package br.com.furrypetcare.domain.guardian;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GuardianRepository extends JpaRepository<GuardianEntity, UUID> {

}
