package com.hypesofts.homember.room.api;

import com.hypesofts.homember.room.core.Room;

import java.util.UUID;

public record RoomRepresentation(UUID id, String name) {

    public RoomRepresentation(Room room) {
        this(room.getId().getId(), room.getName());
    }
}
