package fr.esiea.pcbuilder.infrastructure.exporter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.esiea.pcbuilder.application.exporters.ComputerExporter;
import fr.esiea.pcbuilder.domain.entities.Computer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonComputerExporter implements ComputerExporter {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void export(List<Computer> computers, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(computers, writer);
        } catch (IOException e) {
            throw new RuntimeException("Failed to export computers to JSON: " + filePath, e);
        }
    }
}
