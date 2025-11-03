package fr.esiea.pcbuilder.application.usecases;

import fr.esiea.pcbuilder.application.dto.*;
import fr.esiea.pcbuilder.application.repositories.ComputerGateway;
import fr.esiea.pcbuilder.infrastructure.persistence.InMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddComputerUseCaseTest {

    private ComputerGateway repository;
    private AddComputerUseCase useCase;
    private CreateComputerUseCase createComputerUseCase;

    @BeforeEach
    void setUp() {
        repository = new InMemoryRepository();
        useCase = new AddComputerUseCase(repository);
        createComputerUseCase = new CreateComputerUseCase();
    }

    @Test
    void executeThrowsWhenDtoIsNull() {
        ComputerDTO computerDto = null;
        assertThrows(IllegalArgumentException.class, () -> useCase.execute(computerDto));
    }

    @Test
    void executeAddsValidComputerToRepository() {
        // Arrange
        ComputerDTO dto = createComputerUseCase.execute(useCase);
        dto = new ComputerDTO(
                dto.id(),
                new CaseDTO(1, "Case", 100, 4.5, "Black", "No", "Glass", 2, 3),
                new PowerSupplyDTO(2, "PSU", 80, 4.2, "Gold", 750, "Semi", "Black"),
                new RamDTO(3, "RAM", 60, 4.7, 3200, 3600, 2, 16, 4, "Black", 10, 18),
                new CpuDTO(4, "CPU", 200, 4.8, 6, 3.5, 4.5, 95, "Intel", true),
                new GpuDTO(5, "GPU", 400, 4.9, "RTX", 8192, 1500, 1800, "Black", 250),
                new MotherBoardDTO(6, "MB", 180, 4.3, "LGA1700", "ATX", 128, 4, "Black"),
                new StorageDTO(7, "SSD", 90, 4.6, 1000, 0.09, "SSD", 512, "2.5", "SATA")
        );
        // Act
        useCase.execute(dto);
        // Assert
        assertEquals(1, repository.getComputers().size());
        var saved = repository.getComputers().getFirst();

        assertNotNull(saved.getCpu());
        assertNotNull(saved.getGpu());
        assertNotNull(saved.getMotherBoard());
        assertNotNull(saved.getRam());
        assertNotNull(saved.getStorage());
        assertNotNull(saved.getPowerSupply());
        assertNotNull(saved.getDesktopCase());
    }
}
