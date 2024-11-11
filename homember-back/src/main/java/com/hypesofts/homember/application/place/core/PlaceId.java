package com.hypesofts.homember.application.place.core;

import com.hypesofts.homember.application.framework.baseclass.FetchableDomainId;
import lombok.NonNull;

import java.util.UUID;

public class PlaceId extends FetchableDomainId<Place> {
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
