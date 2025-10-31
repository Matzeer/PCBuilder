package fr.esiea.pcbuilder.infrastructure.exporter;

import fr.esiea.pcbuilder.domain.entities.Computer;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonComputerExporterTest {

    @Test
    void writes_empty_array_when_list_is_empty() throws Exception {
        // Arrange
        JsonComputerExporter exporter = new JsonComputerExporter();
        Path tmp = Files.createTempFile("computers_empty", ".json");

        // Act
        exporter.export(List.of(), tmp.toString());

        // Assert
        String content = Files.readString(tmp, StandardCharsets.UTF_8).trim();
        assertEquals("[]", content);
        assertTrue(Files.size(tmp) >= 2);
    }

    @Test
    void deterministic_output_for_same_input() throws Exception {
        // Arrange
        JsonComputerExporter exporter = new JsonComputerExporter();
        Path f1 = Files.createTempFile("computers_det_1", ".json");
        Path f2 = Files.createTempFile("computers_det_2", ".json");
        List<Computer> input = List.of();

        // Act
        exporter.export(input, f1.toString());
        exporter.export(input, f2.toString());

        // Assert
        byte[] c1 = Files.readAllBytes(f1);
        byte[] c2 = Files.readAllBytes(f2);
        assertArrayEquals(c1, c2);
    }

    @Test
    void wraps_io_errors_in_runtime_exception() {
        // Arrange
        JsonComputerExporter exporter = new JsonComputerExporter();
        String invalidPath =
                System.getProperty("os.name").toLowerCase().contains("win")
                        ? "C:\\Windows\\System32\\forbidden\\out.json"
                        : "/root/builder/forbidden/out.json";

        // Act + Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                exporter.export(List.of(), invalidPath)
        );
        assertTrue(ex.getMessage().toLowerCase().contains("json"));
        assertNotNull(ex.getCause());
    }
}
