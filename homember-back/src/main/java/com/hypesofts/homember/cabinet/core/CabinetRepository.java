package com.hypesofts.homember.cabinet.core;

import com.hypesofts.homember.cabinet.api.CabinetCreation;
import com.hypesofts.homember.room.core.RoomId;

import java.util.List;

public interface CabinetRepository {

    Cabinet create(CabinetCreation cabinetCreation);
    List<Cabinet> getCabinetsByRoomId(RoomId roomId);
}
