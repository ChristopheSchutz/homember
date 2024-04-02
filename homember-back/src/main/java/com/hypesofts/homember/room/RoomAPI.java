package com.hypesofts.homember.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
@ResponseBody
public class RoomAPI {

    @Autowired
    public RoomAPI(RoomService roomService) {
        this.roomService = roomService;
    }

    private RoomService roomService;

    @GetMapping
    public List<RoomRepresentation> getRooms() {
        return roomService.getRooms().stream()
                .map(RoomRepresentation::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public RoomRepresentation createRoom(@RequestBody RoomCreation roomCreation) {
        return new RoomRepresentation(roomService.createRoom(roomCreation));
    }

    @DeleteMapping("/{roomId}")
    public void deleteRoom(@PathVariable UUID roomId) {
        roomService.deleteRoom(RoomId.of(roomId));
    }
}
