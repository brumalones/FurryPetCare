package br.com.furrypetcare.domain.email;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;

@Service
public class EmailVerificationService {

    @Autowired
    private EmailService emailService;

    public void sendVerificationEmail(String email, String username, int token) throws MessagingException {
        emailService.sendEmail(email, "Confirmação de Email", username, token);
    }

}
