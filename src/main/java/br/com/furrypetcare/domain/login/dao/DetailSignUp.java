package br.com.furrypetcare.domain.login.dao;


import br.com.furrypetcare.domain.login.LoginEntity;

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
