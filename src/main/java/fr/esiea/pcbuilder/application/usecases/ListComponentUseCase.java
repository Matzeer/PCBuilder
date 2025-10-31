package fr.esiea.pcbuilder.application.usecases;

import fr.esiea.pcbuilder.application.repositories.ComponentGateway;
import fr.esiea.pcbuilder.domain.entities.Component;
import fr.esiea.pcbuilder.shared.enums.Categories;
import fr.esiea.pcbuilder.shared.enums.QueryParams;

import java.util.ArrayList;

public class ListComponentUseCase {

    private final ComponentGateway componentGateway;

    public ListComponentUseCase(ComponentGateway gateway) {
        this.componentGateway = gateway;
    }

    public ArrayList<Component> execute(Categories category,
                                        ArrayList<QueryParams> orders,
                                        int limit) {
        if(category == null || limit < 0) {
            throw new IllegalArgumentException("Paramètres invalides pour la liste des composants.");
        }
        try {
            return componentGateway.getComponentListFilteredOrdered(category, orders, limit);
        } catch (Exception e) {
            throw new RuntimeException("Erreur de lecture des composants : " + e.getMessage(), e);
        }
    }

}
