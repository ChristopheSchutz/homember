package com.hypesofts.homember.application.room.usecase;

import com.hypesofts.homember.application.room.api.RoomCreation;
import com.hypesofts.homember.application.room.api.RoomRepresentation;
import com.hypesofts.homember.application.room.core.Place;
import com.hypesofts.homember.application.room.core.PlaceId;
import com.hypesofts.homember.application.room.core.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CRUDPlaceUseCase {

    private final PlaceRepository placeRepository;

    @Autowired
    public CRUDPlaceUseCase(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<RoomRepresentation> getRooms() {
        return placeRepository.getRooms().stream()
                .map(RoomRepresentation::new)
                .toList();
    }

    public RoomRepresentation create(RoomCreation roomCreation) {
        Place place = new Place(PlaceId.create(), roomCreation.name());
        return new RoomRepresentation(placeRepository.create(place));
    }

    public void delete(UUID roomId) {
        placeRepository.delete(PlaceId.of(roomId));
    }
}
