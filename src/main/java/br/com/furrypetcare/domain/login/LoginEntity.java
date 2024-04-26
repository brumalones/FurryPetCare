package br.com.furrypetcare.domain.login;

import br.com.furrypetcare.domain.login.dto.SignUp;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Table(name = "login")
@Entity(name = "login")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class LoginEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    private int verificationToken;

    private Instant tokenExpiration;

    @Column(nullable = false)
    private boolean isEnabled;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime lastLogin;

    @Enumerated(EnumType.STRING)
    private RoleAuthorities roleAuthorities;

    public LoginEntity(SignUp singUp, RoleAuthorities roleAuthorities) {
        this.username = singUp.username();
        this.password = new BCryptPasswordEncoder().encode(singUp.password());
        this.email = singUp.email();
        definitionToken();
        this.isEnabled = false;
        this.createdAt = LocalDateTime.now();
        this.roleAuthorities = roleAuthorities;
    }

    public void definitionToken() {
        this.verificationToken = new Random().nextInt(100000, 999999);
        this.tokenExpiration = LocalDateTime.now().plusMinutes(15).toInstant(ZoneOffset.of("-03:00"));
    }

    public boolean isTokenExpired() {
        return Instant.now().isAfter(tokenExpiration);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority(roleAuthorities.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
