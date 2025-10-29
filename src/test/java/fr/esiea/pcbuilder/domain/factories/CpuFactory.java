package fr.esiea.pcbuilder.domain.factories;

import fr.esiea.pcbuilder.domain.entities.Cpu;

public final class CpuFactory {

    private CpuFactory() {}

    public static Cpu createExample() {
        return new Cpu(
                1,
                "AMD Ryzen 5 5600X",
                219.99,
                4.8,
                6,
                3.7,
                4.6,
                65,
                "Radeon Vega",
                true
        );
    }
}
