package fr.esiea.pcbuilder.application.repositories;

import fr.esiea.pcbuilder.domain.entities.Component;
import fr.esiea.pcbuilder.shared.enums.Categories;
import fr.esiea.pcbuilder.shared.enums.QueryParams;

import java.util.ArrayList;

public interface ComponentGateway {
    public ArrayList<Component> getComponentListFilteredOrdered(Categories category , ArrayList<QueryParams> orders, int limit);

}
