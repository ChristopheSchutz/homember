package com.hypesofts.homember.application.cabinet.api;

import com.hypesofts.homember.application.cabinet.core.Cabinet;

import java.util.UUID;

public record CabinetRepresentation(UUID id, String name) {

    public CabinetRepresentation(Cabinet cabinet) {
        this(cabinet.getId().getId(), cabinet.getName());
    }
}
