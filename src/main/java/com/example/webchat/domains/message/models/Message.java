package com.example.webchat.domains.message.models;

import com.example.webchat.domains.room.models.Room;
import com.example.webchat.domains.user.models.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "messages")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private User receiver;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private User sender;

    @Enumerated(EnumType.STRING)
    private MessageType type;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    private Room room;

}