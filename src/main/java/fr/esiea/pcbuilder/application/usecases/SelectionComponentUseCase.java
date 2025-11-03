package fr.esiea.pcbuilder.application.usecases;

import fr.esiea.pcbuilder.application.dto.ComponentDTO;
import fr.esiea.pcbuilder.application.dto.ComputerDTO;
import fr.esiea.pcbuilder.application.mappers.ComponentMapper;
import fr.esiea.pcbuilder.application.mappers.ComputerMapper;
import fr.esiea.pcbuilder.application.repositories.ComputerGateway;
import fr.esiea.pcbuilder.domain.entities.*;

public class SelectionComponentUseCase {
    private final ComputerGateway repository;

    public SelectionComponentUseCase(ComputerGateway repository) {
        this.repository = repository;
    }

    public ComputerDTO execute(ComputerDTO computerDto, ComponentDTO componentDto) {
        Component component = ComponentMapper.toEntity(componentDto);
        Computer computer = ComputerMapper.toEntity(computerDto, repository);
        switch (component.getCategory()) {
            case CPU -> computer.setCpu((Cpu) component);
            case MOTHERBOARD -> computer.setMotherBoard((MotherBoard) component);
            case VIDEO_CARD -> computer.setGpu((Gpu) component);
            case MEMORY -> computer.setRam((Ram) component);
            case INTERNAL_HARD_DRIVE -> computer.setStorage((Storage) component);
            case POWER_SUPPLY -> computer.setPowerSupply((PowerSupply) component);
            case CASE -> computer.setDesktopCase((Case) component);
            default -> throw new IllegalArgumentException("Catégorie non prise en charge : " + component.getCategory());
        }

        return ComputerMapper.toDto(computer);
    }
}
