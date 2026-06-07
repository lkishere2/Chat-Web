package com.example.webchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // This activates WS support with a message broker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/queue");
        // This is the channel.
        // When a message is sent to /topic/something, the broker fans it out to all subscribers of that channel.
        // The broker in this situation is Spring Boot internal broker, not external ones like RabbitMQ.
        // Convention:
        // /topic is usually used for broadcast (one to many)
        // /queue is usually used for private (one to one)
        config.setApplicationDestinationPrefixes("/app");
        // This is the front door.
        // Messages sent to /app/something are routed to a @MessageMapping("/something") method in our controller first,
        // before anything gets broadcast.
        // Think of this like @RequestMapping but for WebSocket
        config.setUserDestinationPrefix("/user");
        // This config the @SendToUser,
        // Spring handles the context behind and turns it into /user/{user_id}/queue/something
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws");
        // This is the handshake entry point.
        // This is the HTTP URL a client needs to connect first to establish WebSocket connection.
        // It's the one time handshake. After this, the connection upgrades from HTTP to WebSocket.
        // Everything after that uses the broker/prefix rules.
    }

}

// The flow
// Client connects to → /ws  (handshake)
//        │
//        ├── sends to /app/chat.public  →  @MessageMapping  → @SendTo -> /topic/messages (broadcast)
//        │
//        ├── sends to /app/chat.private →  @MessageMapping  →  @SendToUser → /user/queue/messages (private)
//        │
//        ├── subscribes to /topic/messages       ← group chat
//        └── subscribes to /user/queue/messages  ← private messages