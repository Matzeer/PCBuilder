package fr.esiea.pcbuilder.application.usecases;

import fr.esiea.pcbuilder.application.dto.*;
import fr.esiea.pcbuilder.application.mappers.ComponentMapper;
import fr.esiea.pcbuilder.application.mappers.ComputerMapper;
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
        CpuDTO cpuDto = (CpuDTO) ComponentMapper.toDto(cpu);
        ComputerDTO result = useCase.execute(cpuDto);
        // Act
        assertNotNull(result.cpu());
        assertEquals(cpuDto, result.cpu());
    }

    @Test
    void executeWithGpuSetsGpuInComputer() {
        // Arrange
        SelectionComponentUseCase useCase = new SelectionComponentUseCase();
        Gpu gpu = GpuFactory.createExample();
        GpuDTO gpuDto = (GpuDTO) ComponentMapper.toDto(gpu);
        ComputerDTO result = useCase.execute(gpuDto);
        // Act
        assertNotNull(result.gpu());
        assertEquals(gpuDto, result.gpu());
    }

    @Test
    void executeWithMotherBoardSetsMotherBoardInComputer() {
        // Arrange
        SelectionComponentUseCase useCase = new SelectionComponentUseCase();
        MotherBoard mb = MotherBoardFactory.createExample();
        MotherBoardDTO mbDto = (MotherBoardDTO) ComponentMapper.toDto(mb);
        ComputerDTO result = useCase.execute(mbDto);
        // Act
        assertNotNull(result.motherBoard());
        assertEquals(mbDto, result.motherBoard());
    }

    @Test
    void executeWithRamSetsRamInComputer() {
        // Arrange
        SelectionComponentUseCase useCase = new SelectionComponentUseCase();
        Ram ram = RamFactory.createExample();
        RamDTO ramDto = (RamDTO) ComponentMapper.toDto(ram);
        ComputerDTO result = useCase.execute(ramDto);
        // Act
        assertNotNull(result.ram());
        assertEquals(ramDto, result.ram());
    }

    @Test
    void executeWithStorageSetsStorageInComputer() {
        // Arrange
        SelectionComponentUseCase useCase = new SelectionComponentUseCase();
        Storage storage = StorageFactory.createExample();
        StorageDTO storageDto = (StorageDTO) ComponentMapper.toDto(storage);
        ComputerDTO result = useCase.execute(storageDto);
        // Act
        assertNotNull(result.storage());
        assertEquals(storageDto, result.storage());
    }

    @Test
    void executeWithPowerSupplySetsPowerSupplyInComputer() {
        // Arrange
        SelectionComponentUseCase useCase = new SelectionComponentUseCase();
        PowerSupply psu = PowerSupplyFactory.createExample();
        PowerSupplyDTO psuDto = (PowerSupplyDTO) ComponentMapper.toDto(psu);
        ComputerDTO result = useCase.execute(psuDto);
        // Act
        assertNotNull(result.powerSupply());
        assertEquals(psuDto, result.powerSupply());
    }

    @Test
    void executeWithCaseSetsCaseInComputer() {
        // Arrange
        SelectionComponentUseCase useCase = new SelectionComponentUseCase();
        Case pcCase = CaseFactory.createExample();
        CaseDTO caseDto = (CaseDTO) ComponentMapper.toDto(pcCase);
        ComputerDTO result = useCase.execute(caseDto);
        // Act
        assertNotNull(result.desktopCase());
        assertEquals(caseDto, result.desktopCase());
    }

    @Test
    void executeThrowsWhenComponentIsNull() {
        // Arrange
        SelectionComponentUseCase useCase = new SelectionComponentUseCase();

        // Act + Assert
        assertThrows(NullPointerException.class, () -> useCase.execute(null));
    }
}
