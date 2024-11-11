package com.hypesofts.homember.application.place.api;


import com.hypesofts.homember.application.place.core.Place;

import java.util.UUID;

public record PlaceRepresentation(UUID id, String name) {

    public PlaceRepresentation(Place place) {
        this(place.getId().getId(), place.getName());
    }
}
