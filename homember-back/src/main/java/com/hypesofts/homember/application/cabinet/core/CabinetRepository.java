package com.hypesofts.homember.application.cabinet.core;

import com.hypesofts.homember.application.cabinet.api.CabinetCreation;
import com.hypesofts.homember.application.room.core.RoomId;

import java.util.List;

public interface CabinetRepository {

    Cabinet create(CabinetCreation cabinetCreation);
    List<Cabinet> getCabinetsByRoomId(RoomId roomId);
}
