package br.com.furrypetcare.domain.auth.login.dao;


import br.com.furrypetcare.domain.auth.login.LoginEntity;

public record DetailSignUp(

        String username,
        String email,
        boolean isEnabled
) {
    public DetailSignUp(LoginEntity loginEntity) {
        this(
                loginEntity.getUsername(),
                loginEntity.getEmail(),
                loginEntity.isEnabled()
        );
    }
}
