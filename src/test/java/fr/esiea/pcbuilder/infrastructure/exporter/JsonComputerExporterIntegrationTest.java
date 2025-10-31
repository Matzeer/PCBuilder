package fr.esiea.pcbuilder.infrastructure.exporter;

import fr.esiea.pcbuilder.domain.entities.Computer;
import fr.esiea.pcbuilder.domain.factories.CaseFactory;
import fr.esiea.pcbuilder.domain.factories.ComputerFactory;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonComputerExporterIntegrationTest {

    @Test
    void end_to_end_use_case_creates_valid_json_file() throws Exception {
        // Arrange
        JsonComputerExporter exporter = new JsonComputerExporter();
        List<Computer> computers = List.of(
                ComputerFactory.createExampleComputer(),
                ComputerFactory.createExampleComputer()
        );
        Path tmp = Files.createTempFile("computers_full", ".json");

        // Act
        exporter.export(computers, tmp.toString());

        // Assert
        String content = Files.readString(tmp);
        assertTrue(content.startsWith("["));
        assertTrue(content.endsWith("]\n") || content.endsWith("]"));
        assertTrue(content.contains("AMD Ryzen 5 5600X"));
        assertTrue(content.contains("NVIDIA GeForce RTX 4070 Ti"));
        assertDoesNotThrow(() -> new com.google.gson.JsonParser().parse(content));
    }
}
