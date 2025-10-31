package fr.esiea.pcbuilder.application.dto;

public record MotherBoardDTO(int id, String name, double price, double grade,
                             String socket, String formFactor, int maxMemory,
                             int memorySlots, String color) {
}
