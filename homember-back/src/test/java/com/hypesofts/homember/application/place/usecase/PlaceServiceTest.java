package com.hypesofts.homember.application.place.usecase;

import com.hypesofts.homember.application.place.api.PlaceCreation;
import com.hypesofts.homember.application.place.core.PlaceEntity;
import com.hypesofts.homember.application.place.core.PlaceId;
import com.hypesofts.homember.application.place.core.PlaceRepository;
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
class PlaceServiceTest {

    @Mock
    private PlaceRepository placeRepository;

    private PlaceService useCase;

    @BeforeEach
    void setUp() {
        useCase = new PlaceService(placeRepository);
    }

    // Step 1: Get empty place list
    @Test
    void should_return_empty_list_when_no_places_exist() {
        // Given
        when(placeRepository.getPlaces()).thenReturn(Collections.emptyList());

        // When
        var result = useCase.getPlaces();

        // Then
        assertThat(result).isEmpty();
    }

    // Step 2: Get list with one place
    @Test
    void should_return_list_with_one_place() {
        // Given
        var placeId = PlaceId.of(UUID.randomUUID());
        var place = new PlaceEntity(placeId, "Bedplace");
        when(placeRepository.getPlaces()).thenReturn(List.of(place));

        // When
        var result = useCase.getPlaces();

        // Then
        assertThat(result).hasSize(1);
        var placeRepresentation = result.get(0);
        assertThat(placeRepresentation.id()).isEqualTo(placeId.getId());
        assertThat(placeRepresentation.name()).isEqualTo("Bedplace");
    }

    // Step 3: Create place
    @Test
    void should_create_new_place() {
        // Given
        var placeCreation = new PlaceCreation("Living Place");
        var createdPlace = new PlaceEntity(PlaceId.create(), "Living Place");
        when(placeRepository.create(any(PlaceEntity.class))).thenReturn(createdPlace);

        // When
        var result = useCase.create(placeCreation);

        // Then
        var placeCaptor = ArgumentCaptor.forClass(PlaceEntity.class);
        verify(placeRepository).create(placeCaptor.capture());

        var capturedPlace = placeCaptor.getValue();
        assertThat(capturedPlace.getName()).isEqualTo("Living Place");
        assertThat(capturedPlace.getId()).isNotNull();

        assertThat(result.name()).isEqualTo("Living Place");
        assertThat(result.id()).isEqualTo(createdPlace.getId().getId());
    }

    // Step 4: Delete place
    @Test
    void should_delete_existing_place() {
        // Given
        var placeId = UUID.randomUUID();

        // When
        useCase.delete(placeId);

        // Then
        var placeIdCaptor = ArgumentCaptor.forClass(PlaceId.class);
        verify(placeRepository).delete(placeIdCaptor.capture());
        assertThat(placeIdCaptor.getValue().getId()).isEqualTo(placeId);
    }

    // Step 5: Get multiple places
    @Test
    void should_return_multiple_places() {
        // Given
        var bedplaceId = PlaceId.of(UUID.randomUUID());
        var kitchenId = PlaceId.of(UUID.randomUUID());
        var bedplace = new PlaceEntity(bedplaceId, "Bedplace");
        var kitchen = new PlaceEntity(kitchenId, "Kitchen");

        when(placeRepository.getPlaces()).thenReturn(List.of(bedplace, kitchen));

        // When
        var result = useCase.getPlaces();

        // Then
        assertThat(result).hasSize(2);

        assertThat(result.get(0).id()).isEqualTo(bedplaceId.getId());
        assertThat(result.get(0).name()).isEqualTo("Bedplace");

        assertThat(result.get(1).id()).isEqualTo(kitchenId.getId());
        assertThat(result.get(1).name()).isEqualTo("Kitchen");
    }
}