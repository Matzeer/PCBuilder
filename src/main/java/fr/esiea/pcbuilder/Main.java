package fr.esiea.pcbuilder;

import fr.esiea.pcbuilder.application.exporters.ComputerExporter;
import fr.esiea.pcbuilder.application.repositories.ComponentGateway;
import fr.esiea.pcbuilder.application.repositories.ComputerGateway;
import fr.esiea.pcbuilder.application.usecases.*;
import fr.esiea.pcbuilder.infrastructure.controllers.ConfiguratorController;
import fr.esiea.pcbuilder.infrastructure.exporter.JsonComputerExporter;
import fr.esiea.pcbuilder.infrastructure.persistence.CsvComponentRepository;
import fr.esiea.pcbuilder.infrastructure.persistence.InMemoryRepository;
import fr.esiea.pcbuilder.presentation.presenters.*;
import fr.esiea.pcbuilder.presentation.views.ConsoleView;

public class Main {
    public static void main(String[] args) {
        // Gateways
        ComputerGateway inMemoryRepository = new InMemoryRepository();
        ComponentGateway componentGateway = new CsvComponentRepository("src/main/resources/data/components.csv");

        // Exporter
        ComputerExporter computerExporter = new JsonComputerExporter();

        // Use Cases
        ListComponentUseCase listComponentUseCase = new ListComponentUseCase(componentGateway);
        SelectionComponentUseCase selectionComponentUseCase = new SelectionComponentUseCase(inMemoryRepository);
        SelectFiltersUseCase selectFiltersUseCase = new SelectFiltersUseCase();

        ListComputerUseCase listComputerUseCase = new ListComputerUseCase(inMemoryRepository);
        AddComputerUseCase addComputerUseCase = new AddComputerUseCase(inMemoryRepository);
        ExportComputersUseCase exportComputersUseCase = new ExportComputersUseCase(inMemoryRepository);

        // Presenter
        MenuPresenter menuPresenter = new MenuPresenter();

        ComponentPresenter componentPresenter = new ComponentPresenter();
        FilterPresenter filterPresenter = new FilterPresenter();
        LimitPresenter limitPresenter = new LimitPresenter();
        OrderPresenter orderPresenter = new OrderPresenter();
        OrderDirectionPresenter orderDirectionPresenter = new OrderDirectionPresenter();
        ComputerListPresenter computerListPresenter = new ComputerListPresenter();

        ComputerPresenter computerPresenter = new ComputerPresenter();
        ExportPresenter exportPresenter = new ExportPresenter();

        // View
        ConsoleView view = new ConsoleView();

        ConfiguratorController controller = new ConfiguratorController(
                selectionComponentUseCase,
                listComponentUseCase,
                addComputerUseCase,
                selectFiltersUseCase,
                exportComputersUseCase,
                listComputerUseCase,
                computerExporter,
                menuPresenter,
                computerPresenter,
                componentPresenter,
                filterPresenter,
                orderPresenter,
                orderDirectionPresenter,
                limitPresenter,
                computerListPresenter,
                exportPresenter,
                view
        );
        controller.execute();
    }
}