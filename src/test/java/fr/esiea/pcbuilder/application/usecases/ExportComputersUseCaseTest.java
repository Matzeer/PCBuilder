package fr.esiea.pcbuilder.application.usecases;

import fr.esiea.pcbuilder.application.exporters.ComputerExporter;
import fr.esiea.pcbuilder.application.repositories.ComputerGateway;
import fr.esiea.pcbuilder.domain.entities.Computer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExportComputersUseCaseTest {

    @Test
    void executeCallsRepositoryThenExporterWithSameArguments() {
        // Arrange
        ComputerGateway repository = mock(ComputerGateway.class);
        ComputerExporter exporter = mock(ComputerExporter.class);
        List<Computer> computers = List.of();
        when(repository.getComputers()).thenReturn(computers);
        ExportComputersUseCase useCase = new ExportComputersUseCase(repository);
        String path = "/tmp/out.json";

        // Act
        useCase.execute(exporter, path);

        // Assert
        verify(repository).getComputers();
        verify(exporter).export(computers, path);
        verifyNoMoreInteractions(repository, exporter);
    }

    @Test
    void executeExportsEvenWhenListIsEmpty() {
        // Arrange
        ComputerGateway repository = mock(ComputerGateway.class);
        ComputerExporter exporter = mock(ComputerExporter.class);
        when(repository.getComputers()).thenReturn(List.of());
        ExportComputersUseCase useCase = new ExportComputersUseCase(repository);
        String path = "/tmp/empty.json";

        // Act
        useCase.execute(exporter, path);

        // Assert
        verify(repository).getComputers();
        verify(exporter).export(List.of(), path);
        verifyNoMoreInteractions(repository, exporter);
    }

    @Test
    void executeThrowsWhenRepositoryFails() {
        // Arrange
        ComputerGateway repository = mock(ComputerGateway.class);
        ComputerExporter exporter = mock(ComputerExporter.class);
        when(repository.getComputers()).thenThrow(new IllegalStateException("Repository failure"));
        ExportComputersUseCase useCase = new ExportComputersUseCase(repository);

        // Act + Assert
        assertThrows(IllegalStateException.class, () ->
                useCase.execute(exporter, "/tmp/out.json")
        );
        verify(repository).getComputers();
        verifyNoInteractions(exporter);
    }

    @Test
    void executeThrowsWhenExporterFails() {
        // Arrange
        ComputerGateway repository = mock(ComputerGateway.class);
        ComputerExporter exporter = mock(ComputerExporter.class);
        List<Computer> computers = List.of();
        when(repository.getComputers()).thenReturn(computers);
        doThrow(new RuntimeException("Disk full")).when(exporter).export(computers, "/tmp/out.json");
        ExportComputersUseCase useCase = new ExportComputersUseCase(repository);

        // Act + Assert
        assertThrows(RuntimeException.class, () ->
                useCase.execute(exporter, "/tmp/out.json")
        );
        verify(repository).getComputers();
        verify(exporter).export(computers, "/tmp/out.json");
        verifyNoMoreInteractions(repository, exporter);
    }
}
