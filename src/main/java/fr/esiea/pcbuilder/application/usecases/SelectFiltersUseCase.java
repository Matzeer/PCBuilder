package fr.esiea.pcbuilder.application.usecases;

import fr.esiea.pcbuilder.application.dto.FiltersDTO;
import fr.esiea.pcbuilder.domain.entities.UserParams;
import fr.esiea.pcbuilder.shared.enums.Categories;
import fr.esiea.pcbuilder.shared.enums.QueryParams;

import java.util.EnumSet;
import java.util.ArrayList;

public class SelectFiltersUseCase {
    private static final int DEFAULT_LIMIT = 10;
    private static final Categories DEFAULT_CATEGORY = Categories.CPU;

    public SelectFiltersUseCase() {
    }

    public FiltersDTO execute(FiltersDTO request) {
        if (request == null) {
            return new FiltersDTO(
                    DEFAULT_CATEGORY,
                    new ArrayList<QueryParams>(),
                    DEFAULT_LIMIT
            );
        }
        if (request.limit() <= 0) {
            throw new IllegalArgumentException("Limit must be greater than 0");
        }

        if (request.category() == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        if (!EnumSet.allOf(Categories.class).contains(request.category())) {
            throw new IllegalArgumentException("Invalid category: " + request.category());
        }

        if (request.orders() == null) {
            throw new IllegalArgumentException("Orders cannot be null");
        }
        for (var order : request.orders()) {
            if (!request.category().allows(order)) {
                throw new IllegalArgumentException("Invalid order " + order + " for " + request.category());
            }
        }

        UserParams userParams = new UserParams(request.category(), request.limit());
        userParams.setOrders(request.orders());

        return new FiltersDTO(
                userParams.getActualCategory(),
                userParams.getOrders(),
                userParams.getLimit()
        );
    }

}
