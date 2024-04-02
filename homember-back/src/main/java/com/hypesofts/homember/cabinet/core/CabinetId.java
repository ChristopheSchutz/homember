package com.hypesofts.homember.cabinet.core;

import com.hypesofts.homember.technical.baseclass.FetchableDomainId;
import lombok.NonNull;

import java.util.UUID;

public class CabinetId extends FetchableDomainId<Cabinet> {
    static CabinetId of(@NonNull UUID id) { return new CabinetId(id);}
    public static CabinetId create() { return new CabinetId(UUID.randomUUID());}

    CabinetId(UUID id) {
        super(id);
    }

}
