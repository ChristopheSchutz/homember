package com.hypesofts.homember.application.items.core;

import com.hypesofts.homember.infrastructure.framework.baseclass.FetchableDomainId;
import lombok.NonNull;

import java.util.UUID;

public class ItemId extends FetchableDomainId<ItemEntity> {
    public ItemId(UUID id) {
        super(id);
    }

    public static ItemId of(@NonNull UUID id) {
        return new ItemId(id);
    }

    public static ItemId create() {
        return new ItemId(UUID.randomUUID());
    }

}
