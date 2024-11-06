package com.hypesofts.homember.application.room.usecase;

import com.hypesofts.homember.application.room.api.RoomCreation;
import com.hypesofts.homember.application.room.core.Room;
import com.hypesofts.homember.application.room.core.RoomId;
import com.hypesofts.homember.application.room.core.RoomRepository;
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
class CRUDRoomUseCaseTestG {

    @Mock
    private RoomRepository roomRepository;

    private CRUDRoomUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new CRUDRoomUseCase(roomRepository);
    }

    // Step 1: Get empty room list
    @Test
    void should_return_empty_list_when_no_rooms_exist() {
        // Given
        when(roomRepository.getRooms()).thenReturn(Collections.emptyList());

        // When
        var result = useCase.getRooms();

        // Then
        assertThat(result).isEmpty();
    }

    // Step 2: Get list with one room
    @Test
    void should_return_list_with_one_room() {
        // Given
        var roomId = RoomId.of(UUID.randomUUID());
        var room = new Room(roomId, "Bedroom");
        when(roomRepository.getRooms()).thenReturn(List.of(room));

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
        var createdRoom = new Room(RoomId.create(), "Living Room");
        when(roomRepository.create(any(Room.class))).thenReturn(createdRoom);

        // When
        var result = useCase.create(roomCreation);

        // Then
        var roomCaptor = ArgumentCaptor.forClass(Room.class);
        verify(roomRepository).create(roomCaptor.capture());

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
        var roomIdCaptor = ArgumentCaptor.forClass(RoomId.class);
        verify(roomRepository).delete(roomIdCaptor.capture());
        assertThat(roomIdCaptor.getValue().getId()).isEqualTo(roomId);
    }

    // Step 5: Get multiple rooms
    @Test
    void should_return_multiple_rooms() {
        // Given
        var bedroomId = RoomId.of(UUID.randomUUID());
        var kitchenId = RoomId.of(UUID.randomUUID());
        var bedroom = new Room(bedroomId, "Bedroom");
        var kitchen = new Room(kitchenId, "Kitchen");

        when(roomRepository.getRooms()).thenReturn(List.of(bedroom, kitchen));

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