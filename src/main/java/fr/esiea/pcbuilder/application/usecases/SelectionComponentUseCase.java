package fr.esiea.pcbuilder.application.usecases;

import fr.esiea.pcbuilder.application.dto.*;
import fr.esiea.pcbuilder.application.mappers.ComponentMapper;
import fr.esiea.pcbuilder.application.mappers.ComponentMapper;
import fr.esiea.pcbuilder.application.mappers.ComputerMapper;
import fr.esiea.pcbuilder.domain.entities.*;
import fr.esiea.pcbuilder.shared.enums.Categories;

public class SelectionComponentUseCase {

    private final Computer computer;

    public SelectionComponentUseCase(){
        this.computer = new Computer();
    }

    public ComputerDTO execute(ComponentDTO componentDto) {
        Component component = ComponentMapper.toEntity(componentDto);

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
