package com.example.webchat.domains.message;

import com.example.webchat.domains.message.dtos.MessageDTO;
import com.example.webchat.domains.message.models.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.join")
    @SendTo("/topic/public")
    public MessageDTO join(@Payload MessageDTO message) {
        message.setType(MessageType.JOIN);
        message.setTimestamp(LocalDateTime.now());
        return message;
    }

    @MessageMapping("/chat.leave")
    @SendTo("/topic/public")
    public MessageDTO leave(@Payload MessageDTO message) {
        message.setType(MessageType.LEAVE);
        message.setTimestamp(LocalDateTime.now());
        return message;
    }

    @MessageMapping("/chat.group/{id}")
    public void sendToGroup(@Payload MessageDTO message, @DestinationVariable String id) {
        messagingTemplate.convertAndSend(
                "/topic/messages/group/" + id,
                message
        );
    }

    @MessageMapping("/chat.private")
    public void sendToUser(@Payload MessageDTO message) {
        messagingTemplate.convertAndSendToUser(
                message.getReceiver(),
                "/queue/messages",
                message
        );
    }
}
