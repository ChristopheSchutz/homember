package com.hypesofts.homember.application.place.usecase;

import com.hypesofts.homember.application.place.api.PlaceCreation;
import com.hypesofts.homember.application.place.api.PlaceResource;
import com.hypesofts.homember.application.place.core.PlaceEntity;
import com.hypesofts.homember.application.place.core.PlaceId;
import com.hypesofts.homember.application.place.core.PlaceRepository;
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

    public List<PlaceResource> getPlaces() {
        return placeRepository.getPlaces().stream()
                .map(PlaceResource::new)
                .toList();
    }

    public PlaceResource create(PlaceCreation placeCreation) {
        PlaceEntity place = new PlaceEntity(PlaceId.create(), placeCreation.name());
        return new PlaceResource(placeRepository.create(place));
    }

    public void delete(UUID placeId) {
        placeRepository.delete(PlaceId.of(placeId));
    }

    public PlaceResource findByNameOrCreate(String name) {
        return placeRepository.findByName(name)
                .map(PlaceResource::new)
                .orElseGet(() -> create(new PlaceCreation(name)));
    }
}
