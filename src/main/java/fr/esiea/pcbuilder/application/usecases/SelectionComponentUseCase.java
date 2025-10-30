package fr.esiea.pcbuilder.application.usecases;

import fr.esiea.pcbuilder.domain.entities.*;

public class SelectionComponentUseCase {

    private final Computer computer;

    public SelectionComponentUseCase(){
        this.computer = new Computer();
    }

    public void execute(Component component) {
        switch (component.getCategory()) {
            case CPU -> computer.setCpu((Cpu) component);
            case MOTHERBOARD -> computer.setMotherBoard((MotherBoard)  component);
            case VIDEO_CARD -> computer.setGpu((Gpu)component);
            case MEMORY ->  computer.setRam((Ram) component);
            case INTERNAL_HARD_DRIVE -> computer.setStorage((Storage) component);
            case POWER_SUPPLY -> computer.setPowerSupply((PowerSupply) component);
            case CASE -> computer.setDesktopCase((Case) component);
            default -> throw new IllegalArgumentException("Catégorie de composant non prise en charge : " + component.getCategory());
        }
    }
}
