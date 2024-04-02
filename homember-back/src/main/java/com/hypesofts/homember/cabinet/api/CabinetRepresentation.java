package com.hypesofts.homember.cabinet.api;

import com.hypesofts.homember.cabinet.core.Cabinet;

import java.util.UUID;

public record CabinetRepresentation(UUID id, String name) {

    public CabinetRepresentation(Cabinet cabinet) {
        this(cabinet.getId().getId(), cabinet.getName());
    }
}
