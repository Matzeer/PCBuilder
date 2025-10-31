package fr.esiea.pcbuilder.application.dto;

public record PowerSupplyDTO(int id, String name, double price, double grade,
                             String efficiency, int wattage, String modular, String color) {
}
