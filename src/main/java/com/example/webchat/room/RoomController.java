package com.example.webchat.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<Void> createRoom(@PathVariable Long userId) {
        roomService.createRoom(userId);
        return ResponseEntity.ok().build();
    }

}
