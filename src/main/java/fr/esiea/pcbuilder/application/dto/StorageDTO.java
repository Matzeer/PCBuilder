package fr.esiea.pcbuilder.application.dto;

public record StorageDTO(int id, String name, double price, double grade,
                         int capacity, double pricePerGb, String storageType,
                         int cache, String formFactor, String storageInterface) {
}
