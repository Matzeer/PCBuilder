package fr.esiea.pcbuilder.application.dto;

public record CpuDTO(int id, String name, double price, double grade,
                     int coreCount, double coreClock, double boostClock,
                     int tdp, String graphics, boolean smt)
{ }
