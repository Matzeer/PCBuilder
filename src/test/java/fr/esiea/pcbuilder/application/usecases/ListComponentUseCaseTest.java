package fr.esiea.pcbuilder.application.usecases;

import fr.esiea.pcbuilder.application.dto.ComponentDTO;
import fr.esiea.pcbuilder.application.dto.FiltersDTO;
import fr.esiea.pcbuilder.application.repositories.ComponentGateway;
import fr.esiea.pcbuilder.infrastructure.persistence.CsvComponentRepository;
import fr.esiea.pcbuilder.shared.enums.Categories;
import fr.esiea.pcbuilder.shared.enums.QueryParams;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ListComponentUseCaseTest {

    private Path tempCsv;
    private ComponentGateway gateway;

    @BeforeEach
    void setUp() throws IOException {
        tempCsv = Files.createTempFile("components", ".csv");

        String csvContent = """
                id,name,price,grade,category,core_count,core_clock,boost_clock,tdp,graphics,smt,color,psu,side_panel,external_525_bays,internal_35_bays,socket,form_factor,max_memory,memory_slots,chipset,memory,length,efficiency,wattage,modular,storage_type,capacity,cache,storage_interface
                1,Intel i5,200.0,4.5,cpu,6,3.5,4.2,95,Intel UHD,true,,,,,,,,,,,,,,,,,,,
                2,Intel i7,300.0,4.8,cpu,8,3.8,5.0,125,Intel UHD,true,,,,,,,,,,,,,,,,,,,
                3,Nvidia GTX,400.0,4.9,video-card,,,,,,false,,,,,,,,,RTX,8192,250,,,,,,,
                4,Asus Z790,250.0,4.7,motherboard,,,,,,false,,,,LGA1700,ATX,128,4,,,,,,,,,
                5,Corsair 750W,120.0,4.4,power-supply,,,,,,false,,,,,,,,,,,,,,80+,750,FULL,,,,,
                6,Kingston DDR4,90.0,4.6,memory,,,,,,false,,,,,,,,,,,,,,,,,SSD,512,64,SATA
                """;

        Files.writeString(tempCsv, csvContent);


        Files.writeString(tempCsv, csvContent);
        gateway = new CsvComponentRepository(tempCsv.toString());
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempCsv);
    }

    @Test
    void givenValidCpuCategory_whenExecute_thenReturnCpuList() {
        ListComponentUseCase useCase = new ListComponentUseCase(gateway);
        FiltersDTO filters = new FiltersDTO(Categories.CPU, new ArrayList<>(), 10);

        ArrayList<ComponentDTO> result = useCase.execute(filters);

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(c -> c.category() == Categories.CPU));
    }

    @Test
    void givenCategoryAndLimit_whenExecute_thenReturnLimitedList() {
        ListComponentUseCase useCase = new ListComponentUseCase(gateway);
        ArrayList<QueryParams> orders = new ArrayList<>();
        FiltersDTO filters = new FiltersDTO(Categories.CPU, orders, 1);

        ArrayList<ComponentDTO> result = useCase.execute(filters);

        assertEquals(1, result.size());
    }

    @Test
    void givenOrderByPrice_whenExecute_thenSortedByPriceAsc() {
        ListComponentUseCase useCase = new ListComponentUseCase(gateway);
        ArrayList<QueryParams> orders = new ArrayList<>();
        orders.add(QueryParams.PRICE);
        FiltersDTO filters = new FiltersDTO(Categories.CPU, orders, 10);

        ArrayList<ComponentDTO> result = useCase.execute(filters);
        assertEquals(2, result.size());
        assertTrue(result.get(0).price() <= result.get(1).price());
    }

    @Test
    void givenReverseOrderByPrice_whenExecute_thenSortedByPriceDesc() {
        ListComponentUseCase useCase = new ListComponentUseCase(gateway);
        ArrayList<QueryParams> orders = new ArrayList<>();
        orders.add(QueryParams.valueOf("R_PRICE"));
        FiltersDTO filters = new FiltersDTO(Categories.CPU, orders, 10);

        ArrayList<ComponentDTO> result = useCase.execute(filters);
        assertEquals(2, result.size());
        assertTrue(result.get(0).price() >= result.get(1).price());
    }

    @Test
    void givenInvalidCategory_whenExecute_thenThrowIllegalArgument() {
        ListComponentUseCase useCase = new ListComponentUseCase(gateway);
        FiltersDTO filters = new FiltersDTO(null, new ArrayList<>(), 5);
        assertThrows(IllegalArgumentException.class, () ->
                useCase.execute(filters));
    }

    @Test
    void givenNegativeLimit_whenExecute_thenThrowIllegalArgument() {
        ListComponentUseCase useCase = new ListComponentUseCase(gateway);
        FiltersDTO filters = new FiltersDTO(Categories.VIDEO_CARD, new ArrayList<>(), -2);
        assertThrows(IllegalArgumentException.class, () ->
                useCase.execute(filters));
    }

    @Test
    void givenEmptyFile_whenExecute_thenReturnEmptyList() throws IOException {
        Path emptyCsv = Files.createTempFile("empty", ".csv");
        Files.writeString(emptyCsv, "id,name,price,grade,category\n");

        CsvComponentRepository repo = new CsvComponentRepository(emptyCsv.toString());
        ListComponentUseCase useCase = new ListComponentUseCase(repo);
        FiltersDTO filters = new FiltersDTO(Categories.CPU, new ArrayList<>(), 5);

        ArrayList<ComponentDTO> result = useCase.execute(filters);

        assertTrue(result.isEmpty());
        Files.deleteIfExists(emptyCsv);
    }

    @Test
    void givenNonExistentCsv_whenInitRepository_thenThrowIllegalArgument() {
        Path fakePath = Path.of("fake.csv");
        assertThrows(IllegalArgumentException.class, () ->
                new CsvComponentRepository(fakePath.toString()));
    }
}
