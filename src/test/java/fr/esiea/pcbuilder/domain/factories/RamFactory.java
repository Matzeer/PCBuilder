package fr.esiea.pcbuilder.domain.factories;

import fr.esiea.pcbuilder.domain.entities.Ram;

public final class RamFactory {

    private RamFactory() {
    }

    public static Ram createExample() {
        return new Ram(
                1,
                "Corsair Vengeance LPX",
                89.99,
                4.7,
                3200,
                3600,
                8,
                8,
                5,
                "Black",
                10,
                16
        );
    }
}
