package fr.esiea.pcbuilder.infrastructure.exporter;

import fr.esiea.pcbuilder.domain.entities.Computer;
import fr.esiea.pcbuilder.domain.entities.Cpu;
import fr.esiea.pcbuilder.domain.factories.ComputerFactory;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonComputerExporterTest {

    @Test
    void exportWritesEmptyArrayWhenListIsEmpty() throws Exception {
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
    void exportProducesDeterministicOutputForSameInput() throws Exception {
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
    void exportThrowsRuntimeExceptionWhenIoFails() {
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

    @Test
    void exportCreatesValidJsonFileWithRealComputers() throws Exception {
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

    @Test
    void exportWritesUtf8ContentWithSpecialCharacters() throws Exception {
        // Arrange
        JsonComputerExporter exporter = new JsonComputerExporter();
        Computer computer = ComputerFactory.createExampleComputer();
        Cpu specialCpu = new Cpu(
                1,
                "Ryzen 7 7800X3D – édition « Spéciale »",
                219.99,
                4.8,
                6,
                3.7,
                4.6,
                65,
                "Radeon Vega",
                true
        );
        computer.setCpu(specialCpu);
        List<Computer> list = List.of(computer);
        Path tmp = Files.createTempFile("computers_utf8", ".json");

        // Act
        exporter.export(list, tmp.toString());

        // Assert
        byte[] bytes = Files.readAllBytes(tmp);
        String content = new String(bytes, StandardCharsets.UTF_8);

        assertTrue(content.contains("Ryzen"));
        assertTrue(content.contains("Spéciale"));
        assertTrue(content.contains("RTX 4070"));
        assertFalse(content.contains("�"));
        assertEquals(content, Files.readString(tmp, StandardCharsets.UTF_8));
    }
}
