package com.hypesofts.homember.application.cabinet.core;


import com.hypesofts.homember.application.room.core.RoomId;
import com.hypesofts.homember.application.technical.baseclass.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class Cabinet implements DomainEntity {
    private CabinetId id;
    private String name;
    private RoomId roomId;

    @Override
    public UUID getUUID() {
        return id.getId();
    }
}
