package com.hypesofts.homember.application.room.usecase;

import com.hypesofts.homember.application.room.api.RoomCreation;
import com.hypesofts.homember.application.room.core.Place;
import com.hypesofts.homember.application.room.core.PlaceId;
import com.hypesofts.homember.application.room.core.PlaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CRUDPlaceUseCaseTest {

    @Mock
    private PlaceRepository placeRepository;

    private CRUDPlaceUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new CRUDPlaceUseCase(placeRepository);
    }

    // Step 1: Get empty room list
    @Test
    void should_return_empty_list_when_no_rooms_exist() {
        // Given
        when(placeRepository.getRooms()).thenReturn(Collections.emptyList());

        // When
        var result = useCase.getRooms();

        // Then
        assertThat(result).isEmpty();
    }

    // Step 2: Get list with one room
    @Test
    void should_return_list_with_one_room() {
        // Given
        var roomId = PlaceId.of(UUID.randomUUID());
        var room = new Place(roomId, "Bedroom");
        when(placeRepository.getRooms()).thenReturn(List.of(room));

        // When
        var result = useCase.getRooms();

        // Then
        assertThat(result).hasSize(1);
        var roomRepresentation = result.get(0);
        assertThat(roomRepresentation.id()).isEqualTo(roomId.getId());
        assertThat(roomRepresentation.name()).isEqualTo("Bedroom");
    }

    // Step 3: Create room
    @Test
    void should_create_new_room() {
        // Given
        var roomCreation = new RoomCreation("Living Room");
        var createdRoom = new Place(PlaceId.create(), "Living Room");
        when(placeRepository.create(any(Place.class))).thenReturn(createdRoom);

        // When
        var result = useCase.create(roomCreation);

        // Then
        var roomCaptor = ArgumentCaptor.forClass(Place.class);
        verify(placeRepository).create(roomCaptor.capture());

        var capturedRoom = roomCaptor.getValue();
        assertThat(capturedRoom.getName()).isEqualTo("Living Room");
        assertThat(capturedRoom.getId()).isNotNull();

        assertThat(result.name()).isEqualTo("Living Room");
        assertThat(result.id()).isEqualTo(createdRoom.getId().getId());
    }

    // Step 4: Delete room
    @Test
    void should_delete_existing_room() {
        // Given
        var roomId = UUID.randomUUID();

        // When
        useCase.delete(roomId);

        // Then
        var roomIdCaptor = ArgumentCaptor.forClass(PlaceId.class);
        verify(placeRepository).delete(roomIdCaptor.capture());
        assertThat(roomIdCaptor.getValue().getId()).isEqualTo(roomId);
    }

    // Step 5: Get multiple rooms
    @Test
    void should_return_multiple_rooms() {
        // Given
        var bedroomId = PlaceId.of(UUID.randomUUID());
        var kitchenId = PlaceId.of(UUID.randomUUID());
        var bedroom = new Place(bedroomId, "Bedroom");
        var kitchen = new Place(kitchenId, "Kitchen");

        when(placeRepository.getRooms()).thenReturn(List.of(bedroom, kitchen));

        // When
        var result = useCase.getRooms();

        // Then
        assertThat(result).hasSize(2);

        assertThat(result.get(0).id()).isEqualTo(bedroomId.getId());
        assertThat(result.get(0).name()).isEqualTo("Bedroom");

        assertThat(result.get(1).id()).isEqualTo(kitchenId.getId());
        assertThat(result.get(1).name()).isEqualTo("Kitchen");
    }
}