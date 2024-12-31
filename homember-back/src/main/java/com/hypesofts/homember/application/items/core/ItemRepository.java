package com.hypesofts.homember.application.items.core;

import com.hypesofts.homember.application.place.core.PlaceId;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    ItemEntity create(ItemEntity place);

    void delete(ItemId itemId);

    List<ItemEntity> getAll();

    Optional<ItemEntity> findByNameAndPlace(String value, PlaceId placeId);
}
