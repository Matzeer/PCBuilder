package fr.esiea.pcbuilder.domain.factories;

import fr.esiea.pcbuilder.domain.entities.PowerSupply;

public final class PowerSupplyFactory {

    private PowerSupplyFactory() {}

    public static PowerSupply createExample() {
        return new PowerSupply(
                1,
                "Corsair RM750x",
                129.99,
                4.8,
                "80+ Gold",
                750,
                "Fully Modular",
                "Black"
        );
    }
}
