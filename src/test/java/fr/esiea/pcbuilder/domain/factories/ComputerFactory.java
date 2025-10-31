package fr.esiea.pcbuilder.domain.factories;

import fr.esiea.pcbuilder.domain.entities.*;

public class ComputerFactory {

    public static Computer createExampleComputer() {
        Case pcCase = CaseFactory.createExample();
        PowerSupply psu = PowerSupplyFactory.createExample();
        Ram ram = RamFactory.createExample();
        Cpu cpu = CpuFactory.createExample();
        Gpu gpu = GpuFactory.createExample();
        MotherBoard motherboard = MotherBoardFactory.createExample();
        Storage storage = StorageFactory.createExample();

        return new Computer(pcCase, psu, ram, cpu, gpu, motherboard, storage);
    }
}
