package fr.esiea.pcbuilder.domain.entities;

import fr.esiea.pcbuilder.domain.factories.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

public class ComputerTest {
    @Test
    void ComputerCanBeCreated() {
        //Arrange
        Case pcCase = CaseFactory.createExample();
        PowerSupply psu = PowerSupplyFactory.createExample();
        Ram ram = RamFactory.createExample();
        Cpu cpu = CpuFactory.createExample();
        Gpu gpu = GpuFactory.createExample();
        MotherBoard motherboard = MotherBoardFactory.createExample();
        Storage storage = StorageFactory.createExample();

        //Act
        Computer computer = new Computer(pcCase, psu, ram, cpu, gpu, motherboard, storage);

        //Assert
        Assertions.assertNotNull(computer);
    }
}
