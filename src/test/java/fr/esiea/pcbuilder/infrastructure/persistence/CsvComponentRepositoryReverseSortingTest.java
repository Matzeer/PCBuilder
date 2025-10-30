package fr.esiea.pcbuilder.infrastructure.persistence;

import fr.esiea.pcbuilder.shared.enums.Categories;
import fr.esiea.pcbuilder.shared.enums.QueryParams;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CsvComponentRepositoryReverseSortingTest {

    @TempDir
    Path tempDir;

    @Test
    void cpuShouldSortByPriceDescending() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("cpu_desc_price.csv");
        Files.writeString(csv,
                "id,name,category,price,grade,core_count,core_clock,boost_clock,tdp,graphics,smt\n" +
                        "1,A,cpu,200.0,4.5,6,3.6,4.2,65,Vega,true\n" +
                        "2,B,cpu,350.0,4.7,8,3.8,4.6,105,Vega,true\n" +
                        "3,C,cpu,275.0,4.6,6,3.7,4.4,65,Vega,true\n");
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>(); orders.add(QueryParams.R_PRICE);
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.CPU, orders, 10);
        // Assert
        assertEquals("B", list.get(0).getName());
        assertEquals("C", list.get(1).getName());
        assertEquals("A", list.get(2).getName());
    }

    @Test
    void cpuShouldSortByNameDescendingCaseInsensitive() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("cpu_desc_name.csv");
        Files.writeString(csv,
                "id,name,category,price,grade,core_count,core_clock,boost_clock,tdp,graphics,smt\n" +
                        "1,alpha,cpu,100.0,4.0,4,3.0,3.5,65,Vega,true\n" +
                        "2,Beta,cpu,100.0,4.0,4,3.0,3.5,65,Vega,true\n" +
                        "3,gamma,cpu,100.0,4.0,4,3.0,3.5,65,Vega,true\n");
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>(); orders.add(QueryParams.R_NAME);
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.CPU, orders, 10);
        // Assert
        assertEquals("gamma", list.get(0).getName());
        assertEquals("Beta", list.get(1).getName());
        assertEquals("alpha", list.get(2).getName());
    }

    @Test
    void gpuShouldSortByMemoryDescending() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("gpu_desc_memory.csv");
        Files.writeString(csv,
                "id,name,category,price,grade,chipset,memory,core_clock,boost_clock,color,length\n" +
                        "1,X,video-card,500.0,4.5,RTX 3060,12288,1777,1807,Black,245\n" +
                        "2,Y,video-card,700.0,4.7,RTX 3080,10240,1710,1740,Black,285\n" +
                        "3,Z,video-card,400.0,4.3,RTX 3050,8192,1550,1777,Black,240\n");
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>(); orders.add(QueryParams.R_MEMORY);
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.VIDEO_CARD, orders, 10);
        // Assert
        assertEquals("X", list.get(0).getName());
        assertEquals("Y", list.get(1).getName());
        assertEquals("Z", list.get(2).getName());
    }

    @Test
    void storageShouldSortByCapacityDescending() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("storage_desc_capacity.csv");
        Files.writeString(csv,
                "id,name,category,price,grade,capacity,price_per_gb,storage_type,cache,form_factor,storage_interface\n" +
                        "1,S1,internal-hard-drive,50.0,4.0,500,0.12,SSD,512,2.5,SATA\n" +
                        "2,S2,internal-hard-drive,60.0,4.0,2000,0.03,HDD,256,3.5,SATA\n" +
                        "3,S3,internal-hard-drive,70.0,4.0,1000,0.07,SSD,1024,M.2,NVMe\n");
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>(); orders.add(QueryParams.R_CAPACITY);
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.INTERNAL_HARD_DRIVE, orders, 10);
        // Assert
        assertEquals("S2", list.get(0).getName());
        assertEquals("S3", list.get(1).getName());
        assertEquals("S1", list.get(2).getName());
    }

    @Test
    void ramShouldSortByCasLatencyDescending() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("ram_desc_cas.csv");
        Files.writeString(csv,
                "id,name,category,price,grade,speed_0,speed_1,modules_0,modules_1,price_per_gb,color,first_word_latency,cas_latency\n" +
                        "1,A,memory,80.0,4.2,3600,4000,8,8,5,Black,10,18\n" +
                        "2,B,memory,80.0,4.2,3600,4000,8,8,5,Black,10,16\n" +
                        "3,C,memory,80.0,4.2,3600,4000,8,8,5,Black,10,20\n");
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>(); orders.add(QueryParams.R_CASLATENCY);
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.MEMORY, orders, 10);
        // Assert
        assertEquals("C", list.get(0).getName());
        assertEquals("A", list.get(1).getName());
        assertEquals("B", list.get(2).getName());
    }

    @Test
    void mixedSortShouldApplyReverseOnlyToTaggedKeys() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("cpu_desc_price_then_name.csv");
        Files.writeString(csv,
                "id,name,category,price,grade,core_count,core_clock,boost_clock,tdp,graphics,smt\n" +
                        "1,Alpha,cpu,200.0,4.5,6,3.6,4.2,65,Vega,true\n" +
                        "2,Bravo,cpu,200.0,4.5,6,3.6,4.2,65,Vega,true\n" +
                        "3,Charlie,cpu,350.0,4.5,6,3.6,4.2,65,Vega,true\n");
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>();
        orders.add(QueryParams.R_PRICE); // prix décroissant
        orders.add(QueryParams.NAME);    // tie-breaker croissant
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.CPU, orders, 10);
        // Assert
        assertEquals("Charlie", list.get(0).getName()); // 350 en premier
        assertEquals("Alpha", list.get(1).getName());   // 200 puis nom croissant
        assertEquals("Bravo", list.get(2).getName());
    }
}
