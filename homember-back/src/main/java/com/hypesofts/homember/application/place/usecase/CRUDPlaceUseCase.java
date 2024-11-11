package com.hypesofts.homember.application.place.usecase;

import com.hypesofts.homember.application.place.api.PlaceCreation;
import com.hypesofts.homember.application.place.api.PlaceRepresentation;
import com.hypesofts.homember.application.place.core.Place;
import com.hypesofts.homember.application.place.core.PlaceId;
import com.hypesofts.homember.application.place.core.PlaceRepository;
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

    public List<PlaceRepresentation> getPlaces() {
        return placeRepository.getPlaces().stream()
                .map(PlaceRepresentation::new)
                .toList();
    }

    public PlaceRepresentation create(PlaceCreation placeCreation) {
        Place place = new Place(PlaceId.create(), placeCreation.name());
        return new PlaceRepresentation(placeRepository.create(place));
    }

    public void delete(UUID placeId) {
        placeRepository.delete(PlaceId.of(placeId));
    }
}
