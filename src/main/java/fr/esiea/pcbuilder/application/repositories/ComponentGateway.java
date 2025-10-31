package fr.esiea.pcbuilder.application.repositories;

import fr.esiea.pcbuilder.application.dto.ComponentDTO;
import fr.esiea.pcbuilder.application.dto.FiltersDTO;
import fr.esiea.pcbuilder.domain.entities.Component;
import fr.esiea.pcbuilder.shared.enums.Categories;
import fr.esiea.pcbuilder.shared.enums.QueryParams;

import java.util.ArrayList;

public interface ComponentGateway {
    public ArrayList<ComponentDTO> getComponentListFilteredOrdered(FiltersDTO filtersDTO);

}
