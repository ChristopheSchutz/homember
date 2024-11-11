package com.hypesofts.homember.application.place.core;

import java.util.List;

public interface PlaceRepository {

    Place create(Place place);

    void delete(PlaceId placeId);

    List<Place> getPlaces();
}
