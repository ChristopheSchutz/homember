package com.hypesofts.homember.application.items.core;

import com.hypesofts.homember.application.place.core.PlaceId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InMemoryItemRepository implements ItemRepository {

    private final List<ItemEntity> items;

    public InMemoryItemRepository() {
        this.items = new ArrayList<>();
    }

    @Override
    public ItemEntity create(ItemEntity item) {
        items.add(item);
        return item;
    }

    @Override
    public void delete(ItemId itemId) {
        items.removeIf(item -> item.getId().equals(itemId));
    }

    @Override
    public List<ItemEntity> getAll() {
        return items;
    }

    @Override
    public Optional<ItemEntity> findByNameAndPlace(String name, PlaceId placeId) {
        return items.stream()
                .filter(item -> item.getName().equalsIgnoreCase(name))
                .filter(item -> item.getPlaceId().equals(placeId))
                .findFirst();
    }
}
