package com.hypesofts.homember.application.place.core;

import com.hypesofts.homember.infrastructure.framework.baseclass.FetchableDomainId;
import lombok.NonNull;

import java.util.UUID;

public class PlaceId extends FetchableDomainId<PlaceEntity> {
    public PlaceId(UUID id) {
        super(id);
    }

    public static PlaceId of(@NonNull UUID id) {
        return new PlaceId(id);
    }

    public static PlaceId create() {
        return new PlaceId(UUID.randomUUID());
    }

}
