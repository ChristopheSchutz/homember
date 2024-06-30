package com.hypesofts.homember.application.room.core;

import com.hypesofts.homember.application.technical.baseclass.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Room implements DomainEntity {
    @EqualsAndHashCode.Include
    private RoomId id;
    private String name;

    @Override
    public UUID getUUID() {
        return id.getId();
    }
}
