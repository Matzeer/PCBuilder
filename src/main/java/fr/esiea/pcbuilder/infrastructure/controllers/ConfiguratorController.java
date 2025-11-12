package fr.esiea.pcbuilder.infrastructure.controllers;

import fr.esiea.pcbuilder.application.dto.ComponentDTO;
import fr.esiea.pcbuilder.application.dto.ComputerDTO;
import fr.esiea.pcbuilder.application.dto.FiltersDTO;
import fr.esiea.pcbuilder.application.exporters.ComputerExporter;
import fr.esiea.pcbuilder.application.usecases.*;
import fr.esiea.pcbuilder.presentation.presenters.*;
import fr.esiea.pcbuilder.presentation.ui.ConsoleUI;
import fr.esiea.pcbuilder.presentation.views.ConsoleView;
import fr.esiea.pcbuilder.shared.enums.Categories;
import fr.esiea.pcbuilder.shared.enums.QueryParams;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConfiguratorController {
    private final SelectionComponentUseCase selectionComponentUseCase;
    private final ListComponentUseCase listComponentUseCase;
    private final AddComputerUseCase addComputerUseCase;
    private final SelectFiltersUseCase selectFiltersUseCase;
    private final ComputerExporter computerExporter;
    private final ExportComputersUseCase exportComputersUseCase;
    private final ListComputerUseCase listComputerUseCase;
    private final MenuPresenter menuPresenter;
    private final ComputerPresenter computerPresenter;
    private final ComponentPresenter componentPresenter;
    private final FilterPresenter filterPresenter;
    private final OrderPresenter orderPresenter;
    private final OrderDirectionPresenter orderDirectionPresenter;
    private final LimitPresenter limitPresenter;
    private final ComputerListPresenter computerListPresenter;
    private final ExportPresenter exportPresenter;
    private final ConsoleView view;

    public ConfiguratorController(
            SelectionComponentUseCase selectionComponentUseCase,
            ListComponentUseCase listComponentUseCase,
            AddComputerUseCase addComputerUseCase,
            SelectFiltersUseCase selectFiltersUseCase,
            ExportComputersUseCase exportComputersUseCase,
            ListComputerUseCase listComputerUseCase,
            ComputerExporter computerExporter,
            MenuPresenter menuPresenter,
            ComputerPresenter computerPresenter,
            ComponentPresenter componentPresenter,
            FilterPresenter filterPresenter,
            OrderPresenter orderPresenter,
            OrderDirectionPresenter orderDirectionPresenter,
            LimitPresenter limitPresenter,
            ComputerListPresenter computerListPresenter,
            ExportPresenter exportPresenter,
            ConsoleView view
    ) {
        this.selectionComponentUseCase = selectionComponentUseCase;
        this.listComponentUseCase = listComponentUseCase;
        this.addComputerUseCase = addComputerUseCase;
        this.selectFiltersUseCase = selectFiltersUseCase;
        this.exportComputersUseCase = exportComputersUseCase;
        this.listComputerUseCase = listComputerUseCase;
        this.computerExporter = computerExporter;
        this.menuPresenter = menuPresenter;
        this.computerPresenter = computerPresenter;
        this.componentPresenter = componentPresenter;
        this.filterPresenter = filterPresenter;
        this.orderPresenter = orderPresenter;
        this.orderDirectionPresenter = orderDirectionPresenter;
        this.limitPresenter = limitPresenter;
        this.computerListPresenter = computerListPresenter;
        this.exportPresenter = exportPresenter;
        this.view = view;
    }

    public void execute() {
        while (true) {
            String menu = menuPresenter.present();
            view.display(menu);
            String choice = view.getUserInput().toUpperCase();

            switch (choice) {
                case "C" -> {
                    // Computer building
                    buildComputer();
                }
                case "E" -> {
                    // Computer export
                    exportComputerList();
                }
                case "Z" -> {
                    // Leave the application
                    view.close();
                    return;
                }
                default ->
                        view.display(ConsoleUI.BRIGHT_RED + "Erreur, saisie invalide. Entrez c pour configurer un PC, e pour exporter la liste des PC ou z pour quitter.");
            }

        }
    }

    private void buildComputer() {
        ComputerDTO newComputer = new CreateComputerUseCase().execute(addComputerUseCase);
        while (true) {
            String menuConfig = computerPresenter.present(newComputer);
            view.display(menuConfig);

            String choice = view.getUserInput().toUpperCase();
            FiltersDTO filtersTemplate = new SelectFiltersUseCase().execute(null);
            switch (choice) {
                case "1" -> {
                    // Select CPU
                    FiltersDTO filters = new SelectFiltersUseCase().execute(new FiltersDTO(Categories.CPU,
                            filtersTemplate.orders(),
                            filtersTemplate.limit()));
                    newComputer = listComponentAndSelect(newComputer, filters);
                }
                case "2" -> {
                    // Select GPU
                    FiltersDTO filters = new SelectFiltersUseCase().execute(new FiltersDTO(Categories.VIDEO_CARD,
                            filtersTemplate.orders(),
                            filtersTemplate.limit()));
                    newComputer = listComponentAndSelect(newComputer, filters);
                }
                case "3" -> {
                    // Select RAM
                    FiltersDTO filters = new SelectFiltersUseCase().execute(new FiltersDTO(Categories.MEMORY,
                            filtersTemplate.orders(),
                            filtersTemplate.limit()));
                    newComputer = listComponentAndSelect(newComputer, filters);
                }
                case "4" -> {
                    // Select Storage
                    FiltersDTO filters = new SelectFiltersUseCase().execute(new FiltersDTO(Categories.INTERNAL_HARD_DRIVE,
                            filtersTemplate.orders(),
                            filtersTemplate.limit()));
                    newComputer = listComponentAndSelect(newComputer, filters);
                }
                case "5" -> {
                    // Select Motherboard
                    FiltersDTO filters = new SelectFiltersUseCase().execute(new FiltersDTO(Categories.MOTHERBOARD,
                            filtersTemplate.orders(),
                            filtersTemplate.limit()));
                    newComputer = listComponentAndSelect(newComputer, filters);
                }
                case "6" -> {
                    // Select Power Supply
                    FiltersDTO filters = new SelectFiltersUseCase().execute(new FiltersDTO(Categories.POWER_SUPPLY,
                            filtersTemplate.orders(),
                            filtersTemplate.limit()));
                    newComputer = listComponentAndSelect(newComputer, filters);
                }
                case "7" -> {
                    // Select Case
                    FiltersDTO filters = new SelectFiltersUseCase().execute(new FiltersDTO(Categories.CASE,
                            filtersTemplate.orders(),
                            filtersTemplate.limit()));
                    newComputer = listComponentAndSelect(newComputer, filters);
                }
                case "Z" -> {
                    // Go back to main menu
                    return;
                }
                case "S" -> {
                    // Save the computer
                    addComputerUseCase.execute(newComputer);
                    return;
                }
                case "C" -> {
                    // Load a saved computer
                    ComputerDTO newComputerTmp = listComputerAndSelect();
                    if (newComputerTmp != null) {
                        newComputer = newComputerTmp;
                    }
                }
                default ->
                        view.display(ConsoleUI.BRIGHT_RED + "Erreur, saisie invalide. Entrez un numéro de composant à configurer, s pour sauvegarder, c pour charger un PC sauvegardé ou z pour revenir au menu principal.");
            }
        }
    }

    private ComputerDTO listComponentAndSelect(ComputerDTO newComputer, FiltersDTO filters) {

        ArrayList<ComponentDTO> list = listComponentUseCase.execute(filters);
        String componentList = componentPresenter.present(filters.category(), list, filters);
        view.display(componentList);

        String choice = view.getUserInput().trim().toUpperCase();

        if (choice.matches("\\d+")) {
            //Choose component
            int number = Integer.parseInt(choice);

            if (number < 1 || number > list.size()) {
                view.display(String.format(ConsoleUI.BRIGHT_RED + "Erreur, saisie invalide. Entrez un nombre entre 1 et %d, f pour modifier les filtres ou z pour revenir en arrière.", list.size()));
                return listComponentAndSelect(newComputer, filters);
            }
            return selectionComponentUseCase.execute(newComputer, list.get(number - 1));
        } else if (choice.equals("F")) {
            //Modify filters
            FiltersDTO newFilters = selectFilter(filters);
            return listComponentAndSelect(newComputer, newFilters);
        } else if (choice.equals("Z")) {
            //Go back
            return newComputer;
        } else {
            view.display(String.format(ConsoleUI.BRIGHT_RED + "Erreur, saisie invalide. Entrez un nombre entre 1 et %d, f pour modifier les filtres ou z pour revenir en arrière.", list.size()));
            return listComponentAndSelect(newComputer, filters);
        }
    }

    private ComputerDTO listComputerAndSelect() {
        List<ComputerDTO> computers = listComputerUseCase.execute();
        String computerList = computerListPresenter.present(computers);
        while (true) {
            view.display(computerList);
            String choice = view.getUserInput().trim().toUpperCase();

            if (choice.matches("\\d+")) {
                int number = Integer.parseInt(choice);

                if (number < 1 || number > computers.size()) {
                    view.display(String.format(ConsoleUI.BRIGHT_RED + "Erreur, saisie invalide. Entrez un nombre entre 1 et %d, ou z pour revenir en arrière.", computers.size()));
                } else {
                    return computers.get(number - 1);
                }
            } else if (choice.equals("Z")) {
                return null;
            } else {
                view.display(String.format(ConsoleUI.BRIGHT_RED + "Erreur, saisie invalide. Entrez un nombre entre 1 et %d, ou z pour revenir en arrière.", computers.size()));
            }
        }
    }

    private FiltersDTO selectFilter(FiltersDTO currentFilters) {
        FiltersDTO newFilters = currentFilters;

        while (true) {
            String menu = filterPresenter.present(newFilters);
            view.display(menu);
            String choice = view.getUserInput().trim().toUpperCase();

            switch (choice) {
                case "T" -> newFilters = selectOrder(newFilters); // Select order
                case "L" -> newFilters = selectLimit(newFilters); // Select limit
                case "Z" -> {
                    // Go back
                    return newFilters;
                }
                default ->
                        view.display(ConsoleUI.BRIGHT_RED + "Erreur, saisie invalide. Entrez t pour trier, l pour limiter le nombre de résultats ou z pour revenir en arrière.");
            }
        }
    }

    private FiltersDTO selectLimit(FiltersDTO currentFilters) {
        FiltersDTO newFilters = currentFilters;

        while (true) {
            String menu = limitPresenter.present(newFilters);
            view.display(menu);
            String input = view.getUserInput().trim().toUpperCase();

            if (input.equals("Z")) {
                return newFilters;
            } else {
                try {
                    int limit = Integer.parseInt(input);
                    if (limit > 0) {
                        newFilters = selectFiltersUseCase.execute(new FiltersDTO(newFilters.category(),
                                newFilters.orders(),
                                limit));
                        return newFilters;
                    } else {
                        view.display(ConsoleUI.BRIGHT_RED + "Erreur, la limite doit être positif.");
                    }
                } catch (NumberFormatException e) {
                    view.display(ConsoleUI.BRIGHT_RED + "Erreur, saisie invalide. Entrez un nombre positif ou z pour revenir en arrière.");
                }
            }
        }
    }

    private FiltersDTO selectOrder(FiltersDTO currentFilters) {
        FiltersDTO newFilters = currentFilters;

        while (true) {
            String menu = orderPresenter.present(newFilters);
            view.display(menu);
            String input = view.getUserInput().trim().toUpperCase();

            if (input.equals("Z")) {
                // Go back
                return newFilters;
            } else {
                try {
                    List<QueryParams> allowed = newFilters.category().getAllowedParams()
                            .stream()
                            .filter(p -> !p.name().startsWith("R_"))
                            .filter(p -> !p.name().equals("ID"))
                            .toList();

                    int index = Integer.parseInt(input);
                    if (index < 1 || index > allowed.size()) {
                        System.out.println(ConsoleUI.BRIGHT_RED + "Erreur, saisie invalide. Entrez un nombre entre 1 et " + allowed.size() + " ou z pour revenir en arrière.");
                        continue;
                    }

                    QueryParams selected = allowed.get(index - 1);

                    String directionMenu = orderDirectionPresenter.present(selected);
                    view.display(directionMenu);
                    String choice = view.getUserInput().trim().toUpperCase();

                    switch (choice) {
                        case "C" -> {
                            // Increasing
                            ArrayList<QueryParams> updated = new ArrayList<>(newFilters.orders());
                            updated.remove(QueryParams.valueOf("R_" + selected.name()));
                            updated.add(selected);
                            newFilters = selectFiltersUseCase.execute(new FiltersDTO(newFilters.category(),
                                    updated,
                                    newFilters.limit()));
                            return newFilters;
                        }
                        case "D" -> {
                            // Decreasing
                            ArrayList<QueryParams> updated = new ArrayList<>(newFilters.orders());
                            updated.remove(selected);
                            updated.add(QueryParams.valueOf("R_" + selected.name()));
                            newFilters = selectFiltersUseCase.execute(new FiltersDTO(newFilters.category(),
                                    updated,
                                    newFilters.limit()));
                            return newFilters;
                        }
                        case "R" -> {
                            // Remove
                            ArrayList<QueryParams> updated = newFilters.orders().stream()
                                    .filter(p -> !(p == selected || p.name().equals("R_" + selected.name())))
                                    .collect(Collectors.toCollection(ArrayList::new));
                            newFilters = selectFiltersUseCase.execute(new FiltersDTO(newFilters.category(),
                                    updated,
                                    newFilters.limit()));
                            return newFilters;
                        }
                        case "Z" -> {
                            // Go back
                            return newFilters;
                        }
                        default ->
                                view.display(ConsoleUI.BRIGHT_RED + "Erreur, saisie invalide. Entrez c pour croissant, d pour décroissant, r pour retirer le tri ou z pour revenir en arrière.");
                    }

                } catch (NumberFormatException e) {
                    view.display(ConsoleUI.BRIGHT_RED + "Erreur, saisie invalide. Entrez un nombre entre 1 et " + currentFilters.category().getAllowedParams().size() + " ou z pour revenir en arrière.");
                }
            }
        }
    }

    public void exportComputerList() {
        String path = exportPresenter.present();
        view.display(path);
        String choice = view.getUserInput().trim();

        if (choice.equalsIgnoreCase("z")) {
            view.display(ConsoleUI.BRIGHT_YELLOW + "Export annulé.\n");
            return;
        }

        try {
            Paths.get(choice);

            if (!choice.toLowerCase().endsWith(".json")) {
                choice = choice.replaceAll("\\.[^.]+$", "");
                choice += ".json";
            }

            exportComputersUseCase.execute(computerExporter, choice);
            view.display(ConsoleUI.BRIGHT_GREEN + "Export réussi vers : " + choice + "\n");

        } catch (InvalidPathException e) {
            view.display(ConsoleUI.BRIGHT_RED + "Erreur, chemin de fichier invalide.\n");
        } catch (Exception e) {
            view.display(ConsoleUI.BRIGHT_RED + "Erreur, export impossible : " + e.getMessage() + "\n");
        }
    }


}

