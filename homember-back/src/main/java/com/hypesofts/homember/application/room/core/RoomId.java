package com.hypesofts.homember.application.room.core;

import com.hypesofts.homember.application.technical.baseclass.FetchableDomainId;
import lombok.NonNull;

import java.util.UUID;

public class RoomId extends FetchableDomainId<Room> {
    public static RoomId of(@NonNull UUID id) {
        return new RoomId(id);
    }

    public static RoomId create() {
        return new RoomId(UUID.randomUUID());
    }

    public RoomId(UUID id) {
        super(id);
    }

}
