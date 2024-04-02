package com.hypesofts.homember.cabinet.api;

import com.hypesofts.homember.cabinet.usecase.CRUDCabinetUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cabinets")
@ResponseBody
public class CabinetAPI {

    @Autowired
    public CabinetAPI(CRUDCabinetUseCase crudCabinetUseCase) {
        this.crudCabinetUseCase = crudCabinetUseCase;
    }

    private CRUDCabinetUseCase crudCabinetUseCase;

    @PostMapping
    public CabinetRepresentation create(@RequestBody CabinetCreation cabinetCreation) {
        return crudCabinetUseCase.create(cabinetCreation);
    }
}
