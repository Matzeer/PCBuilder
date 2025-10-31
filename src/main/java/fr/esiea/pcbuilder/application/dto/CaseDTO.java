package fr.esiea.pcbuilder.application.dto;

import fr.esiea.pcbuilder.shared.enums.Categories;

public record CaseDTO(int id, String name, double price, double grade,
                      String color, String psu, String sidePanel,
                      int external525Bays, int internal35Bays) {
}
