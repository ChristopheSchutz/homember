package com.hypesofts.homember.room.usecase;

import com.hypesofts.homember.cabinet.core.CabinetRepository;
import com.hypesofts.homember.cabinet.api.CabinetRepresentation;
import com.hypesofts.homember.room.core.projections.RoomDetails;
import com.hypesofts.homember.room.core.RoomRepository;
import com.hypesofts.homember.room.api.RoomRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetRoomDetailsUseCase {
    private final RoomRepository roomRepository;
    private final CabinetRepository cabinetRepository;

    @Autowired
    public GetRoomDetailsUseCase(RoomRepository roomRepository, CabinetRepository cabinetRepository) {
        this.roomRepository = roomRepository;
        this.cabinetRepository = cabinetRepository;
    }

    public List<RoomDetails> getRoomDetails() {
        return roomRepository.getRooms().stream()
                .map(room -> new RoomDetails(new RoomRepresentation(room),
                        cabinetRepository.getCabinetsByRoomId(room.getId()).stream()
                                .map(CabinetRepresentation::new)
                                .toList()))
                .toList();
    }
}
