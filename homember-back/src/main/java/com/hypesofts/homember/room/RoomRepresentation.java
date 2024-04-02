package com.hypesofts.homember.room;

import java.util.UUID;

public record RoomRepresentation(UUID id, String name) {

    public RoomRepresentation(Room room) {
        this(room.getId().getId(), room.getName());
    }
}
