package fr.esiea.pcbuilder.application.usecases;

import fr.esiea.pcbuilder.application.exporters.ComputerExporter;
import fr.esiea.pcbuilder.application.repositories.ComputerGateway;

public class ExportComputersUseCase {

    private final ComputerGateway repository;

    public ExportComputersUseCase(ComputerGateway repository) {
        this.repository = repository;
    }

    public void execute(ComputerExporter exporter, String filePath) {
        exporter.export(repository.getComputers(), filePath);
    }
}
