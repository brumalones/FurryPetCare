package br.com.furrypetcare.domain.auth.security;


import br.com.furrypetcare.controller.exception.ValidatorException;
import br.com.furrypetcare.domain.auth.login.LoginEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String createToken(LoginEntity user) {
        try {
            System.out.println(secret);
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API-Furry-Pet-Care-Auth")
                    .withSubject(user.getUsername())
                    .withExpiresAt(dataExpiration())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new ValidatorException("Error generating JWT token", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API-Furry-Pet-Care-Auth")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new ValidatorException("Invalid or expired JWT token!");
        }
    }

    private Instant dataExpiration() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
