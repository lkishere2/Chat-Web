package com.example.webchat.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private String sender;
    private String receiver;
    private String message;
    private MessageType type;
    private LocalDateTime timestamp;
}
