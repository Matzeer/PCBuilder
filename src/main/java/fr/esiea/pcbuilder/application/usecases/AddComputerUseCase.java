package fr.esiea.pcbuilder.application.usecases;

import fr.esiea.pcbuilder.application.dto.ComputerDTO;
import fr.esiea.pcbuilder.application.mappers.ComputerMapper;
import fr.esiea.pcbuilder.application.repositories.ComputerGateway;
import fr.esiea.pcbuilder.domain.entities.Computer;

public class AddComputerUseCase {
    private final ComputerGateway repository;

    public AddComputerUseCase(ComputerGateway repository) {
        this.repository = repository;
    }

    public void execute(ComputerDTO computerDto) {
        if (computerDto == null)
            throw new IllegalArgumentException("ComputerDTO est null");

        Computer computer = ComputerMapper.toEntity(computerDto, repository);
        repository.getComputers().removeIf(c -> c.getId() == computer.getId());
        repository.getComputers().add(computer);
    }

    public void execute(Computer computer) {
        if (computer == null)
            throw new IllegalArgumentException("Computer est null");

        repository.addComputer(computer);
    }
}
