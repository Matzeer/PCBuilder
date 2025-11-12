package fr.esiea.pcbuilder.presentation.presenters;

import fr.esiea.pcbuilder.presentation.ui.ConsoleUI;

public class ExportPresenter {

    public String present() {
        String menu;
        String mLine = ConsoleUI.LEFT_BORDER + ConsoleUI.BRIGHT_WHITE + "       Exporter les PCs        " + ConsoleUI.RIGHT_BORDER;
        String sLine = ConsoleUI.LEFT_BORDER + ConsoleUI.BRIGHT_WHITE + "Entrez le nom du fichier JSON  " + ConsoleUI.RIGHT_BORDER;
        String eLine = ConsoleUI.LEFT_BORDER + ConsoleUI.DIM + "Exemple : pcs.json             " + ConsoleUI.RIGHT_BORDER;

        menu = ConsoleUI.HEADER_PROMPT;
        menu += mLine;
        menu += ConsoleUI.MID_LINE;
        menu += sLine;
        menu += eLine;
        menu += ConsoleUI.FOOTER_PROMPT;

        return menu;
    }

}
