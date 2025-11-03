package fr.esiea.pcbuilder.infrastructure.persistence;

import fr.esiea.pcbuilder.application.dto.FiltersDTO;
import fr.esiea.pcbuilder.shared.enums.Categories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CsvComponentRepositoryMalformedParsingTest {

    @TempDir
    Path tempDir;

    @Test
    void cpuLineWithNonNumericPriceIsIgnored() throws Exception {
        // Arrange
        Path csv = tempDir.resolve("cpu_bad_price.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,core_count,core_clock,boost_clock,tdp,graphics,smt
                        1,Ryzen 5 5600X,cpu,notANumber,4.7,6,3.7,4.6,65,Vega,true
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        FiltersDTO filtersDTO = new FiltersDTO(Categories.CPU, new ArrayList<>(), 10);
        // Act
        var list = repo.getComponentListFilteredOrdered(filtersDTO);
        // Assert
        assertTrue(list.isEmpty());
    }

    @Test
    void cpuLineWithInvalidBooleanSmtIsParsedAsFalse() throws Exception {
        // Arrange
        Path csv = tempDir.resolve("cpu_bad_bool.csv");
        Files.writeString(csv, """
        id,name,category,price,grade,core_count,core_clock,boost_clock,tdp,graphics,smt
        1,Ryzen 5 5600X,cpu,200.0,4.7,6,3.7,4.6,65,Vega,maybe
        2,Ryzen 7 5800X,cpu,300.0,4.8,8,3.8,4.7,105, Vega,true
        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        FiltersDTO filtersDTO = new FiltersDTO(Categories.CPU, new ArrayList<>(), 10);

        // Act
        var list = repo.getComponentListFilteredOrdered(filtersDTO);

        // Assert
        assertEquals(2, list.size());

        var cpuFalse = (fr.esiea.pcbuilder.application.dto.CpuDTO) list.get(0);
        var cpuTrue  = (fr.esiea.pcbuilder.application.dto.CpuDTO) list.get(1);

        assertFalse(cpuFalse.smt());
        assertTrue(cpuTrue.smt());
    }

    @Test
    void gpuLineWithEmptyNumericFieldIsIgnored() throws Exception {
        // Arrange
        Path csv = tempDir.resolve("gpu_empty_numeric.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,chipset,memory,core_clock,boost_clock,color,length
                        1,RTX 3060,video-card,329.0,4.5,RTX 3060,,1777,1807,Black,245
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        FiltersDTO filtersDTO = new FiltersDTO(Categories.VIDEO_CARD, new ArrayList<>(), 10);
        // Act
        var list = repo.getComponentListFilteredOrdered(filtersDTO);

        // Assert
        assertTrue(list.isEmpty());
    }

    @Test
    void storageLineWithCommaDecimalIsIgnored() throws Exception {
        // Arrange
        Path csv = tempDir.resolve("storage_comma_decimal.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,capacity,price_per_gb,storage_type,cache,form_factor,storage_interface
                        1,SSD,internal-hard-drive,129,9,1000,0,13,SSD,1024,M.2,NVMe
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        FiltersDTO filtersDTO = new FiltersDTO(Categories.INTERNAL_HARD_DRIVE, new ArrayList<>(), 10);
        // Act
        var list = repo.getComponentListFilteredOrdered(filtersDTO);
        // Assert
        assertTrue(list.isEmpty());
    }

    @Test
    void mixedFileShouldKeepOnlyWellFormedRows() throws Exception {
        // Arrange
        Path csv = tempDir.resolve("mixed_good_bad.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,core_count,core_clock,boost_clock,tdp,graphics,smt
                        1,Ryzen Bad,cpu,xx,4.7,6,3.7,4.6,65,Vega,true
                        2,Ryzen Good,cpu,199.9,4.6,6,3.6,4.4,65,Vega,true
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        FiltersDTO filtersDTO = new FiltersDTO(Categories.CPU, new ArrayList<>(), 10);
        // Act
        var list = repo.getComponentListFilteredOrdered(filtersDTO);
        // Assert
        assertEquals(1, list.size());
        assertEquals("Ryzen Good", list.getFirst().name());
    }

    @Test
    void missingHeaderForRequiredFieldLeadsToIgnoredRow() throws Exception {
        // Arrange
        Path csv = tempDir.resolve("cpu_missing_header.csv");
        // header without core_count
        Files.writeString(csv,
                """
                        id,name,category,price,grade,core_clock,boost_clock,tdp,graphics,smt
                        1,Ryzen 5 5600X,cpu,200.0,4.7,3.7,4.6,65,Vega,true
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        FiltersDTO filtersDTO = new FiltersDTO(Categories.CPU, new ArrayList<>(), 10);
        // Act
        var list = repo.getComponentListFilteredOrdered(filtersDTO);
        // Assert
        assertTrue(list.isEmpty());
    }

    @Test
    void ramLineWithWhitespaceNumericFieldIsIgnored() throws Exception {
        // Arrange
        Path csv = tempDir.resolve("ram_whitespace_numeric.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,speed_0,speed_1,modules_0,modules_1,price_per_gb,color,first_word_latency,cas_latency
                        1,Corsair,memory,89.99,4.7, ,3600,8,8,5,Black,10,16
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        FiltersDTO filtersDTO = new FiltersDTO(Categories.MEMORY, new ArrayList<>(), 10);
        // Act
        var list = repo.getComponentListFilteredOrdered(filtersDTO);
        // Assert
        assertTrue(list.isEmpty());
    }

    @Test
    void caseLineWithMissingIntegerFieldIsIgnored() throws Exception {
        // Arrange
        Path csv = tempDir.resolve("case_missing_int.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,color,psu,side_panel,external_525_bays,internal_35_bays
                        1,NZXT H510,case,89.99,4.5,Black,None,Tempered Glass,,2
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        FiltersDTO filtersDTO = new FiltersDTO(Categories.CASE, new ArrayList<>(), 10);
        // Act
        var list = repo.getComponentListFilteredOrdered(filtersDTO);
        // Assert
        assertTrue(list.isEmpty());
    }
}
