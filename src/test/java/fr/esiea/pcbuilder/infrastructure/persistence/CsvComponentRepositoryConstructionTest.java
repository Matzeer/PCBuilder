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

class CsvComponentRepositoryConstructionTest {

    @TempDir
    Path tempDir;

    @Test
    void constructorShouldThrowIllegalArgumentWhenFileDoesNotExist() {
        // Arrange
        String invalidPath = tempDir.resolve("missing.csv").toString();
        // Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new CsvComponentRepository(invalidPath));
        // Assert
        assertTrue(ex.getMessage().contains("CSV"));
    }

    @Test
    void constructorShouldThrowIllegalArgumentWhenPathIsDirectory() {
        // Arrange
        String dirPath = tempDir.toString();
        // Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new CsvComponentRepository(dirPath));
        // Assert
        assertTrue(ex.getMessage().contains("CSV"));
    }

    @Test
    void getComponentListFilteredOrderedShouldReturnEmptyListWhenCsvIsEmpty() throws IOException {
        // Arrange
        Path emptyCsv = tempDir.resolve("empty.csv");
        Files.createFile(emptyCsv);
        CsvComponentRepository repo = new CsvComponentRepository(emptyCsv.toString());
        // Act
        var result = repo.getComponentListFilteredOrdered(Categories.CPU, new ArrayList<QueryParams>(), 10);
        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }
}
