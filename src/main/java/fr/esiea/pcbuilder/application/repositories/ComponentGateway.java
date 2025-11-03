package fr.esiea.pcbuilder.application.repositories;

import fr.esiea.pcbuilder.application.dto.ComponentDTO;
import fr.esiea.pcbuilder.application.dto.FiltersDTO;

import java.util.ArrayList;

public interface ComponentGateway {
    ArrayList<ComponentDTO> getComponentListFilteredOrdered(FiltersDTO filtersDTO);

}
