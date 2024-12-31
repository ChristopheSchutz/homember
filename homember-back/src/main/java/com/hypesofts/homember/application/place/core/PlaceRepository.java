package com.hypesofts.homember.application.place.core;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository {

    PlaceEntity create(PlaceEntity place);

    void delete(PlaceId placeId);

    List<PlaceEntity> getAll();

    Optional<PlaceEntity> findByName(String name);
}
