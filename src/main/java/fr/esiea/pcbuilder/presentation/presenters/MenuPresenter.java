package fr.esiea.pcbuilder.presentation.presenters;

import fr.esiea.pcbuilder.presentation.ui.ConsoleUI;

public class MenuPresenter {
    public String present() {
        String menu;

        String mLine = ConsoleUI.LEFT_BORDER + ConsoleUI.BRIGHT_YELLOW + ConsoleUI.BRIGHT_WHITE + "        Menu principal         " + ConsoleUI.RIGHT_BORDER;
        String cLine = ConsoleUI.LEFT_BORDER + ConsoleUI.BRIGHT_YELLOW + "c." + ConsoleUI.BRIGHT_WHITE + " Configurer son PC           " + ConsoleUI.RIGHT_BORDER;
        String eLine = ConsoleUI.LEFT_BORDER + ConsoleUI.BRIGHT_YELLOW + "e." + ConsoleUI.BRIGHT_WHITE + " Exporter ses PCs            " + ConsoleUI.RIGHT_BORDER;
        String zLine = ConsoleUI.LEFT_BORDER + ConsoleUI.BRIGHT_YELLOW + "z." + ConsoleUI.BRIGHT_WHITE + " Quitter                     " + ConsoleUI.RIGHT_BORDER;

        menu = ConsoleUI.HEADER_PROMPT;
        menu += mLine;
        menu += ConsoleUI.MID_LINE;
        menu += cLine;
        menu += eLine;
        menu += ConsoleUI.MID_LINE;
        menu += zLine;
        menu += ConsoleUI.FOOTER_LINE;
        menu += ConsoleUI.CHOICE_PROMPT;
        return menu;
    }
}
