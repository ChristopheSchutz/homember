package com.hypesofts.homember.application.place.core;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryPlaceRepository implements PlaceRepository {

    private final List<Place> places;

    public InMemoryPlaceRepository() {
        this.places = new ArrayList<>(
                List.of(
                        new Place(PlaceId.create(), "Salon"),
                        new Place(PlaceId.create(), "armoire de la cave"),
                        new Place(PlaceId.create(), "tiroir de la commode"))
        );
    }

    @Override
    public Place create(Place place) {
        places.add(place);
        return place;
    }

    @Override
    public void delete(PlaceId placeId) {
        places.removeIf(room -> room.getId().equals(placeId));
    }

    @Override
    public List<Place> getPlaces() {
        return places;
    }
}
