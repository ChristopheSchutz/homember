package com.hypesofts.homember.application.place.core;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InMemoryPlaceRepository implements PlaceRepository {

    private final List<PlaceEntity> places;

    public InMemoryPlaceRepository() {
        this.places = new ArrayList<>(
                List.of(
                        new PlaceEntity(PlaceId.create(), "Salon"),
                        new PlaceEntity(PlaceId.create(), "armoire de la cave"),
                        new PlaceEntity(PlaceId.create(), "tiroir de la commode"))
        );
    }

    @Override
    public PlaceEntity create(PlaceEntity place) {
        places.add(place);
        return place;
    }

    @Override
    public void delete(PlaceId placeId) {
        places.removeIf(place -> place.getId().equals(placeId));
    }

    @Override
    public List<PlaceEntity> getAll() {
        return places;
    }

    @Override
    public Optional<PlaceEntity> findByName(String name) {
        return places.stream()
                .filter(place -> place.getName().equalsIgnoreCase(name))
                .findFirst();
    }
}
