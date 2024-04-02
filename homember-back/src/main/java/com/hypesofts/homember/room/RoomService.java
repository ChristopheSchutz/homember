package com.hypesofts.homember.room;

import java.util.List;

public interface RoomService {

    List<Room> getRooms();
    Room createRoom(RoomCreation roomCreation);
    void deleteRoom(RoomId roomId);
}
