package com.hypesofts.homember.cabinet.usecase;

import com.hypesofts.homember.cabinet.core.CabinetRepository;
import com.hypesofts.homember.cabinet.api.CabinetCreation;
import com.hypesofts.homember.cabinet.api.CabinetRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CRUDCabinetUseCase {

    @Autowired
    public CRUDCabinetUseCase(CabinetRepository cabinetRepository) {
        this.cabinetRepository = cabinetRepository;
    }

    private final CabinetRepository cabinetRepository;

    public CabinetRepresentation create(CabinetCreation cabinetCreation) {
        return new CabinetRepresentation(cabinetRepository.create(cabinetCreation));
    }
}
