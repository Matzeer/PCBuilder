package fr.esiea.pcbuilder.infrastructure.presentation.presenters;

import fr.esiea.pcbuilder.application.dto.ComputerDTO;
import fr.esiea.pcbuilder.infrastructure.presentation.ui.ConsoleUI;


public class ComputerPresenter {
    public String present(ComputerDTO computer) {
        String emptyField = "Non sélectionné";
        String menu;
        String sLine = ConsoleUI.LEFT_BORDER + ConsoleUI.BRIGHT_YELLOW + "s. " + ConsoleUI.BRIGHT_WHITE + "Sauvegarder                 " + ConsoleUI.RIGHT_BORDER;
        String cLine = ConsoleUI.LEFT_BORDER + ConsoleUI.BRIGHT_YELLOW + "c. " + ConsoleUI.BRIGHT_WHITE + "Charger                     " + ConsoleUI.RIGHT_BORDER;
        String mLine = ConsoleUI.LEFT_BORDER + ConsoleUI.BRIGHT_WHITE + "       Configuration PC        " + ConsoleUI.RIGHT_BORDER;

        String line = ConsoleUI.BRIGHT_YELLOW + "  1. " + ConsoleUI.BRIGHT_MAGENTA + " Cpu          : " + ConsoleUI.BRIGHT_WHITE + String.format("%s\n", computer.cpu() == null ? emptyField : computer.cpu().name()) +
                ConsoleUI.BRIGHT_YELLOW + "  2. " + ConsoleUI.BRIGHT_MAGENTA + " Gpu          : " + ConsoleUI.BRIGHT_WHITE + String.format("%s\n", computer.gpu() == null ? emptyField : computer.gpu().name()) +
                ConsoleUI.BRIGHT_YELLOW + "  3. " + ConsoleUI.BRIGHT_MAGENTA + " Ram          : " + ConsoleUI.BRIGHT_WHITE + String.format("%s\n", computer.ram() == null ? emptyField : computer.ram().name()) +
                ConsoleUI.BRIGHT_YELLOW + "  4. " + ConsoleUI.BRIGHT_MAGENTA + " Stokage      : " + ConsoleUI.BRIGHT_WHITE + String.format("%s\n", computer.storage() == null ? emptyField : computer.storage().name()) +
                ConsoleUI.BRIGHT_YELLOW + "  5. " + ConsoleUI.BRIGHT_MAGENTA + " Carte Mère   : " + ConsoleUI.BRIGHT_WHITE + String.format("%s\n", computer.motherBoard() == null ? emptyField : computer.motherBoard().name()) +
                ConsoleUI.BRIGHT_YELLOW + "  6. " + ConsoleUI.BRIGHT_MAGENTA + " Alimentation : " + ConsoleUI.BRIGHT_WHITE + String.format("%s\n", computer.powerSupply() == null ? emptyField : computer.powerSupply().name()) +
                ConsoleUI.BRIGHT_YELLOW + "  7. " + ConsoleUI.BRIGHT_MAGENTA + " Boitier      : " + ConsoleUI.BRIGHT_WHITE + String.format("%s\n", computer.desktopCase() == null ? emptyField : computer.desktopCase().name());

        menu = ConsoleUI.HEADER_PROMPT;
        menu += mLine;
        menu += ConsoleUI.FOOTER_LINE + "\n";

        menu += line + "\n";

        menu += ConsoleUI.HEADER_LINE;
        menu += sLine + cLine;
        menu += ConsoleUI.FOOTER_PROMPT;

        return menu;
    }
}
