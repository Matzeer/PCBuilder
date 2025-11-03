package fr.esiea.pcbuilder.application.usecases;

import fr.esiea.pcbuilder.application.dto.ComponentDTO;
import fr.esiea.pcbuilder.application.dto.FiltersDTO;
import fr.esiea.pcbuilder.application.repositories.ComponentGateway;

import java.util.ArrayList;

public class ListComponentUseCase {

    private final ComponentGateway componentGateway;

    public ListComponentUseCase(ComponentGateway gateway) {
        this.componentGateway = gateway;
    }

    public ArrayList<ComponentDTO> execute(FiltersDTO filtersDTO) {
        if (filtersDTO.category() == null || filtersDTO.limit() < 0) {
            throw new IllegalArgumentException("Paramètres invalides pour la liste des composants.");
        }
        try {
            return componentGateway.getComponentListFilteredOrdered(filtersDTO);
        } catch (Exception e) {
            throw new RuntimeException("Erreur de lecture des composants : " + e.getMessage(), e);
        }
    }

}
