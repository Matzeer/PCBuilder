package fr.esiea.pcbuilder.application.usecases;

import fr.esiea.pcbuilder.application.dto.ComputerDTO;
import fr.esiea.pcbuilder.application.mappers.ComputerMapper;
import fr.esiea.pcbuilder.application.repositories.ComputerGateway;

import java.util.List;

public class ListComputerUseCase {
    private final ComputerGateway repository;

    public ListComputerUseCase(ComputerGateway repository) {
        this.repository = repository;
    }

    public List<ComputerDTO> execute() {
        return repository.getComputers().stream()
                .map(ComputerMapper::toDto)
                .toList();
    }
}

