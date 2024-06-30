package com.hypesofts.homember.application.room.core;

import java.util.List;

public interface RoomRepository {

    Room create(Room room);

    void delete(RoomId roomId);

    List<Room> getRooms();
}
