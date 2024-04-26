package br.com.furrypetcare.domain.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface LoginRepository extends JpaRepository<LoginEntity, UUID> {

    UserDetails findByUsername(String login);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("""
            select l from login l
            where l.email = :email
            and l.verificationToken = :verificationToken
            """)
    LoginEntity findByEmailAndToken(String email, String verificationToken);

    @Query("""
            select l from login l
            where l.email = :email
            and l.isEnabled = false
            """)
    LoginEntity findByEmail(String email);
}
