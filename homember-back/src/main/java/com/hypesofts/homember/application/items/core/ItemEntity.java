package com.hypesofts.homember.application.items.core;

import com.hypesofts.homember.application.place.core.PlaceId;
import com.hypesofts.homember.infrastructure.framework.baseclass.DomainEntity;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ItemEntity implements DomainEntity {
    private final ItemId id;
    private final String name;
    private final PlaceId placeId;

    public ItemEntity(ItemId id, String name, PlaceId placeId) {
        verifyParameters(name);

        this.id = id;
        this.name = name;
        this.placeId = placeId;
    }

    private static void verifyParameters(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null nor empty");
        }
    }

    @Override
    public UUID getUUID() {
        return id.getId();
    }
}
