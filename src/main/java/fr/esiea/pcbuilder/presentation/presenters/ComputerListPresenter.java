package fr.esiea.pcbuilder.presentation.presenters;

import fr.esiea.pcbuilder.application.dto.ComputerDTO;
import fr.esiea.pcbuilder.presentation.helpers.PresenterHelper;
import fr.esiea.pcbuilder.presentation.ui.ConsoleUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ComputerListPresenter {

    public String present(List<ComputerDTO> computerDTOList) {
        StringBuilder sb = new StringBuilder(header("Liste des Ordinateurs"));

        List<String> headers = List.of("N°", "CPU", "GPU", "Carte Mere", "RAM", "Stockage", "Alim", "Boitier");
        List<TableRenderer.Align> aligns = List.of(
                TableRenderer.Align.RIGHT,
                TableRenderer.Align.LEFT,
                TableRenderer.Align.LEFT,
                TableRenderer.Align.LEFT,
                TableRenderer.Align.LEFT,
                TableRenderer.Align.LEFT,
                TableRenderer.Align.LEFT,
                TableRenderer.Align.LEFT
        );

        List<List<String>> rows = new ArrayList<>();
        String empty = "Non defini";

        for (int i = 0; i < computerDTOList.size(); i++) {
            ComputerDTO dto = computerDTOList.get(i);

            boolean allEmpty =
                    dto.cpu() == null &&
                            dto.gpu() == null &&
                            dto.motherBoard() == null &&
                            dto.ram() == null &&
                            dto.storage() == null &&
                            dto.powerSupply() == null &&
                            dto.desktopCase() == null;

            if (allEmpty) continue;

            rows.add(List.of(
                    String.valueOf(i + 1),
                    dto.cpu() != null ? dto.cpu().name() : empty,
                    dto.gpu() != null ? dto.gpu().name() : empty,
                    dto.motherBoard() != null ? dto.motherBoard().name() : empty,
                    dto.ram() != null ? dto.ram().name() : empty,
                    dto.storage() != null ? dto.storage().name() : empty,
                    dto.powerSupply() != null ? dto.powerSupply().name() : empty,
                    dto.desktopCase() != null ? dto.desktopCase().name() : empty
            ));
        }

        if (rows.isEmpty()) {
            sb.append(ConsoleUI.DIM).append("\nAucun ordinateur disponible.\n\n").append(ConsoleUI.RESET);
        } else {
            sb.append(TableRenderer.render(headers, rows, aligns));
        }

        sb.append(menuFooter());
        return sb.toString();
    }

    private String header(String title) {
        PresenterHelper helper = new PresenterHelper();
        String menuTitle;

        String mLine = ConsoleUI.LEFT_BORDER + ConsoleUI.BRIGHT_WHITE + helper.center(title, 31) + ConsoleUI.RIGHT_BORDER;

        menuTitle = ConsoleUI.HEADER_PROMPT;
        menuTitle += mLine;
        menuTitle += ConsoleUI.FOOTER_LINE;
        menuTitle += ConsoleUI.RESET;

        return menuTitle;
    }

    private String menuFooter() {
        String aLine = ConsoleUI.LEFT_BORDER + ConsoleUI.BRIGHT_YELLOW + "a. " + ConsoleUI.BRIGHT_WHITE + "Ajouter un ordinateur       " + ConsoleUI.RIGHT_BORDER;

        String menu = ConsoleUI.HEADER_LINE;
        menu += aLine;
        menu += ConsoleUI.FOOTER_PROMPT;

        return menu;
    }

    // ===================== Table Renderer =====================
    static final class TableRenderer {

        static String render(List<String> headers, List<List<String>> rows, List<Align> aligns) {
            int colCount = headers.size();

            List<List<String>> safeRows = rows.stream()
                    .map(r -> {
                        List<String> out = new ArrayList<>(colCount);
                        for (int i = 0; i < colCount; i++) {
                            out.add(i < r.size() ? Objects.toString(r.get(i), "") : "");
                        }
                        return out;
                    })
                    .toList();

            int[] widths = new int[colCount];
            for (int i = 0; i < colCount; i++) {
                int max = len(headers.get(i));
                for (List<String> r : safeRows) max = Math.max(max, len(r.get(i)));
                widths[i] = max + 2; // +2 => 1 espace de chaque côté
            }

            StringBuilder sb = new StringBuilder();

            sb.append(ConsoleUI.BRIGHT_BLACK).append(line(widths)).append(ConsoleUI.RESET).append('\n');

            sb.append(ConsoleUI.BRIGHT_BLACK).append('|').append(ConsoleUI.RESET);
            for (int i = 0; i < widths.length; i++) {
                String cell = headers.get(i);
                int inner = widths[i] - 2;
                String content = align(cell, inner, aligns.get(i));
                sb.append(ConsoleUI.BOLD).append(ConsoleUI.BRIGHT_MAGENTA)
                        .append(' ').append(content).append(' ')
                        .append(ConsoleUI.RESET);
                sb.append(ConsoleUI.BRIGHT_BLACK).append('|').append(ConsoleUI.RESET);
            }
            sb.append('\n');

            sb.append(ConsoleUI.BRIGHT_BLACK).append(line(widths)).append(ConsoleUI.RESET).append('\n');

            for (List<String> r : safeRows) {
                sb.append(ConsoleUI.BRIGHT_BLACK).append('|').append(ConsoleUI.RESET);

                for (int i = 0; i < widths.length; i++) {
                    String cell = r.get(i);
                    int inner = widths[i] - 2;
                    String content = align(cell, inner, aligns.get(i));

                    if (i == 0) {
                        sb.append(ConsoleUI.BRIGHT_YELLOW)
                                .append(' ').append(content).append(' ')
                                .append(ConsoleUI.RESET);
                    } else if (cell.equals("Non defini")) {
                        sb.append(ConsoleUI.DIM)
                                .append(' ').append(content).append(' ')
                                .append(ConsoleUI.RESET);
                    } else {
                        sb.append(ConsoleUI.BRIGHT_WHITE)
                                .append(' ').append(content).append(' ')
                                .append(ConsoleUI.RESET);
                    }

                    sb.append(ConsoleUI.BRIGHT_BLACK).append('|').append(ConsoleUI.RESET);
                }
                sb.append('\n');
            }

            sb.append(ConsoleUI.BRIGHT_BLACK).append(line(widths)).append(ConsoleUI.RESET).append('\n');

            return sb.toString();
        }

        private static int len(String s) {
            return s == null ? 0 : s.length();
        }

        private static String line(int[] widths) {
            StringBuilder sb = new StringBuilder();
            sb.append('+');
            for (int w : widths) {
                sb.append("-".repeat(w));
                sb.append('+');
            }
            return sb.toString();
        }

        private static String align(String s, int width, Align a) {
            if (s.length() >= width) return s.substring(0, width);
            int pad = width - s.length();
            return switch (a) {
                case LEFT -> s + " ".repeat(pad);
                case RIGHT -> " ".repeat(pad) + s;
            };
        }

        enum Align {LEFT, RIGHT}
    }
}