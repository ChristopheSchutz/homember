package com.hypesofts.homember.application.cabinet.core;


import com.hypesofts.homember.application.room.core.RoomId;
import com.hypesofts.homember.application.technical.baseclass.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cabinet implements DomainEntity {
    @EqualsAndHashCode.Include
    private CabinetId id;
    private String name;
    private RoomId roomId;

    @Override
    public UUID getUUID() {
        return id.getId();
    }
}
