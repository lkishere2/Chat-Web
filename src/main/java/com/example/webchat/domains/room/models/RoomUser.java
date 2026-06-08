package com.example.webchat.domains.room.models;

import com.example.webchat.domains.user.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room_user")
public class RoomUser {

    @EmbeddedId
    private RoomUserId id;

    @ManyToOne
    @MapsId("roomId")
    private Room room;

    @ManyToOne
    @MapsId("userId")
    private User user;

}