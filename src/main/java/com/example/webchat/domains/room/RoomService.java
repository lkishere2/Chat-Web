package com.example.webchat.domains.room;

import com.example.webchat.domains.room.models.Room;
import com.example.webchat.domains.room.models.RoomUser;
import com.example.webchat.domains.room.models.RoomUserId;
import com.example.webchat.domains.user.UserRepository;
import com.example.webchat.domains.user.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    public void createRoom(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Room newRoom = roomRepository.save(Room.builder().build());

        RoomUser roomUser = RoomUser.builder()
                .id(new RoomUserId(newRoom.getId(), user.getId()))
                .room(newRoom)
                .user(user)
                .build();

        newRoom.getMembers().add(roomUser);
        roomRepository.save(newRoom);
    }
}
