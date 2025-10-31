package fr.esiea.pcbuilder.infrastructure.persistence;

import fr.esiea.pcbuilder.application.dto.CpuDTO;
import fr.esiea.pcbuilder.application.dto.GpuDTO;
import fr.esiea.pcbuilder.domain.entities.Cpu;
import fr.esiea.pcbuilder.domain.entities.Gpu;
import fr.esiea.pcbuilder.shared.enums.Categories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CsvComponentRepositoryFilteringTest {

    @TempDir
    Path tempDir;

    @Test
    void filterShouldReturnOnlyRequestedCategory() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("mixed.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,core_count,core_clock,boost_clock,tdp,graphics,smt
                        1,Ryzen 5 5600X,cpu,200.0,4.7,6,3.7,4.6,65,Vega,true
                        2,RTX 4070 Ti,video-card,849.99,4.9,0,0,0,0,None,false
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        // Act
        var result = repo.getComponentListFilteredOrdered(Categories.CPU, new ArrayList<>(), 10);
        // Assert
        assertEquals(1, result.size());
        assertInstanceOf(CpuDTO.class, result.getFirst());
        assertEquals("Ryzen 5 5600X", result.getFirst().name());
    }

    @Test
    void filterShouldReturnEmptyListWhenNoCategoryMatch() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("noMatch.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,chipset,memory,core_clock,boost_clock,color,length
                        1,RTX 4070 Ti,video-card,849.99,4.9,RTX 4070 Ti,12288,2310,2610,Black,310
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        // Act
        var result = repo.getComponentListFilteredOrdered(Categories.CPU, new ArrayList<>(), 10);
        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void filterShouldNotAffectValidEntriesOfOtherCategories() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("multi.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,core_count,core_clock,boost_clock,tdp,graphics,smt,chipset,memory,color,length
                        1,Ryzen 5 5600X,cpu,200.0,4.7,6,3.7,4.6,65,Vega,true,,,,
                        2,Ryzen 7 5800X,cpu,300.0,4.8,8,3.8,4.7,105,Vega,true,,,,
                        3,RTX 4070 Ti,video-card,849.99,4.9,0,2310,2610,0,None,false,RTX 4070 Ti,12288,Black,310
                        """
        );
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        // Act
        var cpuList = repo.getComponentListFilteredOrdered(Categories.CPU, new ArrayList<>(), 10);
        var gpuList = repo.getComponentListFilteredOrdered(Categories.VIDEO_CARD, new ArrayList<>(), 10);
        // Assert
        assertEquals(2, cpuList.size());
        assertEquals(1, gpuList.size());
        assertTrue(cpuList.stream().allMatch(c -> c instanceof CpuDTO));
        assertTrue(gpuList.stream().allMatch(c -> c instanceof GpuDTO));
    }
}
