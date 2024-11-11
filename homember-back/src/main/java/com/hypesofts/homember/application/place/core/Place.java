package com.hypesofts.homember.application.place.core;

import com.hypesofts.homember.application.framework.baseclass.DomainEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Place implements DomainEntity {
    @EqualsAndHashCode.Include
    private final PlaceId id;
    private final String name;

    public Place(PlaceId id, String name) {
        // assert name isn't null nor empty
        verifyParameters(name);

        this.id = id;
        this.name = name;
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
