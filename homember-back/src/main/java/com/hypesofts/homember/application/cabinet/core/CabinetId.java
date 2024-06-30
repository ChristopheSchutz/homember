package com.hypesofts.homember.application.cabinet.core;

import com.hypesofts.homember.application.technical.baseclass.FetchableDomainId;
import lombok.NonNull;

import java.util.UUID;

public class CabinetId extends FetchableDomainId<Cabinet> {
    public static CabinetId of(@NonNull UUID id) {
        return new CabinetId(id);
    }

    public static CabinetId create() {
        return new CabinetId(UUID.randomUUID());
    }

    CabinetId(UUID id) {
        super(id);
    }

}
