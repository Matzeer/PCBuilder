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

class JsonComputerExporterUtf8Test {

    @Test
    void writes_utf8_content_with_special_characters() throws Exception {
        // Arrange
        JsonComputerExporter exporter = new JsonComputerExporter();
        Computer c = ComputerFactory.createExampleComputer();
        Cpu specialCpu = new Cpu (
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
        c.setCpu(specialCpu);
        List<Computer> list = List.of(c);
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
