package fr.esiea.pcbuilder.presentation.presenters;

import fr.esiea.pcbuilder.application.dto.FiltersDTO;
import fr.esiea.pcbuilder.presentation.helpers.PresenterHelper;
import fr.esiea.pcbuilder.presentation.ui.ConsoleUI;

import java.util.stream.Collectors;

public class FilterPresenter {

    public String present(FiltersDTO filters) {
        PresenterHelper helper = new PresenterHelper();
        String currentOrders = filters.orders().isEmpty()
                ? "Aucun tri sélectionné"
                : filters.orders().stream()
                .map(helper::toFrenchName)
                .collect(Collectors.joining(", "));

        String menu;
        String mLine = ConsoleUI.LEFT_BORDER + ConsoleUI.BRIGHT_WHITE + "        Filtres actuels        " + ConsoleUI.RIGHT_BORDER;
        String tLine = ConsoleUI.LEFT_BORDER + ConsoleUI.BRIGHT_YELLOW + "t. " + ConsoleUI.BRIGHT_WHITE + "Modifier un tri             " + ConsoleUI.RIGHT_BORDER;
        String lLine = ConsoleUI.LEFT_BORDER + ConsoleUI.BRIGHT_YELLOW + "l. " + ConsoleUI.BRIGHT_WHITE + "Modifier la limite          " + ConsoleUI.RIGHT_BORDER;

        String cLine = ConsoleUI.BRIGHT_MAGENTA + "  Catégorie       : " + ConsoleUI.BRIGHT_WHITE + String.format("%s", filters.category().name()) + ConsoleUI.RESET + "\n" +
                ConsoleUI.BRIGHT_MAGENTA + "  Filtres actuels : " + ConsoleUI.BRIGHT_WHITE + String.format("%s", currentOrders) + ConsoleUI.RESET + "\n" +
                ConsoleUI.BRIGHT_MAGENTA + "  Limite actuelle : " + ConsoleUI.BRIGHT_WHITE + String.format("%d", filters.limit()) + ConsoleUI.RESET + "\n\n";

        menu = ConsoleUI.HEADER_PROMPT;
        menu += mLine;
        menu += ConsoleUI.FOOTER_LINE;
        menu += "\n" + cLine;
        menu += ConsoleUI.HEADER_LINE;
        menu += tLine;
        menu += lLine;
        menu += ConsoleUI.FOOTER_PROMPT;

        return menu;
    }


}
