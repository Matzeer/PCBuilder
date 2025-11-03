package fr.esiea.pcbuilder.infrastructure.presentation.presenters;

import fr.esiea.pcbuilder.application.dto.FiltersDTO;
import fr.esiea.pcbuilder.infrastructure.presentation.ui.ConsoleUI;

public class LimitPresenter {

    public String present(FiltersDTO filters) {
        String menu;
        String mLine = ConsoleUI.LEFT_BORDER + ConsoleUI.BRIGHT_WHITE + "     Choix du sens de tri      " + ConsoleUI.RIGHT_BORDER;
        String lLine = ConsoleUI.BRIGHT_MAGENTA + "  Limite actuelle : " + ConsoleUI.BRIGHT_WHITE + String.format("%d", filters.limit()) + ConsoleUI.RESET + "\n\n";

        menu = ConsoleUI.HEADER_PROMPT;
        menu += mLine;
        menu += ConsoleUI.FOOTER_LINE + "\n";
        menu += lLine;
        menu += ConsoleUI.HEADER_LINE;
        menu += ConsoleUI.LEFT_BORDER + ConsoleUI.BRIGHT_YELLOW + "z. " + ConsoleUI.BRIGHT_WHITE + "Retour au menu précédent    " + ConsoleUI.RIGHT_BORDER;
        menu += ConsoleUI.FOOTER_LINE;
        menu += ConsoleUI.CHOICE_PROMPT;

        return menu;
    }
}
