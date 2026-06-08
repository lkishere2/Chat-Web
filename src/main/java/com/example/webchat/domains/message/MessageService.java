package com.example.webchat.domains.message;

import com.example.webchat.domains.message.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getHistory() {
        return messageRepository.findTop50ByOrderByTimestampDesc();
    }


}
