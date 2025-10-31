package fr.esiea.pcbuilder.application.dto;

import fr.esiea.pcbuilder.shared.enums.Categories;

public record StorageDTO(int id, String name, double price, double grade,
                         int capacity, double pricePerGb, String storageType,
                         int cache, String formFactor, String storageInterface)
        implements ComponentDTO {

    @Override
    public Categories category() {
        return Categories.INTERNAL_HARD_DRIVE;
    }
}
