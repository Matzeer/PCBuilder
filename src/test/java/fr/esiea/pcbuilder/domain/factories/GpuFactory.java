package fr.esiea.pcbuilder.domain.factories;

import fr.esiea.pcbuilder.domain.entities.Gpu;

public final class GpuFactory {

    private GpuFactory() {}

    public static Gpu createExample() {
        return new Gpu(
                1,
                "NVIDIA GeForce RTX 4070 Ti",
                849.99,
                4.9,
                "RTX 4070 Ti",
                12288,
                2310,
                2610,
                "Black",
                310
        );
    }
}
