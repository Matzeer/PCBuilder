package fr.esiea.pcbuilder.infrastructure.persistence;

import fr.esiea.pcbuilder.domain.entities.*;
import fr.esiea.pcbuilder.shared.enums.Categories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CsvComponentRepositoryParsingTest {

    @TempDir
    Path tempDir;

    @Test
    void cpuLineShouldBeParsedIntoCpuInstance() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("cpu.csv");
        Files.writeString(csv, "id,name,category,price,grade,core_count,core_clock,boost_clock,tdp,graphics,smt\n" +
                "1,Ryzen 5 5600X,cpu,200.0,4.7,6,3.7,4.6,65,Vega,true");
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.CPU, new ArrayList<>(), 10);
        // Assert
        assertEquals(1, list.size());
        assertInstanceOf(Cpu.class, list.getFirst());
        Cpu cpu = (Cpu) list.getFirst();
        assertEquals(6, cpu.getCoreCount());
        assertEquals(3.7, cpu.getCoreClock());
    }

    @Test
    void caseLineShouldBeParsedIntoCaseInstance() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("case.csv");
        Files.writeString(csv, "id,name,category,price,grade,color,psu,side_panel,external_525_bays,internal_35_bays\n" +
                "1,NZXT H510,case,89.99,4.5,Black,None,Tempered Glass,2,2");
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.CASE, new ArrayList<>(), 10);
        // Assert
        assertEquals(1, list.size());
        assertInstanceOf(Case.class, list.getFirst());
        Case c = (Case) list.getFirst();
        assertEquals("Black", c.getColor());
        assertEquals(2, c.getExternal525Bays());
    }

    @Test
    void motherboardLineShouldBeParsedIntoMotherBoardInstance() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("motherboard.csv");
        Files.writeString(csv, "id,name,category,price,grade,socket,form_factor,max_memory,memory_slots,color\n" +
                "1,ASUS B550,motherboard,149.99,4.6,AM4,ATX,128,4,Black");
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.MOTHERBOARD, new ArrayList<>(), 10);
        // Assert
        assertEquals(1, list.size());
        assertInstanceOf(MotherBoard.class, list.getFirst());
        MotherBoard m = (MotherBoard) list.getFirst();
        assertEquals("AM4", m.getSocket());
        assertEquals(128, m.getMaxMemory());
    }

    @Test
    void gpuLineShouldBeParsedIntoGpuInstance() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("gpu.csv");
        Files.writeString(csv, "id,name,category,price,grade,chipset,memory,core_clock,boost_clock,color,length\n" +
                "1,RTX 4070 Ti,video-card,849.99,4.9,RTX 4070 Ti,12288,2310,2610,Black,310");
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.VIDEO_CARD, new ArrayList<>(), 10);
        // Assert
        assertEquals(1, list.size());
        assertInstanceOf(Gpu.class, list.getFirst());
        Gpu g = (Gpu) list.getFirst();
        assertEquals("RTX 4070 Ti", g.getChipset());
        assertEquals(12288, g.getMemory());
    }

    @Test
    void powerSupplyLineShouldBeParsedIntoPowerSupplyInstance() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("psu.csv");
        Files.writeString(csv, "id,name,category,price,grade,efficiency,wattage,modular,color\n" +
                "1,Corsair RM750x,power-supply,129.99,4.8,80+ Gold,750,Fully Modular,Black");
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.POWER_SUPPLY, new ArrayList<>(), 10);
        // Assert
        assertEquals(1, list.size());
        assertInstanceOf(PowerSupply.class, list.getFirst());
        PowerSupply psu = (PowerSupply) list.getFirst();
        assertEquals(750, psu.getWattage());
        assertEquals("80+ Gold", psu.getEfficiency());
    }

    @Test
    void storageLineShouldBeParsedIntoStorageInstance() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("storage.csv");
        Files.writeString(csv, "id,name,category,price,grade,capacity,price_per_gb,storage_type,cache,form_factor,storage_interface\n" +
                "1,Samsung 970 EVO,internal-hard-drive,129.99,4.9,1000,0.13,SSD,1024,M.2,NVMe");
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.INTERNAL_HARD_DRIVE, new ArrayList<>(), 10);
        // Assert
        assertEquals(1, list.size());
        assertInstanceOf(Storage.class, list.getFirst());
        Storage s = (Storage) list.getFirst();
        assertEquals(1000, s.getCapacity());
        assertEquals("SSD", s.getStorageType());
    }

    @Test
    void ramLineShouldBeParsedIntoRamInstance() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("ram.csv");
        Files.writeString(csv, "id,name,category,price,grade,speed_0,speed_1,modules_0,modules_1,price_per_gb,color,first_word_latency,cas_latency\n" +
                "1,Corsair Vengeance,memory,89.99,4.7,3200,3600,8,8,5,Black,10,16");
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.MEMORY, new ArrayList<>(), 10);
        // Assert
        assertEquals(1, list.size());
        assertInstanceOf(Ram.class, list.getFirst());
        Ram r = (Ram) list.getFirst();
        assertEquals(3200, r.getSpeed0());
        assertEquals(8, r.getModule0());
    }

    @Test
    void unknownCategoryLineShouldBeIgnored() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("unknown.csv");
        Files.writeString(csv, "id,name,category,price,grade\n1,Weird Component,unknown,10.0,3.0");
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.CPU, new ArrayList<>(), 10);
        // Assert
        assertTrue(list.isEmpty());
    }

    @Test
    void malformedLineShouldBeIgnored() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("malformed.csv");
        Files.writeString(csv, "id,name,category,price,grade,core_count,core_clock,boost_clock,tdp,graphics,smt\n" +
                "1,Ryzen,cpu,notANumber,4.5,6,3.7,4.6,65,Vega,true");
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        // Act
        var list = repo.getComponentListFilteredOrdered(Categories.CPU, new ArrayList<>(), 10);
        // Assert
        assertTrue(list.isEmpty());
    }
}
