package br.com.furrypetcare.domain.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendEmail(String to, String subject, String username, int token) throws MessagingException {
        var body = prepareEmail(username, subject, token);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);
        javaMailSender.send(message);
    }

    private String prepareEmail(String username, String subject, int token) {
        Context context = new Context();
        context.setVariable("token", formartToken(token));
        context.setVariable("user", username);
        return templateEngine.process("verificationEmail", context);
    }

    private String formartToken(int token) {
        String sToken = Integer.toString(token);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sToken.length(); i++) {
            if (i > 0 && i % 3 == 0) {
                sb.append('-');
            }
            sb.append(sToken.charAt(i));
        }
        return sb.toString();
    }
}
