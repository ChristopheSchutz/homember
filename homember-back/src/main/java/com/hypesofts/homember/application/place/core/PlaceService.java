package com.hypesofts.homember.application.place.core;

import com.hypesofts.homember.application.place.api.PlaceCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<PlaceEntity> getPlaces() {
        return placeRepository.getAll();
    }

    public PlaceEntity create(PlaceCreation placeCreation) {
        PlaceEntity place = new PlaceEntity(PlaceId.create(), placeCreation.name());
        return placeRepository.create(place);
    }

    public void delete(UUID placeId) {
        placeRepository.delete(PlaceId.of(placeId));
    }

    public PlaceEntity findByNameOrCreate(String name) {
        return placeRepository.findByName(name)
                .orElseGet(() -> create(new PlaceCreation(name)));
    }
}
