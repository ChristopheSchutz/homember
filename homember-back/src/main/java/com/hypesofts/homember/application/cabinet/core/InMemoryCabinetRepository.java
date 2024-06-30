package com.hypesofts.homember.application.cabinet.core;

import com.hypesofts.homember.application.room.core.RoomId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryCabinetRepository implements CabinetRepository {

    private final List<Cabinet> cabinets;

    public InMemoryCabinetRepository() {
        this.cabinets = new ArrayList<>();
    }

    @Override
    public Cabinet create(Cabinet cabinet) {
        cabinets.add(cabinet);
        return cabinet;
    }

    @Override
    public void delete(CabinetId cabinetId) {
        cabinets.removeIf(cabinet -> cabinet.getId().equals(cabinetId));
    }

    @Override
    public List<Cabinet> getCabinetsByRoomId(RoomId roomId) {
        return cabinets.stream()
                .filter(cabinet -> cabinet.getRoomId().equals(roomId))
                .toList();
    }
}
