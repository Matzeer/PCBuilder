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

        boolean isFieldNull = computerDto.cpu() == null ||
                computerDto.gpu() == null ||
                computerDto.motherBoard() == null ||
                computerDto.ram() == null ||
                computerDto.storage() == null ||
                computerDto.powerSupply() == null ||
                computerDto.desktopCase() == null;

        if (isFieldNull)
            throw new IllegalStateException("Tous les composants du ComputerDTO doivent être non nuls");

        Computer computer = ComputerMapper.toEntity(computerDto);
        repository.addComputer(computer);
    }
}
