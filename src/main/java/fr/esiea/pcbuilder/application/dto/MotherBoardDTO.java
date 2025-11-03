package fr.esiea.pcbuilder.application.dto;

import fr.esiea.pcbuilder.shared.enums.Categories;

public record MotherBoardDTO(int id, String name, double price, double grade,
                             String socket, String formFactor, int maxMemory,
                             int memorySlots, String color)
        implements ComponentDTO {

    @Override
    public Categories category() {
        return Categories.MOTHERBOARD;
    }
}
