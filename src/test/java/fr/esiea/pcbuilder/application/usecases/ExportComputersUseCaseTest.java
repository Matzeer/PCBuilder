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
    void happy_path_calls_repo_then_exporter_with_same_args() {
        // Arrange
        ComputerGateway repo = mock(ComputerGateway.class);
        ComputerExporter exporter = mock(ComputerExporter.class);
        List<Computer> computers = List.of();
        when(repo.getComputers()).thenReturn(computers);
        ExportComputersUseCase useCase = new ExportComputersUseCase(repo);
        String path = "/tmp/out.json";

        // Act
        useCase.execute(exporter, path);

        // Assert
        verify(repo).getComputers();
        verify(exporter).export(computers, path);
        verifyNoMoreInteractions(repo, exporter);
    }

    @Test
    void empty_list_is_still_exported() {
        // Arrange
        ComputerGateway repo = mock(ComputerGateway.class);
        ComputerExporter exporter = mock(ComputerExporter.class);
        when(repo.getComputers()).thenReturn(List.of());
        ExportComputersUseCase useCase = new ExportComputersUseCase(repo);
        String path = "/tmp/empty.json";

        // Act
        useCase.execute(exporter, path);

        // Assert
        verify(repo).getComputers();
        verify(exporter).export(List.of(), path);
        verifyNoMoreInteractions(repo, exporter);
    }

    @Test
    void propagates_exception_from_repository() {
        // Arrange
        ComputerGateway repo = mock(ComputerGateway.class);
        ComputerExporter exporter = mock(ComputerExporter.class);
        when(repo.getComputers()).thenThrow(new IllegalStateException("repo down"));
        ExportComputersUseCase useCase = new ExportComputersUseCase(repo);

        // Act + Assert
        assertThrows(IllegalStateException.class, () ->
                useCase.execute(exporter, "/tmp/out.json")
        );
        verify(repo).getComputers();
        verifyNoInteractions(exporter);
    }

    @Test
    void propagates_exception_from_exporter() {
        // Arrange
        ComputerGateway repo = mock(ComputerGateway.class);
        ComputerExporter exporter = mock(ComputerExporter.class);
        List<Computer> computers = List.of();
        when(repo.getComputers()).thenReturn(computers);
        doThrow(new RuntimeException("disk full")).when(exporter).export(computers, "/tmp/out.json");
        ExportComputersUseCase useCase = new ExportComputersUseCase(repo);

        // Act + Assert
        assertThrows(RuntimeException.class, () ->
                useCase.execute(exporter, "/tmp/out.json")
        );
        verify(repo).getComputers();
        verify(exporter).export(computers, "/tmp/out.json");
        verifyNoMoreInteractions(repo, exporter);
    }
}
