package com.hypesofts.homember.application.room.api;

import com.hypesofts.homember.application.room.core.projections.RoomDetails;
import com.hypesofts.homember.application.room.usecase.CRUDRoomUseCase;
import com.hypesofts.homember.application.room.usecase.GetRoomDetailsUseCase;
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

    private CRUDRoomUseCase crudRoomUseCase;

    @Autowired
    public RoomAPI(CRUDRoomUseCase crudRoomUseCase, GetRoomDetailsUseCase getRoomDetailsUseCase) {
        this.crudRoomUseCase = crudRoomUseCase;
        this.getRoomDetailsUseCase = getRoomDetailsUseCase;
    }
    private GetRoomDetailsUseCase getRoomDetailsUseCase;

    @GetMapping
    public List<RoomRepresentation> getRooms() {
        return crudRoomUseCase.getRooms();
    }

    @GetMapping("/details")
    public List<RoomDetails> getRoomDetails() {
        return getRoomDetailsUseCase.getRoomDetails();
    }

    @PostMapping
    public RoomRepresentation create(@RequestBody RoomCreation roomCreation) {
        return crudRoomUseCase.create(roomCreation);
    }

    @DeleteMapping("/{roomId}")
    public void delete(@PathVariable UUID roomId) {
        crudRoomUseCase.delete(roomId);
    }
}
