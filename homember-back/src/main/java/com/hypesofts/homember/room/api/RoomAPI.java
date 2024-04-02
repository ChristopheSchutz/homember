package com.hypesofts.homember.room.api;

import com.hypesofts.homember.room.core.projections.RoomDetails;
import com.hypesofts.homember.room.usecase.CRUDRoomUseCase;
import com.hypesofts.homember.room.usecase.GetRoomDetailsUseCase;
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

@RestController
@RequestMapping("/api/rooms")
@ResponseBody
public class RoomAPI {

    @Autowired
    public RoomAPI(CRUDRoomUseCase roomUseCase, GetRoomDetailsUseCase getRoomDetailsUseCase) {
        this.roomUseCase = roomUseCase;
        this.getRoomDetailsUseCase = getRoomDetailsUseCase;
    }

    private CRUDRoomUseCase roomUseCase;
    private GetRoomDetailsUseCase getRoomDetailsUseCase;

    @GetMapping
    public List<RoomRepresentation> getRooms() {
        return roomUseCase.getRooms();
    }

    @GetMapping("/details")
    public List<RoomDetails> getRoomDetails() {
        return getRoomDetailsUseCase.getRoomDetails();
    }

    @PostMapping
    public RoomRepresentation create(@RequestBody RoomCreation roomCreation) {
        return roomUseCase.create(roomCreation);
    }

    @DeleteMapping("/{roomId}")
    public void delete(@PathVariable UUID roomId) {
        roomUseCase.delete(roomId);
    }
}
