package com.hypesofts.homember.application.room.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hypesofts.homember.application.room.usecase.CRUDPlaceUseCase;
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

@WebMvcTest(RoomAPI.class)
class RoomAPITest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CRUDPlaceUseCase crudPlaceUseCase;

    // Step 1: GET empty list of rooms
    @Test
    void should_return_empty_list_when_no_rooms_exist() throws Exception {
        // Given
        when(crudPlaceUseCase.getRooms()).thenReturn(Collections.emptyList());

        // When/Then
        mockMvc.perform(get("/api/rooms"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    // Step 2: GET list with rooms
    @Test
    void should_return_list_of_rooms() throws Exception {
        // Given
        var roomId = UUID.randomUUID();
        var roomRepresentation = new RoomRepresentation(roomId, "Bedroom");
        when(crudPlaceUseCase.getRooms()).thenReturn(List.of(roomRepresentation));

        // When/Then
        mockMvc.perform(get("/api/rooms"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [{
                            "id": "%s",
                            "name": "Bedroom"
                        }]
                        """.formatted(roomId)));
    }

    // Step 3: POST create room
    @Test
    void should_create_new_room() throws Exception {
        // Given
        var roomId = UUID.randomUUID();
        var roomCreation = new RoomCreation("Living Room");
        var createdRoom = new RoomRepresentation(roomId, "Living Room");

        when(crudPlaceUseCase.create(any(RoomCreation.class))).thenReturn(createdRoom);

        // When/Then
        mockMvc.perform(post("/api/rooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(roomCreation)))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": "%s",
                            "name": "Living Room"
                        }
                        """.formatted(roomId)));
    }

    // Step 5: DELETE room
    @Test
    void should_delete_existing_room() throws Exception {
        // Given
        var roomId = UUID.randomUUID();

        // When/Then
        mockMvc.perform(delete("/api/rooms/{roomId}", roomId))
                .andExpect(status().isOk());

        verify(crudPlaceUseCase).delete(roomId);
    }

    // Step 6: GET with multiple rooms
    @Test
    void should_return_multiple_rooms() throws Exception {
        // Given
        var bedroomId = UUID.randomUUID();
        var kitchenId = UUID.randomUUID();
        var rooms = List.of(
                new RoomRepresentation(bedroomId, "Bedroom"),
                new RoomRepresentation(kitchenId, "Kitchen")
        );
        when(crudPlaceUseCase.getRooms()).thenReturn(rooms);

        // When/Then
        mockMvc.perform(get("/api/rooms"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [{
                            "id": "%s",
                            "name": "Bedroom"
                        }, {
                            "id": "%s",
                            "name": "Kitchen"
                        }]
                        """.formatted(bedroomId, kitchenId)));
    }

    // Step 7: POST with missing content type
    @Test
    void should_reject_request_with_missing_content_type() throws Exception {
        // Given
        var roomCreation = new RoomCreation("Living Room");

        // When/Then
        mockMvc.perform(post("/api/rooms")
                        .content(objectMapper.writeValueAsString(roomCreation)))
                .andExpect(status().isUnsupportedMediaType());
    }

    // Step 8: DELETE with invalid UUID format
    @Test
    void should_reject_delete_with_invalid_uuid() throws Exception {
        // When/Then
        mockMvc.perform(delete("/api/rooms/invalid-uuid"))
                .andExpect(status().isBadRequest());
    }
}