package com.example.webchat.domains.room;

import com.example.webchat.domains.room.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
