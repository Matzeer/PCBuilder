package fr.esiea.pcbuilder.application.dto;

import fr.esiea.pcbuilder.shared.enums.Categories;
import fr.esiea.pcbuilder.shared.enums.QueryParams;

import java.util.ArrayList;

public record FiltersDTO(Categories category, ArrayList<QueryParams> orders, int limit) {
}
