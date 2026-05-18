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

    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    public void addSession(String userId, WebSocketSession session) {
        sessions.put(userId, session);
    }

    public void removeSession(String userId) {
        sessions.remove(userId);
    }

    public void handleIncoming(String sender, String content) throws Exception {
        Message message = Message.builder()
                .sender(sender)
                .content(content)
                .build();
        chatRepository.save(message);
        broadcast(sender + ": " + content);
    }

    public void broadcast(String text) throws Exception {
        for (WebSocketSession session : sessions.values()) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(text));
            }
        }
    }

    public List<Message> getHistory() {
        return chatRepository.findTop50ByOrderByTimestampDesc();
    }
}
