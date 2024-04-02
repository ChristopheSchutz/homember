package com.hypesofts.homember.room.core;

import com.hypesofts.homember.technical.baseclass.FetchableDomainId;
import lombok.NonNull;

import java.util.UUID;

public class RoomId extends FetchableDomainId<Room> {
    public static RoomId of(@NonNull UUID id) { return new RoomId(id);}
    static RoomId create() { return new RoomId(UUID.randomUUID());}

    public RoomId(UUID id) {
        super(id);
    }

}
