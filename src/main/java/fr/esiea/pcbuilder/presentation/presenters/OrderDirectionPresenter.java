package fr.esiea.pcbuilder.presentation.presenters;

import fr.esiea.pcbuilder.presentation.ui.ConsoleUI;
import fr.esiea.pcbuilder.shared.enums.QueryParams;

public class OrderDirectionPresenter {

    public String present(QueryParams selectedParam) {

        String menu;
        String mLine = ConsoleUI.LEFT_BORDER + ConsoleUI.BRIGHT_WHITE + "     Choix du sens de tri      " + ConsoleUI.RIGHT_BORDER;
        String pLine = ConsoleUI.BRIGHT_MAGENTA + "  Paramètre sélectionné : " + ConsoleUI.BRIGHT_WHITE + String.format("%s", toFrenchName(selectedParam)) + ConsoleUI.RESET + "\n\n";
        String cLine = ConsoleUI.LEFT_BORDER + ConsoleUI.BRIGHT_YELLOW + "c. " + ConsoleUI.BRIGHT_WHITE + "Croissant                   " + ConsoleUI.RIGHT_BORDER +
                ConsoleUI.LEFT_BORDER + ConsoleUI.BRIGHT_YELLOW + "d. " + ConsoleUI.BRIGHT_WHITE + "Décroissant                 " + ConsoleUI.RIGHT_BORDER +
                ConsoleUI.LEFT_BORDER + ConsoleUI.BRIGHT_YELLOW + "r. " + ConsoleUI.BRIGHT_WHITE + "Retirer le tri              " + ConsoleUI.RIGHT_BORDER;

        menu = ConsoleUI.HEADER_PROMPT;
        menu += mLine;
        menu += ConsoleUI.FOOTER_LINE + "\n";
        menu += pLine;
        menu += ConsoleUI.HEADER_LINE;
        menu += cLine;
        menu += ConsoleUI.FOOTER_PROMPT;

        return menu;
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
            case EXTERNAL525BAYS -> "Baies 5.25";
            case INTERNAL35BAYS -> "Baies 3.5";
            default -> param.name();
        };
    }
}
