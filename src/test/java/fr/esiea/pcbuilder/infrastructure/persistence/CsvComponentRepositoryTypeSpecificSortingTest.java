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

class CsvComponentRepositoryTypeSpecificSortingTest {

    @TempDir
    Path tempDir;

    // ---------- CPU ----------

    @Test
    void cpuShouldSortByCoreClockAscending() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("cpu_coreclock.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,core_count,core_clock,boost_clock,tdp,graphics,smt
                        1,A,cpu,200.0,4.6,6,3.9,4.5,65,Vega,true
                        2,B,cpu,210.0,4.6,6,3.5,4.4,65,Vega,true
                        3,C,cpu,220.0,4.6,6,3.7,4.6,65,Vega,true
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>(); orders.add(QueryParams.CORECLOCK);
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.CPU, orders, 10);
        // Assert
        assertEquals("B", list.get(0).getName());
        assertEquals("C", list.get(1).getName());
        assertEquals("A", list.get(2).getName());
    }

    @Test
    void cpuShouldSortByGraphicsAlphabeticallyCaseInsensitive() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("cpu_graphics.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,core_count,core_clock,boost_clock,tdp,graphics,smt
                        1,A,cpu,200.0,4.6,6,3.7,4.5,65,vega,true
                        2,B,cpu,210.0,4.6,6,3.7,4.5,65,Intel UHD,true
                        3,C,cpu,220.0,4.6,6,3.7,4.5,65,Radeon,true
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>(); orders.add(QueryParams.GRAPHICS);
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.CPU, orders, 10);
        // Assert
        assertEquals("B", list.get(0).getName()); // Intel UHD
        assertEquals("C", list.get(1).getName()); // Radeon
        assertEquals("A", list.get(2).getName()); // vega
    }

    // ---------- GPU ----------

    @Test
    void gpuShouldSortByMemoryAscending() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("gpu_memory.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,chipset,memory,core_clock,boost_clock,color,length
                        1,X,video-card,500.0,4.5,RTX 3060,12288,1777,1807,Black,245
                        2,Y,video-card,700.0,4.7,RTX 3080,10240,1710,1740,Black,285
                        3,Z,video-card,400.0,4.3,RTX 3050,8192,1550,1777,Black,240
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>(); orders.add(QueryParams.MEMORY);
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.VIDEO_CARD, orders, 10);
        // Assert
        assertEquals("Z", list.get(0).getName());
        assertEquals("Y", list.get(1).getName());
        assertEquals("X", list.get(2).getName());
    }

    @Test
    void gpuShouldSortByLengthAscending() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("gpu_length.csv");
        Files.writeString(csv,
                "id,name,category,price,grade,chipset,memory,core_clock,boost_clock,color,length\n" +
                        "1,A,video-card,500.0,4.5,RTX 3060,12288,1777,1807,Black,300\n" +
                        "2,B,video-card,500.0,4.5,RTX 3060,12288,1777,1807,Black,250\n" +
                        "3,C,video-card,500.0,4.5,RTX 3060,12288,1777,1807,Black,270\n");
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>(); orders.add(QueryParams.LENGTH);
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.VIDEO_CARD, orders, 10);
        // Assert
        assertEquals("B", list.get(0).getName());
        assertEquals("C", list.get(1).getName());
        assertEquals("A", list.get(2).getName());
    }

    // ---------- CASE ----------

    @Test
    void caseShouldSortByExternal525BaysAscending() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("case_bays.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,color,psu,side_panel,external_525_bays,internal_35_bays
                        1,A,case,80.0,4.2,Black,None,Tempered Glass,0,2
                        2,B,case,90.0,4.2,Black,None,Tempered Glass,2,2
                        3,C,case,70.0,4.2,Black,None,Tempered Glass,1,2
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>(); orders.add(QueryParams.EXTERNAL525BAYS);
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.CASE, orders, 10);
        // Assert
        assertEquals("A", list.get(0).getName());
        assertEquals("C", list.get(1).getName());
        assertEquals("B", list.get(2).getName());
    }

    @Test
    void caseShouldSortByColorAlphabeticallyCaseInsensitive() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("case_color.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,color,psu,side_panel,external_525_bays,internal_35_bays
                        1,A,case,80.0,4.2,white,None,Tempered Glass,0,2
                        2,B,case,80.0,4.2,Black,None,Tempered Glass,0,2
                        3,C,case,80.0,4.2,Gray,None,Tempered Glass,0,2
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>(); orders.add(QueryParams.COLOR);
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.CASE, orders, 10);
        // Assert
        assertEquals("B", list.get(0).getName()); // Black
        assertEquals("C", list.get(1).getName()); // Gray
        assertEquals("A", list.get(2).getName()); // white
    }

    // ---------- MOTHERBOARD ----------

    @Test
    void motherboardShouldSortByMaxMemoryAscending() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("mb_maxmemory.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,socket,form_factor,max_memory,memory_slots,color
                        1,A,motherboard,120.0,4.1,AM4,ATX,128,4,Black
                        2,B,motherboard,110.0,4.1,AM4,ATX,64,4,Black
                        3,C,motherboard,150.0,4.1,AM4,ATX,256,4,Black
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>(); orders.add(QueryParams.MAXMEMORY);
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.MOTHERBOARD, orders, 10);
        // Assert
        assertEquals("B", list.get(0).getName());
        assertEquals("A", list.get(1).getName());
        assertEquals("C", list.get(2).getName());
    }

    @Test
    void motherboardShouldSortBySocketAlphabeticallyCaseInsensitive() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("mb_socket.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,socket,form_factor,max_memory,memory_slots,color
                        1,A,motherboard,120.0,4.1,LGA1700,ATX,128,4,Black
                        2,B,motherboard,120.0,4.1,am4,ATX,128,4,Black
                        3,C,motherboard,120.0,4.1,TR4,ATX,128,4,Black
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>(); orders.add(QueryParams.SOCKET);
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.MOTHERBOARD, orders, 10);
        // Assert
        assertEquals("B", list.get(0).getName()); // am4
        assertEquals("1", String.valueOf(list.get(1).getId())); // LGA1700
        assertEquals("3", String.valueOf(list.get(2).getId())); // TR4
    }

    // ---------- STORAGE ----------

    @Test
    void storageShouldSortByCapacityAscending() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("storage_capacity.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,capacity,price_per_gb,storage_type,cache,form_factor,storage_interface
                        1,S1,internal-hard-drive,50.0,4.0,2000,0.025,HDD,256,3.5,SATA
                        2,S2,internal-hard-drive,60.0,4.0,500,0.12,SSD,512,2.5,SATA
                        3,S3,internal-hard-drive,70.0,4.0,1000,0.07,SSD,1024,M.2,NVMe
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>(); orders.add(QueryParams.CAPACITY);
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.INTERNAL_HARD_DRIVE, orders, 10);
        // Assert
        assertEquals("S2", list.get(0).getName());
        assertEquals("S3", list.get(1).getName());
        assertEquals("S1", list.get(2).getName());
    }

    @Test
    void storageShouldSortByStorageTypeAlphabeticallyCaseInsensitive() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("storage_type.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,capacity,price_per_gb,storage_type,cache,form_factor,storage_interface
                        1,A,internal-hard-drive,50.0,4.0,500,0.12,ssd,512,2.5,SATA
                        2,B,internal-hard-drive,60.0,4.0,500,0.12,HDD,256,3.5,SATA
                        3,C,internal-hard-drive,70.0,4.0,500,0.12,NVMe,512,M.2,PCIe
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>(); orders.add(QueryParams.STORAGETYPE);
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.INTERNAL_HARD_DRIVE, orders, 10);
        // Assert
        assertEquals("2", String.valueOf(list.get(0).getId())); // HDD
        assertEquals("3", String.valueOf(list.get(1).getId())); // NVMe
        assertEquals("1", String.valueOf(list.get(2).getId())); // ssd
    }

    // ---------- RAM ----------

    @Test
    void ramShouldSortBySpeed0Ascending() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("ram_speed0.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,speed_0,speed_1,modules_0,modules_1,price_per_gb,color,first_word_latency,cas_latency
                        1,A,memory,80.0,4.2,3600,4000,8,8,5,Black,10,18
                        2,B,memory,80.0,4.2,3200,3600,8,8,5,Black,10,18
                        3,C,memory,80.0,4.2,6000,6400,16,16,6,Black,12,36
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>(); orders.add(QueryParams.SPEED0);
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.MEMORY, orders, 10);
        // Assert
        assertEquals("B", list.get(0).getName());
        assertEquals("A", list.get(1).getName());
        assertEquals("C", list.get(2).getName());
    }

    @Test
    void ramShouldSortByCasLatencyAscending() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("ram_cas.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,speed_0,speed_1,modules_0,modules_1,price_per_gb,color,first_word_latency,cas_latency
                        1,A,memory,80.0,4.2,3600,4000,8,8,5,Black,10,18
                        2,B,memory,80.0,4.2,3600,4000,8,8,5,Black,10,16
                        3,C,memory,80.0,4.2,3600,4000,8,8,5,Black,10,20
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>(); orders.add(QueryParams.CASLATENCY);
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.MEMORY, orders, 10);
        // Assert
        assertEquals("B", list.get(0).getName());
        assertEquals("A", list.get(1).getName());
        assertEquals("C", list.get(2).getName());
    }

    @Test
    void ramShouldSortByPricePerGbAscending() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("ram_pricepergb.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,speed_0,speed_1,modules_0,modules_1,price_per_gb,color,first_word_latency,cas_latency
                        1,A,memory,80.0,4.2,3600,4000,8,8,6,Black,10,18
                        2,B,memory,80.0,4.2,3600,4000,8,8,4,Black,10,18
                        3,C,memory,80.0,4.2,3600,4000,8,8,5,Black,10,18
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>(); orders.add(QueryParams.PRICEPERGB);
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.MEMORY, orders, 10);
        // Assert
        assertEquals("B", list.get(0).getName());
        assertEquals("C", list.get(1).getName());
        assertEquals("A", list.get(2).getName());
    }
}