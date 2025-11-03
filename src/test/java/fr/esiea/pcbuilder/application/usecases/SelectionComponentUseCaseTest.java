package fr.esiea.pcbuilder.application.usecases;

import fr.esiea.pcbuilder.application.dto.*;
import fr.esiea.pcbuilder.application.mappers.ComponentMapper;
import fr.esiea.pcbuilder.application.mappers.ComputerMapper;
import fr.esiea.pcbuilder.application.repositories.ComputerGateway;
import fr.esiea.pcbuilder.domain.entities.*;
import fr.esiea.pcbuilder.domain.factories.*;

import fr.esiea.pcbuilder.infrastructure.persistence.InMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class SelectionComponentUseCaseTest {

    private ComputerGateway computerGateway;
    private CreateComputerUseCase createComputerUseCase;
    private AddComputerUseCase addComputerUseCase;

    @BeforeEach
    void setUp() {
        computerGateway = new InMemoryRepository();
        createComputerUseCase = new CreateComputerUseCase();
        addComputerUseCase = new AddComputerUseCase(computerGateway);
    }

    @Test
    void executeWithCpuSetsCpuInComputer() {
        // Arrange
        SelectionComponentUseCase useCase = new SelectionComponentUseCase(computerGateway);
        Cpu cpu = CpuFactory.createExample();
        CpuDTO cpuDto = (CpuDTO) ComponentMapper.toDto(cpu);
        ComputerDTO computerDto = createComputerUseCase.execute(addComputerUseCase);
        ComputerDTO result = useCase.execute(computerDto,cpuDto);
        // Act
        assertNotNull(result.cpu());
        assertEquals(cpuDto, result.cpu());
    }

    @Test
    void executeWithGpuSetsGpuInComputer() {
        // Arrange
        SelectionComponentUseCase useCase = new SelectionComponentUseCase(computerGateway);
        Gpu gpu = GpuFactory.createExample();
        GpuDTO gpuDto = (GpuDTO) ComponentMapper.toDto(gpu);
        ComputerDTO computerDto = createComputerUseCase.execute(addComputerUseCase);
        ComputerDTO result = useCase.execute(computerDto, gpuDto);
        // Act
        assertNotNull(result.gpu());
        assertEquals(gpuDto, result.gpu());
    }

    @Test
    void executeWithMotherBoardSetsMotherBoardInComputer() {
        // Arrange
        SelectionComponentUseCase useCase = new SelectionComponentUseCase(computerGateway);
        MotherBoard mb = MotherBoardFactory.createExample();
        MotherBoardDTO mbDto = (MotherBoardDTO) ComponentMapper.toDto(mb);
        ComputerDTO computerDto = createComputerUseCase.execute(addComputerUseCase);
        ComputerDTO result = useCase.execute(computerDto,mbDto);
        // Act
        assertNotNull(result.motherBoard());
        assertEquals(mbDto, result.motherBoard());
    }

    @Test
    void executeWithRamSetsRamInComputer() {
        // Arrange
        SelectionComponentUseCase useCase = new SelectionComponentUseCase(computerGateway);
        Ram ram = RamFactory.createExample();
        RamDTO ramDto = (RamDTO) ComponentMapper.toDto(ram);
        ComputerDTO computerDto = createComputerUseCase.execute(addComputerUseCase);
        ComputerDTO result = useCase.execute(computerDto,ramDto);
        // Act
        assertNotNull(result.ram());
        assertEquals(ramDto, result.ram());
    }

    @Test
    void executeWithStorageSetsStorageInComputer() {
        // Arrange
        SelectionComponentUseCase useCase = new SelectionComponentUseCase(computerGateway);
        Storage storage = StorageFactory.createExample();
        StorageDTO storageDto = (StorageDTO) ComponentMapper.toDto(storage);
        ComputerDTO computerDto = createComputerUseCase.execute(addComputerUseCase);
        ComputerDTO result = useCase.execute(computerDto,storageDto);
        // Act
        assertNotNull(result.storage());
        assertEquals(storageDto, result.storage());
    }

    @Test
    void executeWithPowerSupplySetsPowerSupplyInComputer() {
        // Arrange
        SelectionComponentUseCase useCase = new SelectionComponentUseCase(computerGateway);
        PowerSupply psu = PowerSupplyFactory.createExample();
        PowerSupplyDTO psuDto = (PowerSupplyDTO) ComponentMapper.toDto(psu);
        ComputerDTO computerDto = createComputerUseCase.execute(addComputerUseCase);
        ComputerDTO result = useCase.execute(computerDto,psuDto);
        // Act
        assertNotNull(result.powerSupply());
        assertEquals(psuDto, result.powerSupply());
    }

    @Test
    void executeWithCaseSetsCaseInComputer() {
        // Arrange
        SelectionComponentUseCase useCase = new SelectionComponentUseCase(computerGateway);
        Case pcCase = CaseFactory.createExample();
        CaseDTO caseDto = (CaseDTO) ComponentMapper.toDto(pcCase);
        ComputerDTO computerDto = createComputerUseCase.execute(addComputerUseCase);
        ComputerDTO result = useCase.execute(computerDto,caseDto);
        // Act
        assertNotNull(result.desktopCase());
        assertEquals(caseDto, result.desktopCase());
    }

    @Test
    void executeThrowsWhenComponentIsNull() {
        // Arrange
        SelectionComponentUseCase useCase = new SelectionComponentUseCase(computerGateway);
        ComputerDTO computerDto = createComputerUseCase.execute(addComputerUseCase);
        // Act + Assert
        assertThrows(NullPointerException.class, () -> useCase.execute(computerDto,null));
    }
}
