package fr.esiea.pcbuilder.infrastructure.presentation.ui;

public class ConsoleUI {
    public static final String RESET = "\033[0m";
    public static final String BOLD = "\033[1m";
    public static final String DIM = "\033[2m";

    public static final String BRIGHT_BLACK = "\033[90m";
    public static final String BRIGHT_RED = "\033[91m";
    public static final String BRIGHT_GREEN = "\033[92m";
    public static final String BRIGHT_YELLOW = "\033[93m";
    public static final String BRIGHT_MAGENTA = "\033[95m";
    public static final String BRIGHT_CYAN = "\033[96m";
    public static final String BRIGHT_WHITE = "\033[97m";

    public static final String HEADER_LINE = BRIGHT_CYAN + "╔════════════════════════════════╗\n";
    public static final String HEADER_PROMPT = BRIGHT_CYAN + "╔════════════════════════════════╗\n║           PC Builder           ║\n";
    public static final String MID_LINE = BRIGHT_CYAN + "╠════════════════════════════════╣\n";
    public static final String FOOTER_LINE = BRIGHT_CYAN + "╚════════════════════════════════╝\n";
    public static final String CHOICE_PROMPT = BRIGHT_WHITE + "Votre choix : " + RESET;
    public static final String LEFT_BORDER = BRIGHT_CYAN + "║ " + RESET;
    public static final String RIGHT_BORDER = BRIGHT_CYAN + "║\n" + RESET;
    public static final String FOOTER_PROMPT = MID_LINE + LEFT_BORDER + BRIGHT_YELLOW + "z. " + BRIGHT_WHITE + "Retour au menu précédent    " + RIGHT_BORDER + FOOTER_LINE + CHOICE_PROMPT;
}
