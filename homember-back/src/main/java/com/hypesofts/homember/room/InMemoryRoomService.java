package com.hypesofts.homember.room;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryRoomService implements RoomService {

    private final List<Room> rooms;

    public InMemoryRoomService() {
        this.rooms = new ArrayList<>(
                List.of(
                        new Room(RoomId.create(), "Salon"),
                        new Room(RoomId.create(), "Cave"),
                        new Room(RoomId.create(), "Grenier"))
        );
    }

    @Override
    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public Room createRoom(RoomCreation roomCreation) {
        Room room = new Room(RoomId.create(), roomCreation.name());
        rooms.add(room);
        return room;
    }

    @Override
    public void deleteRoom(RoomId roomId) {
        rooms.removeIf(room -> room.getId().equals(roomId));
    }
}
