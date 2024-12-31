package com.hypesofts.homember.application.items.core;

import com.hypesofts.homember.application.items.core.api.ItemCreation;
import com.hypesofts.homember.application.place.core.PlaceId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemEntity> getAll() {
        return itemRepository.getAll();
    }

    public ItemEntity create(ItemCreation itemCreation) {
        ItemEntity item = new ItemEntity(ItemId.create(), itemCreation.name(), itemCreation.placeId());
        return itemRepository.create(item);
    }

    public void delete(UUID itemId) {
        itemRepository.delete(ItemId.of(itemId));
    }

    public ItemEntity findByNameAndPlaceOrCreate(String name, PlaceId placeId) {
        return itemRepository.findByNameAndPlace(name, placeId)
                .orElseGet(() -> create(new ItemCreation(name, placeId)));
    }
}
