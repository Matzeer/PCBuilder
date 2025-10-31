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

class CsvComponentRepositoryGenericSortingTest {

    @TempDir
    Path tempDir;

    @Test
    void defaultSortByIdWhenNoOrdersProvided() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("cpu_default_id.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,core_count,core_clock,boost_clock,tdp,graphics,smt
                        5,Alpha,cpu,300.0,4.6,8,3.8,4.7,105,Vega,true
                        2,Beta,cpu,200.0,4.7,6,3.7,4.6,65,Vega,true
                        9,Gamma,cpu,250.0,4.5,6,3.6,4.4,65,Vega,true
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>();
        // Act
        var result = repo.getComponentListFilteredOrdered(Categories.CPU, orders, 10);
        // Assert
        assertEquals(3, result.size());
        assertEquals(2, result.get(0).id());
        assertEquals(5, result.get(1).id());
        assertEquals(9, result.get(2).id());
    }

    @Test
    void sortByNameAscendingCaseInsensitive() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("cpu_name.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,core_count,core_clock,boost_clock,tdp,graphics,smt
                        1,gamma,cpu,250.0,4.5,6,3.6,4.4,65,Vega,true
                        2,Beta,cpu,200.0,4.7,6,3.7,4.6,65,Vega,true
                        3,alpha,cpu,300.0,4.6,8,3.8,4.7,105,Vega,true
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>();
        orders.add(QueryParams.NAME);
        // Act
        var result = repo.getComponentListFilteredOrdered(Categories.CPU, orders, 10);
        // Assert
        assertEquals(3, result.size());
        assertEquals("alpha", result.get(0).name());
        assertEquals("Beta", result.get(1).name());
        assertEquals("gamma", result.get(2).name());
    }

    @Test
    void sortByPriceAscending() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("cpu_price.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,core_count,core_clock,boost_clock,tdp,graphics,smt
                        1,A,cpu,300.0,4.6,8,3.8,4.7,105,Vega,true
                        2,B,cpu,150.0,4.7,6,3.7,4.6,65,Vega,true
                        3,C,cpu,200.0,4.5,6,3.6,4.4,65,Vega,true
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>();
        orders.add(QueryParams.PRICE);
        // Act
        var result = repo.getComponentListFilteredOrdered(Categories.CPU, orders, 10);
        // Assert
        assertEquals("B", result.get(0).name());
        assertEquals("C", result.get(1).name());
        assertEquals("A", result.get(2).name());
    }

    @Test
    void sortByGradeAscending() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("cpu_grade.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,core_count,core_clock,boost_clock,tdp,graphics,smt
                        1,A,cpu,300.0,4.8,8,3.8,4.7,105,Vega,true
                        2,B,cpu,150.0,4.5,6,3.7,4.6,65,Vega,true
                        3,C,cpu,200.0,4.6,6,3.6,4.4,65,Vega,true
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>();
        orders.add(QueryParams.GRADE);
        // Act
        var result = repo.getComponentListFilteredOrdered(Categories.CPU, orders, 10);
        // Assert
        assertEquals("B", result.get(0).name());
        assertEquals("C", result.get(1).name());
        assertEquals("A", result.get(2).name());
    }

    @Test
    void sortByPriceThenNameAsTieBreaker() throws IOException {
        // Arrange
        Path csv = tempDir.resolve("cpu_price_name.csv");
        Files.writeString(csv,
                """
                        id,name,category,price,grade,core_count,core_clock,boost_clock,tdp,graphics,smt
                        1,Delta,cpu,200.0,4.6,8,3.8,4.7,105,Vega,true
                        2,Bravo,cpu,150.0,4.7,6,3.7,4.6,65,Vega,true
                        3,Alpha,cpu,200.0,4.5,6,3.6,4.4,65,Vega,true
                        4,Charlie,cpu,150.0,4.4,6,3.6,4.4,65,Vega,true
                        """);
        CsvComponentRepository repo = new CsvComponentRepository(csv.toString());
        var orders = new ArrayList<QueryParams>();
        orders.add(QueryParams.PRICE);
        orders.add(QueryParams.NAME);
        // Act
        var result = repo.getComponentListFilteredOrdered(Categories.CPU, orders, 10);
        // Assert
        assertEquals(4, result.size());
        assertEquals("Bravo", result.get(0).name());
        assertEquals("Charlie", result.get(1).name());
        assertEquals("Alpha", result.get(2).name());
        assertEquals("Delta", result.get(3).name());
    }
}
