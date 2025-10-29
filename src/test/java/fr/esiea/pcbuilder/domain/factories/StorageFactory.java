package fr.esiea.pcbuilder.domain.factories;

import fr.esiea.pcbuilder.domain.entities.Storage;

public final class StorageFactory {

    private StorageFactory() {}

    public static Storage createExample() {
        return new Storage(
                1,
                "Samsung 970 EVO Plus",
                129.99,
                4.9,
                1000,
                0.13,
                "SSD",
                1024,
                "M.2",
                "NVMe PCIe 3.0 x4"
        );
    }
}
