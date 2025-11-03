package fr.esiea.pcbuilder.infrastructure.presentation.helpers;

import fr.esiea.pcbuilder.shared.enums.QueryParams;

public class PresenterHelper {
    public String toFrenchName(QueryParams param) {
        return switch (param) {
            case NAME -> "Nom croissant";
            case PRICE -> "Prix croissant";
            case GRADE -> "Note croissant";
            case CORECOUNT -> "Nombre de cœurs croissant";
            case CORECLOCK -> "Fréquence de base croissant";
            case BOOSTCLOCK -> "Fréquence boost croissant";
            case TDP -> "Consommation (TDP) croissant";
            case GRAPHICS -> "Carte graphique intégrée croissant";
            case SMT -> "SMT croissant";
            case SOCKET -> "Socket croissant";
            case FORMFACTOR -> "Format croissant";
            case MAXMEMORY -> "Mémoire max croissant";
            case MEMORYSLOTS -> "Slots mémoire croissant";
            case COLOR -> "Couleur croissant";
            case SPEED0 -> "Vitesse module 1 croissant";
            case SPEED1 -> "Vitesse module 2 croissant";
            case MODULE0 -> "Taille module 1 croissant";
            case MODULE1 -> "Taille module 2 croissant";
            case PRICEPERGB -> "Prix par Go croissant";
            case FIRSTWORDLATENCY -> "Latence premier mot croissant";
            case CASLATENCY -> "Latence CAS croissant";
            case CAPACITY -> "Capacité croissant";
            case STORAGETYPE -> "Type de stockage croissant";
            case CACHE -> "Cache croissant";
            case STORAGEINTERFACE -> "Interface croissant";
            case CHIPSET -> "Chipset croissant";
            case MEMORY -> "Mémoire vidéo croissant";
            case LENGTH -> "Longueur croissant";
            case EFFICIENCY -> "Efficacité croissant";
            case WATTAGE -> "Puissance (W) croissant";
            case MODULAR -> "Modularité croissant";
            case PSU -> "Alimentation incluse croissant";
            case SIDEPANEL -> "Panneau latéral croissant";
            case EXTERNAL525BAYS -> "Baies 5.25\" croissant";
            case INTERNAL35BAYS -> "Baies 3.5\" croissant";
            case R_NAME -> "Nom decroissant";
            case R_PRICE -> "Prix decroissant";
            case R_GRADE -> "Note decroissant";
            case R_CORECOUNT -> "Nombre de cœurs decroissant";
            case R_CORECLOCK -> "Fréquence de base decroissant";
            case R_BOOSTCLOCK -> "Fréquence boost decroissant";
            case R_TDP -> "Consommation (TDP) decroissant";
            case R_GRAPHICS -> "Carte graphique intégrée decroissant";
            case R_SMT -> "SMT decroissant";
            case R_SOCKET -> "Socket decroissant";
            case R_FORMFACTOR -> "Format decroissant";
            case R_MAXMEMORY -> "Mémoire max decroissant";
            case R_MEMORYSLOTS -> "Slots mémoire decroissant";
            case R_COLOR -> "Couleur decroissant";
            case R_SPEED0 -> "Vitesse module 1 decroissant";
            case R_SPEED1 -> "Vitesse module 2 decroissant";
            case R_MODULE0 -> "Taille module 1 decroissant";
            case R_MODULE1 -> "Taille module 2 decroissant";
            case R_PRICEPERGB -> "Prix par Go decroissant";
            case R_FIRSTWORDLATENCY -> "Latence premier mot decroissant";
            case R_CASLATENCY -> "Latence CAS decroissant";
            case R_CAPACITY -> "Capacité decroissant";
            case R_STORAGETYPE -> "Type de stockage decroissant";
            case R_CACHE -> "Cache decroissant";
            case R_STORAGEINTERFACE -> "Interface decroissant";
            case R_CHIPSET -> "Chipset decroissant";
            case R_MEMORY -> "Mémoire vidéo decroissant";
            case R_LENGTH -> "Longueur decroissant";
            case R_EFFICIENCY -> "Efficacité decroissant";
            case R_WATTAGE -> "Puissance (W) decroissant";
            case R_MODULAR -> "Modularité decroissant";
            case R_PSU -> "Alimentation incluse decroissant";
            case R_SIDEPANEL -> "Panneau latéral decroissant";
            case R_EXTERNAL525BAYS -> "Baies 5.25 decroissant";
            case R_INTERNAL35BAYS -> "Baies 3.5 decroissant";
            default -> param.name();
        };
    }

    // Centers a string within a given width by padding with spaces
    public String center(String s, int width) {
        int pad = width - s.length();
        int left = pad / 2;
        int right = pad - left;

        if (s.length() >= width) return s.substring(0, width);

        return " ".repeat(left) + s + " ".repeat(right);
    }
}
