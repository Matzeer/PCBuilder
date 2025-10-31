package fr.esiea.pcbuilder.application.dto;

import fr.esiea.pcbuilder.shared.enums.Categories;

public record RamDTO(int id, String name, double price, double grade,
                     int speed0, int speed1, int module0, int module1, int pricePerGb,
                     String color, int firstWordLatency, int casLatency)
        implements ComponentDTO {

    @Override
    public Categories category() {
        return Categories.MEMORY;
    }
}
