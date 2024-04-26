package br.com.furrypetcare.domain.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationService {

    @Autowired
    private EmailService emailService;

    public void sendVerificationEmail(String email, int token) {
        String subject = "Verificação de Email";
        String verificationLink = "http://localhost:8080/verify?token=" + token;
        String text = "Por favor, clique no link abaixo para verificar seu endereço de email:\n\n" + verificationLink;
        emailService.sendEmail(email, subject, text);
    }

}
