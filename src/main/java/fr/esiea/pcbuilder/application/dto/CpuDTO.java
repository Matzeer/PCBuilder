package fr.esiea.pcbuilder.application.dto;

import fr.esiea.pcbuilder.shared.enums.Categories;

public record CpuDTO(int id, String name, double price, double grade,
                     int coreCount, double coreClock, double boostClock,
                     int tdp, String graphics, boolean smt)
        implements ComponentDTO {

    @Override
    public Categories category() {
        return Categories.CPU;
    }
}
