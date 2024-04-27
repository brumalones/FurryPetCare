package br.com.furrypetcare.domain.login;

import br.com.furrypetcare.domain.email.EmailVerificationService;
import br.com.furrypetcare.domain.email.dto.VerificationEmail;
import br.com.furrypetcare.domain.login.dao.SignIn;
import br.com.furrypetcare.domain.login.dto.SignUp;
import br.com.furrypetcare.controller.exception.ValidatorException;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private LoginRepository repository;

    @Autowired
    EmailVerificationService emailVerificationService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    public LoginEntity signUpMerchant(SignUp singUp) throws MessagingException {
        return sigUp(singUp, RoleAuthorities.ROLE_MERCHANT);
    }

    public LoginEntity signUpClient(SignUp singUp) throws MessagingException {
        return sigUp(singUp, RoleAuthorities.ROLE_CLIENT);
    }

    private LoginEntity sigUp(SignUp singUp, RoleAuthorities roleAuthorities) throws MessagingException {
        if (!repository.existsByUsername(singUp.username())) {
            if (!repository.existsByEmail(singUp.email())) {
                var loginEntity = new LoginEntity(singUp, roleAuthorities);
                emailVerificationService.sendVerificationEmail(loginEntity.getEmail(), loginEntity.getUsername(), loginEntity.getVerificationToken());
                return repository.save(loginEntity);
            }
            throw new ValidatorException("Email " + singUp.email() + " already exists");
        }
        throw new ValidatorException("Username " + singUp.username() + " already exists");
    }

    public LoginEntity verificationToken(VerificationEmail verificationEmail) {
        var loginEntity = repository.findByEmailAndToken(verificationEmail.email(), verificationEmail.token());
        if (!loginEntity.isTokenExpired()) {
            loginEntity.setEnabled(true);
            return repository.save(loginEntity);
        }
        throw new ValidatorException("Token has expired!");
    }

    public LoginEntity resendVerificationToken(String email) throws MessagingException {
        var loginEntity = repository.findByEmail(email);
        loginEntity.definitionToken();
        emailVerificationService.sendVerificationEmail(loginEntity.getEmail(), loginEntity.getUsername(), loginEntity.getVerificationToken());
        return repository.save(loginEntity);
    }

    public boolean loginIsEnabled(SignIn signIn) {
        return repository.findByUsername(signIn.username()).isEnabled();
    }
}
