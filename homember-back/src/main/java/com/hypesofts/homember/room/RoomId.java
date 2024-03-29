package com.hypesofts.homember.room;

import com.hypesofts.homember.technical.baseclass.FetchableDomainId;
import lombok.NonNull;

import java.util.UUID;

public class RoomId extends FetchableDomainId<Room> {
    static RoomId of(@NonNull UUID id) { return new RoomId(id);}
    static RoomId create() { return new RoomId(UUID.randomUUID());}

    RoomId(UUID id) {
        super(id);
    }

}
