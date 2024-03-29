package com.hypesofts.homember.room;

import com.hypesofts.homember.technical.baseclass.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

public record RoomDTO (UUID id, String name) {

    public RoomDTO(Room room) {
        this(room.getId().getId(), room.getName());
    }
}
