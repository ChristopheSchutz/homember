package com.hypesofts.homember.application.place.api;


import com.hypesofts.homember.application.place.core.PlaceEntity;

import java.util.UUID;

public record PlaceResource(UUID id, String name) {

    public PlaceResource(PlaceEntity place) {
        this(place.getId().getId(), place.getName());
    }
}
