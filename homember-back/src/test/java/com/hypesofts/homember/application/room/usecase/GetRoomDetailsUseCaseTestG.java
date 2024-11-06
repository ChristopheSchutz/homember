package com.hypesofts.homember.application.room.usecase;

import com.hypesofts.homember.application.cabinet.core.Cabinet;
import com.hypesofts.homember.application.cabinet.core.CabinetId;
import com.hypesofts.homember.application.cabinet.core.CabinetRepository;
import com.hypesofts.homember.application.room.core.Room;
import com.hypesofts.homember.application.room.core.RoomId;
import com.hypesofts.homember.application.room.core.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetRoomDetailsUseCaseTestG {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private CabinetRepository cabinetRepository;

    private GetRoomDetailsUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetRoomDetailsUseCase(roomRepository, cabinetRepository);
    }

    // Step 1: Test with empty room list
    @Test
    void should_return_empty_list_when_no_rooms_exist() {
        // Given
        when(roomRepository.getRooms()).thenReturn(Collections.emptyList());

        // When
        var result = useCase.getRoomDetails();

        // Then
        assertThat(result).isEmpty();
    }

    // Step 2: Test with one room but no cabinets
    @Test
    void should_return_room_details_without_cabinets() {
        // Given
        var roomId = RoomId.of(UUID.randomUUID());
        var room = new Room(roomId, "Bedroom");

        when(roomRepository.getRooms()).thenReturn(List.of(room));
        when(cabinetRepository.getCabinetsByRoomId(roomId)).thenReturn(Collections.emptyList());

        // When
        var result = useCase.getRoomDetails();

        // Then
        assertThat(result).hasSize(1);
        var roomDetails = result.get(0);
        assertThat(roomDetails.room().id()).isEqualTo(roomId.getId());
        assertThat(roomDetails.room().name()).isEqualTo("Bedroom");
        assertThat(roomDetails.cabinets()).isEmpty();
    }

    // Step 3: Test with one room and one cabinet
    @Test
    void should_return_room_details_with_one_cabinet() {
        // Given
        var roomId = RoomId.of(UUID.randomUUID());
        var cabinetId = CabinetId.of(UUID.randomUUID());

        var room = new Room(roomId, "Bedroom");
        var cabinet = new Cabinet(cabinetId, "Wardrobe", roomId);

        when(roomRepository.getRooms()).thenReturn(List.of(room));
        when(cabinetRepository.getCabinetsByRoomId(roomId)).thenReturn(List.of(cabinet));

        // When
        var result = useCase.getRoomDetails();

        // Then
        assertThat(result).hasSize(1);
        var roomDetails = result.get(0);
        assertThat(roomDetails.room().id()).isEqualTo(roomId.getId());
        assertThat(roomDetails.room().name()).isEqualTo("Bedroom");
        assertThat(roomDetails.cabinets()).hasSize(1);
        assertThat(roomDetails.cabinets().get(0).id()).isEqualTo(cabinetId.getId());
        assertThat(roomDetails.cabinets().get(0).name()).isEqualTo("Wardrobe");
    }

    // Step 4: Test with multiple rooms and cabinets
    @Test
    void should_return_multiple_room_details_with_their_cabinets() {
        // Given
        var bedroomId = RoomId.of(UUID.randomUUID());
        var kitchenId = RoomId.of(UUID.randomUUID());
        var wardrobeId = CabinetId.of(UUID.randomUUID());
        var cupboardId = CabinetId.of(UUID.randomUUID());

        var bedroom = new Room(bedroomId, "Bedroom");
        var kitchen = new Room(kitchenId, "Kitchen");
        var wardrobe = new Cabinet(wardrobeId, "Wardrobe", bedroomId);
        var cupboard = new Cabinet(cupboardId, "Cupboard", kitchenId);

        when(roomRepository.getRooms()).thenReturn(List.of(bedroom, kitchen));
        when(cabinetRepository.getCabinetsByRoomId(bedroomId)).thenReturn(List.of(wardrobe));
        when(cabinetRepository.getCabinetsByRoomId(kitchenId)).thenReturn(List.of(cupboard));

        // When
        var result = useCase.getRoomDetails();

        // Then
        assertThat(result).hasSize(2);

        var bedroomDetails = result.get(0);
        assertThat(bedroomDetails.room().id()).isEqualTo(bedroomId.getId());
        assertThat(bedroomDetails.room().name()).isEqualTo("Bedroom");
        assertThat(bedroomDetails.cabinets()).hasSize(1);
        assertThat(bedroomDetails.cabinets().get(0).id()).isEqualTo(wardrobeId.getId());
        assertThat(bedroomDetails.cabinets().get(0).name()).isEqualTo("Wardrobe");

        var kitchenDetails = result.get(1);
        assertThat(kitchenDetails.room().id()).isEqualTo(kitchenId.getId());
        assertThat(kitchenDetails.room().name()).isEqualTo("Kitchen");
        assertThat(kitchenDetails.cabinets()).hasSize(1);
        assertThat(kitchenDetails.cabinets().get(0).id()).isEqualTo(cupboardId.getId());
        assertThat(kitchenDetails.cabinets().get(0).name()).isEqualTo("Cupboard");
    }
}