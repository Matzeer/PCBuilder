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

class CsvComponentRepositoryLimitationTest {

    @TempDir
    Path tempDir;

    @Test
    void limitZeroShouldReturnEmptyList() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("cpu.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,core_count,core_clock,boost_clock,tdp,graphics,smt
                        1,Ryzen 5 5600X,cpu,200.0,4.7,6,3.7,4.6,65,Vega,true
                        2,Ryzen 7 5800X,cpu,300.0,4.8,8,3.8,4.7,105,Vega,true
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        // Act
        var result = repo.getComponentListFilteredOrdered(Categories.CPU, new ArrayList<>(), 0);
        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void limitGreaterThanAvailableShouldReturnAll() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("cpu2.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,core_count,core_clock,boost_clock,tdp,graphics,smt
                        1,Ryzen 5 5600X,cpu,200.0,4.7,6,3.7,4.6,65,Vega,true
                        2,Ryzen 7 5800X,cpu,300.0,4.8,8,3.8,4.7,105,Vega,true
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        // Act
        var result = repo.getComponentListFilteredOrdered(Categories.CPU, new ArrayList<>(), 10);
        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void limitOneShouldReturnExactlyOneSortedEntry() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("cpu3.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,core_count,core_clock,boost_clock,tdp,graphics,smt
                        10,Ryzen 7 5800X,cpu,300.0,4.8,8,3.8,4.7,105,Vega,true
                        5,Ryzen 5 5600X,cpu,200.0,4.7,6,3.7,4.6,65,Vega,true
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>();
        orders.add(QueryParams.PRICE);
        // Act
        var result = repo.getComponentListFilteredOrdered(Categories.CPU, orders, 1);
        // Assert
        assertEquals(1, result.size());
        assertEquals("Ryzen 5 5600X", result.getFirst().name());
    }
}
