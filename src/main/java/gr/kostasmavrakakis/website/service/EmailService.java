package gr.kostasmavrakakis.website.service;

import gr.kostasmavrakakis.website.dto.MessageDTO;

public interface EmailService {
    void sendMessage(MessageDTO messageDTO);
}
