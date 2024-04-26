package br.com.furrypetcare.controller;


import br.com.furrypetcare.domain.email.dto.VerificationEmail;
import br.com.furrypetcare.domain.login.LoginEntity;
import br.com.furrypetcare.domain.login.LoginService;
import br.com.furrypetcare.domain.login.dao.DetailSignUp;
import br.com.furrypetcare.domain.login.dao.SignIn;
import br.com.furrypetcare.domain.login.dto.SignUp;
import br.com.furrypetcare.security.DataTokenJWT;
import br.com.furrypetcare.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/security")
@Tag(name = "Security Super Pet Care API")
public class SecurityController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/merchant/sing-up")
    @Transactional
    public ResponseEntity<DetailSignUp> singUpMerchant(@RequestBody @Valid SignUp singUp, UriComponentsBuilder componentsBuilder) {
        var loginEntity = loginService.signUpMerchant(singUp);
        var uri = componentsBuilder.path("service/{id}").buildAndExpand(loginEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailSignUp(loginEntity));
    }

    @PostMapping("/client/sing-up")
    @Transactional
    public ResponseEntity<DetailSignUp> singUpClient(@RequestBody @Valid SignUp singUp, UriComponentsBuilder componentsBuilder) {
        var loginEntity = loginService.signUpClient(singUp);
        var uri = componentsBuilder.path("security/login/{id}").buildAndExpand(loginEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailSignUp(loginEntity));
    }

    @PostMapping("/verify-email")
    @Transactional
    public ResponseEntity<DetailSignUp> verifyEmail(@RequestBody @Valid VerificationEmail verificationEmail) {
        LoginEntity login = loginService.verificationToken(verificationEmail);
        return ResponseEntity.accepted().body(new DetailSignUp(login));
    }

    @PostMapping("/resend-verification-email")
    @Transactional
    public ResponseEntity<DetailSignUp> resendVerification(@RequestParam @Valid String email) {
        LoginEntity login = loginService.resendVerificationToken(email);
        return ResponseEntity.accepted().body(new DetailSignUp(login));
    }

    @PostMapping("/sign-in")
    @Operation(summary = "Autenticação", description = "Método responsável pela autenticação da aplicação")
    public ResponseEntity<?> login(@RequestBody @Valid SignIn signIn) {
        if (loginService.loginIsEnabled(signIn)) {
            var authenticationToken = new UsernamePasswordAuthenticationToken(signIn.username(), signIn.password());
            var authentication = manager.authenticate(authenticationToken);
            var token = tokenService.createToken((LoginEntity) authentication.getPrincipal());
            return ResponseEntity.ok(new DataTokenJWT(token));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Email pendente de confirmação");
    }

}
