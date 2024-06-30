package com.hypesofts.homember.application.room.core;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryRoomRepository implements RoomRepository {

    private final List<Room> rooms;

    public InMemoryRoomRepository() {
        this.rooms = new ArrayList<>(
                List.of(
                        new Room(RoomId.create(), "Salon"),
                        new Room(RoomId.create(), "Cave"),
                        new Room(RoomId.create(), "Grenier"))
        );
    }

    @Override
    public Room create(Room room) {
        rooms.add(room);
        return room;
    }

    @Override
    public void delete(RoomId roomId) {
        rooms.removeIf(room -> room.getId().equals(roomId));
    }

    @Override
    public List<Room> getRooms() {
        return rooms;
    }
}
