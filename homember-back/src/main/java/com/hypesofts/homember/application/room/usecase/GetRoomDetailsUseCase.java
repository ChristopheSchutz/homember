package com.hypesofts.homember.application.room.usecase;

import com.hypesofts.homember.application.cabinet.api.CabinetRepresentation;
import com.hypesofts.homember.application.cabinet.core.CabinetRepository;
import com.hypesofts.homember.application.room.api.RoomRepresentation;
import com.hypesofts.homember.application.room.core.RoomRepository;
import com.hypesofts.homember.application.room.core.projections.RoomDetails;
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
