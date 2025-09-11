package gr.kostasmavrakakis.website.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.lang.StringBuilder;

import gr.kostasmavrakakis.website.dto.MessageDTO;
import gr.kostasmavrakakis.website.util.InputSanitizer;


@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${contactform.recipient}")
    private String owner; 

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMessage(MessageDTO messageDTO) {

        if ("ERROR".equals(messageDTO.getName())) { throw new IllegalStateException("Forced error for testing"); } // DEBUG! TEST SUBMISSION FAILURE

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(owner);
        mail.setFrom(owner);
        mail.setReplyTo(InputSanitizer.sanitizeHeaders(messageDTO.getEmail()));
        mail.setSubject("Νέο μήνυμα από: " + 
                        InputSanitizer.sanitizeHeaders(messageDTO.getName()) + " | " + 
                        InputSanitizer.sanitizeHeaders(messageDTO.getEmail()));

        StringBuilder sb = new StringBuilder();
        sb.append(InputSanitizer.escapeHtml(messageDTO.getName())).append("\n");
        sb.append(InputSanitizer.escapeHtml(messageDTO.getEmail())).append("\n");
        if (!"".equals(messageDTO.getTelephone())) {
            sb.append(InputSanitizer.escapeHtml(messageDTO.getTelephone())).append("\n");
        }
        sb.append("\n");
        sb.append(InputSanitizer.escapeHtml(messageDTO.getMessage()));

        sb.append("\n").append("DEBUG sanitize header: ").append(InputSanitizer.sanitizeHeaders(messageDTO.getMessage())); //DEBUG! TEST HEADER SANITIZER
        sb.append("\n").append("DEBUG sanitize log: ").append(InputSanitizer.sanitizeLogs(messageDTO.getMessage())); //DEBUG! TEST LOG SANITIZER
        sb.append("\n").append("DEBUG escape html: ").append(InputSanitizer.escapeHtml(messageDTO.getMessage())); //DEBUG! TEST HTML SANITIZER

        mail.setText(sb.toString());

        mailSender.send(mail);
    }
}