package fr.esiea.pcbuilder.application.exporters;

import fr.esiea.pcbuilder.domain.entities.Computer;
import java.util.List;

public interface ComputerExporter {
    void export(List<Computer> computers, String filePath);
}
