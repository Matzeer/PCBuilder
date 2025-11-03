package fr.esiea.pcbuilder.application.dto;

import fr.esiea.pcbuilder.shared.enums.Categories;

public interface ComponentDTO {
    int id();

    String name();

    double price();

    double grade();

    Categories category();
}
