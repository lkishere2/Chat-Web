package com.example.webchat.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public List<Message> getHistory() {
        return chatRepository.findTop50ByOrderByTimestampDesc();
    }


}
