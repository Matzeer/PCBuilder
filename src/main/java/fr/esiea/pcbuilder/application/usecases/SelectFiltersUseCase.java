package fr.esiea.pcbuilder.application.usecases;

import fr.esiea.pcbuilder.application.dto.FiltersDTO;
import fr.esiea.pcbuilder.domain.entities.UserParams;
import fr.esiea.pcbuilder.shared.enums.Categories;

import java.util.EnumSet;

public class SelectFiltersUseCase {
    private static final int DEFAULT_LIMIT = 10;
    private static final Categories DEFAULT_CATEGORY = Categories.CPU;

    private UserParams userParams;

    public SelectFiltersUseCase() {
        this.userParams = new UserParams(DEFAULT_CATEGORY, DEFAULT_LIMIT);
    }

    public FiltersDTO execute(FiltersDTO request) {
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

        userParams.setActualCategory(request.category());
        userParams.setOrders(request.orders());
        userParams.setLimit(request.limit());

        return new FiltersDTO(
                userParams.getActualCategory(),
                userParams.getOrders(),
                userParams.getLimit()
        );
    }

}
