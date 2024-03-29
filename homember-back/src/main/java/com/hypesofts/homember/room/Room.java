package com.hypesofts.homember.room;

import com.hypesofts.homember.technical.baseclass.DomainEntity;
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
