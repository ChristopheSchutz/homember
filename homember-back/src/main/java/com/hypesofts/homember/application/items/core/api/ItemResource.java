package com.hypesofts.homember.application.items.core.api;


import com.hypesofts.homember.application.items.core.ItemEntity;

import java.util.UUID;

public record ItemResource(UUID id, String name, UUID placeId) {

    public ItemResource(ItemEntity item) {
        this(item.getId().getId(), item.getName(), item.getPlaceId().getId());
    }
}
