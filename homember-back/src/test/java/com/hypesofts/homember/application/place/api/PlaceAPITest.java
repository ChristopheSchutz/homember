package com.hypesofts.homember.application.place.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hypesofts.homember.application.place.usecase.CRUDPlaceUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlaceAPI.class)
class PlaceAPITest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CRUDPlaceUseCase crudPlaceUseCase;

    // Step 1: GET empty list of places
    @Test
    void should_return_empty_list_when_no_places_exist() throws Exception {
        // Given
        when(crudPlaceUseCase.getPlaces()).thenReturn(Collections.emptyList());

        // When/Then
        mockMvc.perform(get("/api/places"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    // Step 2: GET list with places
    @Test
    void should_return_list_of_places() throws Exception {
        // Given
        var placeId = UUID.randomUUID();
        var placeRepresentation = new PlaceRepresentation(placeId, "Bedplace");
        when(crudPlaceUseCase.getPlaces()).thenReturn(List.of(placeRepresentation));

        // When/Then
        mockMvc.perform(get("/api/places"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [{
                            "id": "%s",
                            "name": "Bedplace"
                        }]
                        """.formatted(placeId)));
    }

    // Step 3: POST create place
    @Test
    void should_create_new_place() throws Exception {
        // Given
        var placeId = UUID.randomUUID();
        var placeCreation = new PlaceCreation("Living Place");
        var createdPlace = new PlaceRepresentation(placeId, "Living Place");

        when(crudPlaceUseCase.create(any(PlaceCreation.class))).thenReturn(createdPlace);

        // When/Then
        mockMvc.perform(post("/api/places")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(placeCreation)))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": "%s",
                            "name": "Living Place"
                        }
                        """.formatted(placeId)));
    }

    // Step 5: DELETE place
    @Test
    void should_delete_existing_place() throws Exception {
        // Given
        var placeId = UUID.randomUUID();

        // When/Then
        mockMvc.perform(delete("/api/places/{placeId}", placeId))
                .andExpect(status().isOk());

        verify(crudPlaceUseCase).delete(placeId);
    }

    // Step 6: GET with multiple places
    @Test
    void should_return_multiple_places() throws Exception {
        // Given
        var bedplaceId = UUID.randomUUID();
        var kitchenId = UUID.randomUUID();
        var places = List.of(
                new PlaceRepresentation(bedplaceId, "Bedplace"),
                new PlaceRepresentation(kitchenId, "Kitchen")
        );
        when(crudPlaceUseCase.getPlaces()).thenReturn(places);

        // When/Then
        mockMvc.perform(get("/api/places"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [{
                            "id": "%s",
                            "name": "Bedplace"
                        }, {
                            "id": "%s",
                            "name": "Kitchen"
                        }]
                        """.formatted(bedplaceId, kitchenId)));
    }

    // Step 7: POST with missing content type
    @Test
    void should_reject_request_with_missing_content_type() throws Exception {
        // Given
        var placeCreation = new PlaceCreation("Living Place");

        // When/Then
        mockMvc.perform(post("/api/places")
                        .content(objectMapper.writeValueAsString(placeCreation)))
                .andExpect(status().isUnsupportedMediaType());
    }

    // Step 8: DELETE with invalid UUID format
    @Test
    void should_reject_delete_with_invalid_uuid() throws Exception {
        // When/Then
        mockMvc.perform(delete("/api/places/invalid-uuid"))
                .andExpect(status().isBadRequest());
    }
}