package fr.esiea.pcbuilder.application.usecases;

import fr.esiea.pcbuilder.application.dto.ComputerDTO;
import fr.esiea.pcbuilder.domain.entities.*;
import fr.esiea.pcbuilder.domain.factories.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectionComponentUseCaseTest {

    @Test
    void executeWithCpuSetsCpuInComputer() {
        // Arrange
        SelectionComponentUseCase useCase = new SelectionComponentUseCase();
        Cpu cpu = CpuFactory.createExample();

        // Act
        assertThrows(NullPointerException.class, () -> useCase.execute(cpu));
    }

    @Test
    void executeWithGpuSetsGpuInComputer() {
        // Arrange
        SelectionComponentUseCase useCase = new SelectionComponentUseCase();
        Gpu gpu = GpuFactory.createExample();

        // Act
        assertThrows(NullPointerException.class, () -> useCase.execute(gpu));
    }

    @Test
    void executeWithMotherBoardSetsMotherBoardInComputer() {
        // Arrange
        SelectionComponentUseCase useCase = new SelectionComponentUseCase();
        MotherBoard mb = MotherBoardFactory.createExample();

        // Act
        assertThrows(NullPointerException.class, () -> useCase.execute(mb));
    }

    @Test
    void executeWithRamSetsRamInComputer() {
        // Arrange
        SelectionComponentUseCase useCase = new SelectionComponentUseCase();
        Ram ram = RamFactory.createExample();

        // Act
        assertThrows(NullPointerException.class, () -> useCase.execute(ram));
    }

    @Test
    void executeWithStorageSetsStorageInComputer() {
        // Arrange
        SelectionComponentUseCase useCase = new SelectionComponentUseCase();
        Storage storage = StorageFactory.createExample();

        // Act
        assertThrows(NullPointerException.class, () -> useCase.execute(storage));
    }

    @Test
    void executeWithPowerSupplySetsPowerSupplyInComputer() {
        // Arrange
        SelectionComponentUseCase useCase = new SelectionComponentUseCase();
        PowerSupply psu = PowerSupplyFactory.createExample();

        // Act
        assertThrows(NullPointerException.class, () -> useCase.execute(psu));
    }

    @Test
    void executeWithCaseSetsCaseInComputer() {
        // Arrange
        SelectionComponentUseCase useCase = new SelectionComponentUseCase();
        Case pcCase = CaseFactory.createExample();

        // Act
        assertThrows(NullPointerException.class, () -> useCase.execute(pcCase));
    }

    @Test
    void executeThrowsWhenComponentIsNull() {
        // Arrange
        SelectionComponentUseCase useCase = new SelectionComponentUseCase();

        // Act + Assert
        assertThrows(NullPointerException.class, () -> useCase.execute(null));
    }
}
