package com.hypesofts.homember.application.cabinet.usecase;

import com.hypesofts.homember.application.cabinet.api.CabinetCreation;
import com.hypesofts.homember.application.cabinet.api.CabinetRepresentation;
import com.hypesofts.homember.application.cabinet.core.Cabinet;
import com.hypesofts.homember.application.cabinet.core.CabinetId;
import com.hypesofts.homember.application.cabinet.core.CabinetRepository;
import com.hypesofts.homember.application.room.core.RoomId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CRUDCabinetUseCase {

    @Autowired
    public CRUDCabinetUseCase(CabinetRepository cabinetRepository) {
        this.cabinetRepository = cabinetRepository;
    }

    private final CabinetRepository cabinetRepository;

    public CabinetRepresentation create(CabinetCreation cabinetCreation) {
        Cabinet cabinet = new Cabinet(CabinetId.create(), cabinetCreation.name(), RoomId.of(cabinetCreation.roomId()));
        return new CabinetRepresentation(cabinetRepository.create(cabinet));
    }

    public void delete(UUID cabinetId) {
        cabinetRepository.delete(CabinetId.of(cabinetId));
    }
}
