package fr.esiea.pcbuilder.application.dto;

import fr.esiea.pcbuilder.shared.enums.Categories;

public record GpuDTO(int id, String name, double price, double grade,
                     String chipset, int memory, int coreClock,
                     int boostClock, String color, int length)
        implements ComponentDTO {
    @Override
    public Categories category() {
        return Categories.VIDEO_CARD;
    }
}