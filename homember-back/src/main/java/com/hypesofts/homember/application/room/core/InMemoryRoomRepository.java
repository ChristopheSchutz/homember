package com.hypesofts.homember.application.room.core;

import com.hypesofts.homember.application.room.api.RoomCreation;
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
    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public Room create(RoomCreation roomCreation) {
        Room room = new Room(RoomId.create(), roomCreation.name());
        rooms.add(room);
        return room;
    }

    @Override
    public void delete(RoomId roomId) {
        rooms.removeIf(room -> room.getId().equals(roomId));
    }
}
