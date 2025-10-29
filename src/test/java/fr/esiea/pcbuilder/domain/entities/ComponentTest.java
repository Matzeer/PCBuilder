package fr.esiea.pcbuilder.domain.entities;

import fr.esiea.pcbuilder.domain.factories.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ComponentTest {
    @Test
    void casesCanBeCreated() {
        //Act
        Case pcCase = CaseFactory.createExample();

        //Assert
        Assertions.assertNotNull(pcCase);
    }

    @Test
    void powerSuppliesCanBeCreated() {
        //Act
        PowerSupply psu = PowerSupplyFactory.createExample();

        //Assert
        Assertions.assertNotNull(psu);
    }

    @Test
    void CpuCanBeCreated() {
        //Act
        Cpu cpu = CpuFactory.createExample();

        //Assert
        Assertions.assertNotNull(cpu);
    }

    @Test
    void GpuCanBeCreated() {
        //Act
        Gpu gpu = GpuFactory.createExample();

        //Assert
        Assertions.assertNotNull(gpu);
    }

    @Test
    void MotherboardCanBeCreated() {
        //Act
        MotherBoard motherboard = MotherBoardFactory.createExample();

        //Assert
        Assertions.assertNotNull(motherboard);
    }

    @Test
    void MemoryCanBeCreated() {
        //Act
        Ram ram = RamFactory.createExample();

        //Assert
        Assertions.assertNotNull(ram);
    }

    @Test
    void StorageCanBeCreated() {
        //Act
        Storage storage = StorageFactory.createExample();

        //Assert
        Assertions.assertNotNull(storage);
    }
}
