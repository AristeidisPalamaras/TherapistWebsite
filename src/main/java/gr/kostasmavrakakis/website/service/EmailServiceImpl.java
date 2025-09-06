package gr.kostasmavrakakis.website.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import gr.kostasmavrakakis.website.dto.MessageDTO;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${contactform.recipient}")
    private String recipient;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMessage(MessageDTO messageDTO) {

        // TEST
        // if ("tost".equals(messageDTO.getName())) { throw new IllegalStateException("Forced error for testing"); }

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(recipient);
        mail.setFrom(messageDTO.getEmail());
        mail.setSubject("New contact form submission from " + messageDTO.getName() + " | Email " + messageDTO.getEmail() + " | Tel: " + messageDTO.getTelephone());
        mail.setText(messageDTO.getMessage());

        mailSender.send(mail);
    }
}