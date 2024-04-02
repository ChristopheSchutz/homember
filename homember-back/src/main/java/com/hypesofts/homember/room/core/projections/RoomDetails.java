package com.hypesofts.homember.room.core.projections;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.hypesofts.homember.cabinet.api.CabinetRepresentation;
import com.hypesofts.homember.room.api.RoomRepresentation;

import java.util.List;

public record RoomDetails(@JsonUnwrapped RoomRepresentation room, List<CabinetRepresentation> cabinets) {

}
