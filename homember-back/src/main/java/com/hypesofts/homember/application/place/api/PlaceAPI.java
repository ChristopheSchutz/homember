package com.hypesofts.homember.application.place.api;

import com.hypesofts.homember.application.place.usecase.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/places")
@ResponseBody
public class PlaceAPI {

    private final PlaceService placeService;

    @Autowired
    public PlaceAPI(PlaceService placeService) {
        this.placeService = placeService;
    }


    @GetMapping
    public List<PlaceResource> getPlaces() {
        return placeService.getPlaces();
    }

    @PostMapping
    public PlaceResource create(@RequestBody PlaceCreation placeCreation) {
        return placeService.create(placeCreation);
    }

    @DeleteMapping("/{placeId}")
    public void delete(@PathVariable UUID placeId) {
        placeService.delete(placeId);
    }
}
