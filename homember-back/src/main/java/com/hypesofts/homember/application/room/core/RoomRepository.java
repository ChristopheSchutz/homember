package com.hypesofts.homember.application.room.core;
import com.hypesofts.homember.application.room.api.RoomCreation;

import java.util.List;

public interface RoomRepository {

    List<Room> getRooms();
    Room create(RoomCreation roomCreation);
    void delete(RoomId roomId);
}
