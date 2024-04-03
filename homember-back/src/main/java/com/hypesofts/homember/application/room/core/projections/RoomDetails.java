package com.hypesofts.homember.application.room.core.projections;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.hypesofts.homember.application.cabinet.api.CabinetRepresentation;
import com.hypesofts.homember.application.room.api.RoomRepresentation;

import java.util.List;

public record RoomDetails(@JsonUnwrapped RoomRepresentation room, List<CabinetRepresentation> cabinets) {

}
