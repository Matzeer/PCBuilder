package fr.esiea.pcbuilder.application.usecases;

import fr.esiea.pcbuilder.application.dto.FiltersDTO;
import fr.esiea.pcbuilder.domain.entities.UserParams;
import fr.esiea.pcbuilder.shared.enums.Categories;
import fr.esiea.pcbuilder.shared.enums.QueryParams;

import java.util.ArrayList;
import java.util.EnumSet;

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
            throw new IllegalArgumentException("Limite ne peut être inferieur à 0");
        }

        if (request.category() == null) {
            throw new IllegalArgumentException("Catégorie ne peut être null");
        }
        if (!EnumSet.allOf(Categories.class).contains(request.category())) {
            throw new IllegalArgumentException("Catégorie invalide: " + request.category());
        }

        if (request.orders() == null) {
            throw new IllegalArgumentException("Ordre ne peut être null");
        }
        for (var order : request.orders()) {
            if (!request.category().allows(order)) {
                throw new IllegalArgumentException("Ordre invalide " + order + " pour " + request.category());
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
