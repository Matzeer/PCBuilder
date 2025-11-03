package fr.esiea.pcbuilder.application.usecases;

import fr.esiea.pcbuilder.application.dto.ComputerDTO;
import fr.esiea.pcbuilder.application.mappers.ComputerMapper;
import fr.esiea.pcbuilder.domain.entities.Computer;

public class CreateComputerUseCase {
    public CreateComputerUseCase() {
    }

    public ComputerDTO execute(AddComputerUseCase addComputerUseCase) {
        Computer computer = new Computer();
        addComputerUseCase.execute(computer);
        return ComputerMapper.toDto(computer);
    }

}
