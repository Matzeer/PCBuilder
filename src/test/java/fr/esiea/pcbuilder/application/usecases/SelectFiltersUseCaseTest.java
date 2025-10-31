package fr.esiea.pcbuilder.application.usecases;

import fr.esiea.pcbuilder.application.dto.FiltersDTO;
import fr.esiea.pcbuilder.shared.enums.Categories;
import fr.esiea.pcbuilder.shared.enums.QueryParams;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SelectFiltersUseCaseTest {

    @Test
    void validRequestUpdatesUserParamsAndReturnsSameValues() {
        // Arrange
        SelectFiltersUseCase useCase = new SelectFiltersUseCase();
        ArrayList<QueryParams> orders = new ArrayList<>(List.of(QueryParams.PRICE, QueryParams.NAME));
        FiltersDTO request = new FiltersDTO(Categories.VIDEO_CARD, orders, 5);

        // Act
        FiltersDTO result = useCase.execute(request);

        // Assert
        assertEquals(Categories.VIDEO_CARD, result.category());
        assertEquals(orders, result.orders());
        assertEquals(5, result.limit());
    }

    @Test
    void emptyOrdersListIsAccepted() {
        // Arrange
        SelectFiltersUseCase useCase = new SelectFiltersUseCase();
        ArrayList<QueryParams> emptyOrders = new ArrayList<>();
        FiltersDTO request = new FiltersDTO(Categories.CPU, emptyOrders, 10);

        // Act
        FiltersDTO result = useCase.execute(request);

        // Assert
        assertEquals(Categories.CPU, result.category());
        assertTrue(result.orders().isEmpty());
        assertEquals(10, result.limit());
    }

    @Test
    void zeroLimitThrowsException() {
        // Arrange
        SelectFiltersUseCase useCase = new SelectFiltersUseCase();
        ArrayList<QueryParams> orders = new ArrayList<>(List.of(QueryParams.PRICE));
        FiltersDTO request = new FiltersDTO(Categories.CPU, orders, 0);

        // Act + Assert
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                useCase.execute(request)
        );
        assertTrue(e.getMessage().contains("Limit must be greater than 0"));
    }

    @Test
    void negativeLimitThrowsException() {
        // Arrange
        SelectFiltersUseCase useCase = new SelectFiltersUseCase();
        ArrayList<QueryParams> orders = new ArrayList<>(List.of(QueryParams.PRICE));
        FiltersDTO request = new FiltersDTO(Categories.CPU, orders, -5);

        // Act + Assert
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                useCase.execute(request)
        );
        assertTrue(e.getMessage().contains("Limit must be greater than 0"));
    }

    @Test
    void nullCategoryThrowsException() {
        // Arrange
        SelectFiltersUseCase useCase = new SelectFiltersUseCase();
        ArrayList<QueryParams> orders = new ArrayList<>(List.of(QueryParams.PRICE));
        FiltersDTO request = new FiltersDTO(null, orders, 5);

        // Act + Assert
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                useCase.execute(request)
        );
        assertTrue(e.getMessage().contains("Category cannot be null"));
    }

    @Test
    void nullOrdersThrowsException() {
        // Arrange
        SelectFiltersUseCase useCase = new SelectFiltersUseCase();
        FiltersDTO request = new FiltersDTO(Categories.CPU, null, 5);

        // Act + Assert
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                useCase.execute(request)
        );
        assertTrue(e.getMessage().contains("Orders cannot be null"));
    }

    @Test
    void userParamsIsUpdatedAfterValidCall() {
        // Arrange
        SelectFiltersUseCase useCase = new SelectFiltersUseCase();
        ArrayList<QueryParams> orders = new ArrayList<>(List.of(QueryParams.NAME));
        FiltersDTO request = new FiltersDTO(Categories.MEMORY, orders, 3);

        // Act
        FiltersDTO result = useCase.execute(request);

        // Assert
        assertEquals(Categories.MEMORY, result.category());
        assertEquals(orders, result.orders());
        assertEquals(3, result.limit());
    }
}
