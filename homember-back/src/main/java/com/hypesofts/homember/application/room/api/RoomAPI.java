package com.hypesofts.homember.application.room.api;

import com.hypesofts.homember.application.room.usecase.CRUDPlaceUseCase;
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

    private final CRUDPlaceUseCase crudPlaceUseCase;

    @Autowired
    public RoomAPI(CRUDPlaceUseCase crudPlaceUseCase) {
        this.crudPlaceUseCase = crudPlaceUseCase;
    }


    @GetMapping
    public List<RoomRepresentation> getRooms() {
        return crudPlaceUseCase.getRooms();
    }

    @PostMapping
    public RoomRepresentation create(@RequestBody RoomCreation roomCreation) {
        return crudPlaceUseCase.create(roomCreation);
    }

    @DeleteMapping("/{roomId}")
    public void delete(@PathVariable UUID roomId) {
        crudPlaceUseCase.delete(roomId);
    }
}
