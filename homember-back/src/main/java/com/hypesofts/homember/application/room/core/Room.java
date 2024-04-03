package com.hypesofts.homember.application.room.core;

import com.hypesofts.homember.application.technical.baseclass.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class Room implements DomainEntity {
    private RoomId id;
    private String name;

    @Override
    public UUID getUUID() {
        return id.getId();
    }
}
