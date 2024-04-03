package com.hypesofts.homember.application.room.usecase;

import com.hypesofts.homember.application.room.api.RoomCreation;
import com.hypesofts.homember.application.room.api.RoomRepresentation;
import com.hypesofts.homember.application.room.core.RoomId;
import com.hypesofts.homember.application.room.core.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CRUDRoomUseCase {

    @Autowired
    public CRUDRoomUseCase(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    private final RoomRepository roomRepository;

    public List<RoomRepresentation> getRooms() {
            return roomRepository.getRooms().stream()
                    .map(RoomRepresentation::new)
                    .collect(Collectors.toList());
    }

    public RoomRepresentation create(RoomCreation roomCreation) {
        return new RoomRepresentation(roomRepository.create(roomCreation));
    }

    public void delete(UUID roomId) {
        roomRepository.delete(RoomId.of(roomId));
    }
}
