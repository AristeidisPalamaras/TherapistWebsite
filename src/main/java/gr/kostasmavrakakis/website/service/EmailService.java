package gr.kostasmavrakakis.website.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import gr.kostasmavrakakis.website.dto.MessageDTO;
import gr.kostasmavrakakis.website.util.InputSanitizer;


@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${contactform.recipient}")
    private String recipient;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMessage(MessageDTO messageDTO) {

        
        if ("ERROR".equals(messageDTO.getName())) { throw new IllegalStateException("Forced error for testing"); } // DEBUG! TEST SUBMISSION FAILURE

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(recipient);
        mail.setFrom(messageDTO.getEmail());
        mail.setSubject("New mail | Name: " + InputSanitizer.sanitizeHeaders(messageDTO.getName()) + 
                                " | Email: " + InputSanitizer.sanitizeHeaders(messageDTO.getEmail()) +
                                " | DEBUG: "+ InputSanitizer.sanitizeHeaders(messageDTO.getMessage()) +" | " + //DEBUG! TEST HEADER SANITIZER
                                " | Tel: " + InputSanitizer.sanitizeHeaders(messageDTO.getTelephone()));
        mail.setText(InputSanitizer.escapeHtml(messageDTO.getMessage()));

        mailSender.send(mail);
    }
}