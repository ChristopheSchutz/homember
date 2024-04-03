package com.hypesofts.homember.application.room.api;


import com.hypesofts.homember.application.room.core.Room;

import java.util.UUID;

public record RoomRepresentation(UUID id, String name) {

    public RoomRepresentation(Room room) {
        this(room.getId().getId(), room.getName());
    }
}
