package fr.esiea.pcbuilder.presentation.presenters;

import fr.esiea.pcbuilder.application.dto.FiltersDTO;
import fr.esiea.pcbuilder.presentation.ui.ConsoleUI;
import fr.esiea.pcbuilder.shared.enums.QueryParams;

import java.util.List;
import java.util.stream.Collectors;

public class OrderPresenter {

    public String present(FiltersDTO filters) {
        List<QueryParams> allowed = filters.category().getAllowedParams()
                .stream()
                .filter(p -> !p.name().startsWith("R_"))
                .filter(p -> !p.name().equals("ID"))
                .toList();

        String currentOrders = filters.orders().isEmpty()
                ? "Aucun tri sélectionné"
                : filters.orders().stream()
                .map(this::toFrenchName)
                .collect(Collectors.joining(", "));

        StringBuilder menu = new StringBuilder();
        String mLine = ConsoleUI.LEFT_BORDER + ConsoleUI.BRIGHT_WHITE + "       Sélection du tri        " + ConsoleUI.RIGHT_BORDER;
        String aLine = ConsoleUI.BRIGHT_MAGENTA + "  Tri(s) actuel(s) : " + ConsoleUI.BRIGHT_WHITE + String.format("%s", currentOrders) + ConsoleUI.RESET + "\n\n";

        menu.append(ConsoleUI.HEADER_PROMPT);
        menu.append(mLine);
        menu.append(ConsoleUI.FOOTER_LINE + "\n");
        menu.append(aLine);

        int index = 1;
        for (QueryParams param : allowed) {
            menu.append(ConsoleUI.BRIGHT_YELLOW).append(String.format("  %-2d. ", index++)).append(ConsoleUI.BRIGHT_WHITE).append(String.format("%s%n", toFrenchName(param)));
        }

        menu.append("\n");
        menu.append(ConsoleUI.HEADER_LINE);
        menu.append(ConsoleUI.LEFT_BORDER + ConsoleUI.BRIGHT_YELLOW + "z. " + ConsoleUI.BRIGHT_WHITE + "Retour au menu précédent    " + ConsoleUI.RIGHT_BORDER);
        menu.append(ConsoleUI.FOOTER_LINE);
        menu.append(ConsoleUI.CHOICE_PROMPT);

        return menu.toString();
    }

    private String toFrenchName(QueryParams param) {
        return switch (param) {
            case NAME -> "Nom";
            case PRICE -> "Prix";
            case GRADE -> "Note";
            case CORECOUNT -> "Nombre de cœurs";
            case CORECLOCK -> "Fréquence de base";
            case BOOSTCLOCK -> "Fréquence boost";
            case TDP -> "Consommation (TDP)";
            case GRAPHICS -> "Carte graphique intégrée";
            case SMT -> "SMT";
            case SOCKET -> "Socket";
            case FORMFACTOR -> "Format";
            case MAXMEMORY -> "Mémoire max";
            case MEMORYSLOTS -> "Slots mémoire";
            case COLOR -> "Couleur";
            case SPEED0 -> "Vitesse module 1";
            case SPEED1 -> "Vitesse module 2";
            case MODULE0 -> "Taille module 1";
            case MODULE1 -> "Taille module 2";
            case PRICEPERGB -> "Prix par Go";
            case FIRSTWORDLATENCY -> "Latence premier mot";
            case CASLATENCY -> "Latence CAS";
            case CAPACITY -> "Capacité";
            case STORAGETYPE -> "Type de stockage";
            case CACHE -> "Cache";
            case STORAGEINTERFACE -> "Interface";
            case CHIPSET -> "Chipset";
            case MEMORY -> "Mémoire vidéo";
            case LENGTH -> "Longueur";
            case EFFICIENCY -> "Efficacité";
            case WATTAGE -> "Puissance (W)";
            case MODULAR -> "Modularité";
            case PSU -> "Alimentation incluse";
            case SIDEPANEL -> "Panneau latéral";
            case EXTERNAL525BAYS -> "Baies 5.25\"";
            case INTERNAL35BAYS -> "Baies 3.5\"";
            default -> param.name();
        };
    }
}
