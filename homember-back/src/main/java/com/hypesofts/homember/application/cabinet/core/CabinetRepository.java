package com.hypesofts.homember.application.cabinet.core;

import com.hypesofts.homember.application.room.core.RoomId;

import java.util.List;

public interface CabinetRepository {

    Cabinet create(Cabinet cabinet);

    void delete(CabinetId cabinetId);

    List<Cabinet> getCabinetsByRoomId(RoomId roomId);
}
