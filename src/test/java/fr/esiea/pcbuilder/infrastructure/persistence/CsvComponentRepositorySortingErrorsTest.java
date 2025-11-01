package fr.esiea.pcbuilder.infrastructure.persistence;

import fr.esiea.pcbuilder.application.dto.FiltersDTO;
import fr.esiea.pcbuilder.shared.enums.Categories;
import fr.esiea.pcbuilder.shared.enums.QueryParams;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CsvComponentRepositorySortingErrorsTest {

    @TempDir
    Path tempDir;

    @Test
    void cpuSortingWithWattageShouldThrowIllegalArgument() throws Exception {
        // Arrange
        Path csv = tempDir.resolve("cpu.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,core_count,core_clock,boost_clock,tdp,graphics,smt
                        1,A,cpu,200.0,4.5,6,3.6,4.2,65,Vega,true
                        2,B,cpu,350.0,4.7,8,3.8,4.6,105,Vega,true
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>(); orders.add(QueryParams.WATTAGE);
        FiltersDTO filtersDTO = new FiltersDTO(Categories.CPU, orders, 10);
        // Act
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> repo.getComponentListFilteredOrdered(filtersDTO));
        // Assert
        assertNotNull(ex);
    }

    @Test
    void gpuSortingWithSocketShouldThrowIllegalArgument() throws Exception {
        // Arrange
        Path csv = tempDir.resolve("gpu.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,chipset,memory,core_clock,boost_clock,color,length
                        1,X,video-card,500.0,4.5,RTX 3060,12288,1777,1807,Black,245
                        2,Y,video-card,700.0,4.7,RTX 3080,10240,1710,1740,Black,285
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>(); orders.add(QueryParams.SOCKET);
        FiltersDTO filtersDTO = new FiltersDTO(Categories.VIDEO_CARD, orders, 10);
        // Act
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> repo.getComponentListFilteredOrdered(filtersDTO));
        // Assert
        assertNotNull(ex);
    }

    @Test
    void ramSortingWithPsuShouldThrowIllegalArgument() throws Exception {
        // Arrange
        Path csv = tempDir.resolve("ram.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,speed_0,speed_1,modules_0,modules_1,price_per_gb,color,first_word_latency,cas_latency
                        1,A,memory,80.0,4.2,3600,4000,8,8,5,Black,10,18
                        2,B,memory,80.0,4.2,3200,3600,8,8,5,Black,10,16
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>(); orders.add(QueryParams.PSU);
        FiltersDTO filtersDTO = new FiltersDTO(Categories.MEMORY, orders, 10);
        // Act
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> repo.getComponentListFilteredOrdered(filtersDTO));
        // Assert
        assertNotNull(ex);
    }

    @Test
    void cpuSortingWithPriceThenWattageShouldThrowIllegalArgument() throws Exception {
        // Arrange
        Path csv = tempDir.resolve("cpu_price_wattage.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,core_count,core_clock,boost_clock,tdp,graphics,smt
                        1,A,cpu,200.0,4.5,6,3.6,4.2,65,Vega,true
                        2,B,cpu,200.0,4.7,8,3.8,4.6,105,Vega,true
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>();
        orders.add(QueryParams.PRICE);
        orders.add(QueryParams.WATTAGE);
        FiltersDTO filtersDTO = new FiltersDTO(Categories.CPU, orders, 10);
        // Act
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> repo.getComponentListFilteredOrdered(filtersDTO));
        // Assert
        assertNotNull(ex);
    }

    @Test
    void storageSortingWithRamKeyShouldThrowIllegalArgument() throws Exception {
        // Arrange
        Path csv = tempDir.resolve("storage.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,capacity,price_per_gb,type,cache,form_factor,interface
                        1,S1,internal-hard-drive,50.0,4.0,500,0.12,SSD,512,2.5,SATA
                        2,S2,internal-hard-drive,60.0,4.0,1000,0.06,HDD,256,3.5,SATA
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>(); orders.add(QueryParams.SPEED0);
        FiltersDTO filtersDTO = new FiltersDTO(Categories.INTERNAL_HARD_DRIVE, orders, 10);
        // Act
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> repo.getComponentListFilteredOrdered(filtersDTO));
        // Assert
        assertNotNull(ex);
    }

    @Test
    void reverseOnIncompatibleKeyShouldThrowIllegalArgument() throws Exception {
        // Arrange
        Path csv = tempDir.resolve("cpu_rev_wattage.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,core_count,core_clock,boost_clock,tdp,graphics,smt
                        1,A,cpu,200.0,4.5,6,3.6,4.2,65,Vega,true
                        2,B,cpu,350.0,4.7,8,3.8,4.6,105,Vega,true
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>(); orders.add(QueryParams.R_WATTAGE);
        FiltersDTO filtersDTO = new FiltersDTO(Categories.CPU, orders, 10);
        // Act
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> repo.getComponentListFilteredOrdered(filtersDTO));
        // Assert
        assertNotNull(ex);
    }

    @Test
    void allOrdersInvalidShouldThrowIllegalArgument() throws Exception {
        // Arrange
        Path csv = tempDir.resolve("cpu_all_invalid.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,core_count,core_clock,boost_clock,tdp,graphics,smt
                        1,A,cpu,200.0,4.5,6,3.6,4.2,65,Vega,true
                        2,B,cpu,300.0,4.6,8,3.8,4.6,105,Vega,true
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>();
        orders.add(QueryParams.WATTAGE);
        orders.add(QueryParams.MODULAR);
        FiltersDTO filtersDTO = new FiltersDTO(Categories.CPU, orders, 10);
        // Act
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> repo.getComponentListFilteredOrdered(filtersDTO));
        // Assert
        assertNotNull(ex);
    }
}
