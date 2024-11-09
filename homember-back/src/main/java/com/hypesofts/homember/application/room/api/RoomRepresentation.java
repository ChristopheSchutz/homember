package com.hypesofts.homember.application.room.api;


import com.hypesofts.homember.application.room.core.Place;

import java.util.UUID;

public record RoomRepresentation(UUID id, String name) {

    public RoomRepresentation(Place place) {
        this(place.getId().getId(), place.getName());
    }
}
