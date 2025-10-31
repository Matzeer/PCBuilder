package fr.esiea.pcbuilder.application.dto;

import fr.esiea.pcbuilder.shared.enums.Categories;

public record PowerSupplyDTO(int id, String name, double price, double grade,
                             String efficiency, int wattage, String modular, String color)
        implements ComponentDTO {

    @Override
    public Categories category() {
        return Categories.POWER_SUPPLY;
    }
}
