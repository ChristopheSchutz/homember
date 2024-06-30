package com.hypesofts.homember.application.cabinet.api;

import com.hypesofts.homember.application.cabinet.usecase.CRUDCabinetUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/cabinets")
@ResponseBody
public class CabinetAPI {

    private CRUDCabinetUseCase crudCabinetUseCase;

    @Autowired
    public CabinetAPI(CRUDCabinetUseCase crudCabinetUseCase) {
        this.crudCabinetUseCase = crudCabinetUseCase;
    }

    @PostMapping
    public CabinetRepresentation create(@RequestBody CabinetCreation cabinetCreation) {
        return crudCabinetUseCase.create(cabinetCreation);
    }

    @DeleteMapping("/{cabinetId}")
    public void delete(@PathVariable UUID cabinetId) {
        crudCabinetUseCase.delete(cabinetId);
    }
}
