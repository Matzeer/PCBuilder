package fr.esiea.pcbuilder.application.dto;

public record GpuDTO(int id, String name, double price, double grade,
                     String chipset, int memory, int coreClock,
                     int boostClock, String color, int length) {
}
