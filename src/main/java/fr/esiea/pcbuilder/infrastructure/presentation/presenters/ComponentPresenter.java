package fr.esiea.pcbuilder.infrastructure.presentation.presenters;

import fr.esiea.pcbuilder.application.dto.*;
import fr.esiea.pcbuilder.infrastructure.presentation.helpers.PresenterHelper;
import fr.esiea.pcbuilder.infrastructure.presentation.ui.ConsoleUI;
import fr.esiea.pcbuilder.shared.enums.Categories;
import fr.esiea.pcbuilder.shared.enums.QueryParams;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ComponentPresenter {

    private static String nz(String s) {
        return Objects.toString(s, "");
    }

    public String present(Categories category, ArrayList<ComponentDTO> components, FiltersDTO filters) {
        return switch (category) {
            case CPU -> displayCpuList(components, filters);
            case MOTHERBOARD -> displayMotherBoardList(components, filters);
            case MEMORY -> displayMemoryList(components, filters);
            case INTERNAL_HARD_DRIVE -> displayStorageList(components, filters);
            case VIDEO_CARD -> displayGpuList(components, filters);
            case CASE -> displayCaseList(components, filters);
            case POWER_SUPPLY -> displayPowerSupplyList(components, filters);
            default -> "Catégorie inconnue";
        };
    }

    // ===================== CPU =====================
    private String displayCpuList(ArrayList<ComponentDTO> components, FiltersDTO filters) {
        StringBuilder sb = new StringBuilder(header("Liste des CPUs"));

        List<String> headers = List.of(
                "N°", "Nom", "Prix(€)", "Note", "Cœurs", "Base(GHz)", "Boost(GHz)", "TDP", "iGPU", "SMT"
        );
        List<TableRenderer.Align> aligns = List.of(
                TableRenderer.Align.RIGHT, TableRenderer.Align.LEFT, TableRenderer.Align.RIGHT, TableRenderer.Align.RIGHT,
                TableRenderer.Align.RIGHT, TableRenderer.Align.RIGHT, TableRenderer.Align.RIGHT, TableRenderer.Align.RIGHT,
                TableRenderer.Align.LEFT, TableRenderer.Align.LEFT
        );

        List<List<String>> rows = new ArrayList<>();
        int index = 1;
        for (ComponentDTO c : components) {
            if (c instanceof CpuDTO cpu) {
                rows.add(List.of(
                        String.valueOf(index++),
                        nz(cpu.name()),
                        String.format("%.2f", cpu.price()),
                        String.format("%.1f", cpu.grade()),
                        String.valueOf(cpu.coreCount()),
                        String.format("%.2f", cpu.coreClock()),
                        String.format("%.2f", cpu.boostClock()),
                        String.valueOf(cpu.tdp()),
                        nz(cpu.graphics()),
                        cpu.smt() ? "oui" : "non"
                ));
            }
        }

        sb.append(TableRenderer.render(headers, rows, aligns));
        sb.append(sortFooter(filters.orders()));
        return sb.toString();
    }

    // ===================== GPU =====================
    private String displayGpuList(ArrayList<ComponentDTO> components, FiltersDTO filters) {
        StringBuilder sb = new StringBuilder(header("Liste des Cartes Graphiques"));

        List<String> headers = List.of(
                "N°", "Nom", "Prix(€)", "Note", "Chipset", "Mémoire(Mo)", "Base(MHz)", "Boost(MHz)", "Couleur", "Long.(cm)"
        );
        List<TableRenderer.Align> aligns = List.of(
                TableRenderer.Align.RIGHT, TableRenderer.Align.LEFT, TableRenderer.Align.RIGHT, TableRenderer.Align.RIGHT,
                TableRenderer.Align.LEFT, TableRenderer.Align.RIGHT, TableRenderer.Align.RIGHT, TableRenderer.Align.RIGHT,
                TableRenderer.Align.LEFT, TableRenderer.Align.RIGHT
        );

        List<List<String>> rows = new ArrayList<>();
        int index = 1;
        for (ComponentDTO c : components) {
            if (c instanceof GpuDTO g) {
                rows.add(List.of(
                        String.valueOf(index++),
                        nz(g.name()),
                        String.format("%.2f", g.price()),
                        String.format("%.1f", g.grade()),
                        nz(g.chipset()),
                        String.valueOf(g.memory()),
                        String.valueOf(g.coreClock()),
                        String.valueOf(g.boostClock()),
                        nz(g.color()),
                        String.valueOf(g.length() / 10) //Convert mm to cm
                ));
            }
        }

        sb.append(TableRenderer.render(headers, rows, aligns));
        sb.append(sortFooter(filters.orders()));
        return sb.toString();
    }

    // ===================== MOTHERBOARD =====================
    private String displayMotherBoardList(ArrayList<ComponentDTO> components, FiltersDTO filters) {
        StringBuilder sb = new StringBuilder(header("Liste des Cartes Mères"));

        List<String> headers = List.of(
                "N°", "Nom", "Prix(€)", "Note", "Socket", "FormFactor", "MaxRAM", "Slots", "Couleur"
        );
        List<TableRenderer.Align> aligns = List.of(
                TableRenderer.Align.RIGHT, TableRenderer.Align.LEFT, TableRenderer.Align.RIGHT, TableRenderer.Align.RIGHT,
                TableRenderer.Align.LEFT, TableRenderer.Align.LEFT, TableRenderer.Align.RIGHT, TableRenderer.Align.RIGHT,
                TableRenderer.Align.LEFT
        );

        List<List<String>> rows = new ArrayList<>();
        int index = 1;
        for (ComponentDTO c : components) {
            if (c instanceof MotherBoardDTO mb) {
                rows.add(List.of(
                        String.valueOf(index++),
                        nz(mb.name()),
                        String.format("%.2f", mb.price()),
                        String.format("%.1f", mb.grade()),
                        nz(mb.socket()),
                        nz(mb.formFactor()),
                        String.valueOf(mb.maxMemory()),
                        String.valueOf(mb.memorySlots()),
                        nz(mb.color())
                ));
            }
        }

        sb.append(TableRenderer.render(headers, rows, aligns));
        sb.append(sortFooter(filters.orders()));
        return sb.toString();
    }

    // ===================== RAM =====================
    private String displayMemoryList(ArrayList<ComponentDTO> components, FiltersDTO filters) {
        StringBuilder sb = new StringBuilder(header("Liste des RAMs"));

        List<String> headers = List.of(
                "N°", "Nom", "Prix(€)", "Note", "V1(MHz)", "V2(MHz)", "M1", "M2", "€/Go", "Couleur", "FWL", "CAS"
        );
        List<TableRenderer.Align> aligns = List.of(
                TableRenderer.Align.RIGHT, TableRenderer.Align.LEFT, TableRenderer.Align.RIGHT, TableRenderer.Align.RIGHT,
                TableRenderer.Align.RIGHT, TableRenderer.Align.RIGHT, TableRenderer.Align.RIGHT, TableRenderer.Align.RIGHT,
                TableRenderer.Align.RIGHT, TableRenderer.Align.LEFT, TableRenderer.Align.RIGHT, TableRenderer.Align.RIGHT
        );

        List<List<String>> rows = new ArrayList<>();
        int index = 1;
        for (ComponentDTO c : components) {
            if (c instanceof RamDTO r) {
                rows.add(List.of(
                        String.valueOf(index++),
                        nz(r.name()),
                        String.format("%.2f", r.price()),
                        String.format("%.1f", r.grade()),
                        String.valueOf(r.speed0()),
                        String.valueOf(r.speed1()),
                        String.valueOf(r.module0()),
                        String.valueOf(r.module1()),
                        String.valueOf(r.pricePerGb()),
                        nz(r.color()),
                        String.valueOf(r.firstWordLatency()),
                        String.valueOf(r.casLatency())
                ));
            }
        }

        sb.append(TableRenderer.render(headers, rows, aligns));
        sb.append(sortFooter(filters.orders()));
        return sb.toString();
    }

    // ===================== STORAGE =====================
    private String displayStorageList(ArrayList<ComponentDTO> components, FiltersDTO filters) {
        StringBuilder sb = new StringBuilder(header("Liste des Disques Internes"));

        List<String> headers = List.of(
                "N°", "Nom", "Prix(€)", "Note", "Cap.(Go)", "€/Go", "Type", "Cache", "Form", "Interface"
        );
        List<TableRenderer.Align> aligns = List.of(
                TableRenderer.Align.RIGHT, TableRenderer.Align.LEFT, TableRenderer.Align.RIGHT, TableRenderer.Align.RIGHT,
                TableRenderer.Align.RIGHT, TableRenderer.Align.RIGHT, TableRenderer.Align.LEFT, TableRenderer.Align.RIGHT,
                TableRenderer.Align.LEFT, TableRenderer.Align.LEFT
        );

        List<List<String>> rows = new ArrayList<>();
        int index = 1;
        for (ComponentDTO c : components) {
            if (c instanceof StorageDTO st) {
                rows.add(List.of(
                        String.valueOf(index++),
                        nz(st.name()),
                        String.format("%.2f", st.price()),
                        String.format("%.1f", st.grade()),
                        String.valueOf(st.capacity()),
                        String.format("%.2f", st.pricePerGb()),
                        nz(st.storageType()),
                        String.valueOf(st.cache()),
                        nz(st.formFactor()),
                        nz(st.storageInterface())
                ));
            }
        }

        sb.append(TableRenderer.render(headers, rows, aligns));
        sb.append(sortFooter(filters.orders()));
        return sb.toString();
    }

    // ===================== CASE =====================
    private String displayCaseList(ArrayList<ComponentDTO> components, FiltersDTO filters) {
        StringBuilder sb = new StringBuilder(header("Liste des Boîtiers"));

        List<String> headers = List.of(
                "N°", "Nom", "Prix(€)", "Note", "Couleur", "PSU", "SidePanel", "Ext 5\"25", "Int 3\"5"
        );
        List<TableRenderer.Align> aligns = List.of(
                TableRenderer.Align.RIGHT, TableRenderer.Align.LEFT, TableRenderer.Align.RIGHT, TableRenderer.Align.RIGHT,
                TableRenderer.Align.LEFT, TableRenderer.Align.LEFT, TableRenderer.Align.LEFT,
                TableRenderer.Align.RIGHT, TableRenderer.Align.RIGHT
        );

        List<List<String>> rows = new ArrayList<>();
        int index = 1;
        for (ComponentDTO c : components) {
            if (c instanceof CaseDTO cs) {
                rows.add(List.of(
                        String.valueOf(index++),
                        nz(cs.name()),
                        String.format("%.2f", cs.price()),
                        String.format("%.1f", cs.grade()),
                        nz(cs.color()),
                        nz(cs.psu()),
                        nz(cs.sidePanel()),
                        String.valueOf(cs.external525Bays()),
                        String.valueOf(cs.internal35Bays())
                ));
            }
        }

        sb.append(TableRenderer.render(headers, rows, aligns));
        sb.append(sortFooter(filters.orders()));
        return sb.toString();
    }

    // ===================== POWER SUPPLY =====================
    private String displayPowerSupplyList(ArrayList<ComponentDTO> components, FiltersDTO filters) {
        StringBuilder sb = new StringBuilder(header("Liste des Alimentations"));

        List<String> headers = List.of(
                "N°", "Nom", "Prix(€)", "Note", "Efficacité", "Wattage", "Modulaire", "Couleur"
        );
        List<TableRenderer.Align> aligns = List.of(
                TableRenderer.Align.RIGHT, TableRenderer.Align.LEFT, TableRenderer.Align.RIGHT, TableRenderer.Align.RIGHT,
                TableRenderer.Align.LEFT, TableRenderer.Align.RIGHT, TableRenderer.Align.LEFT, TableRenderer.Align.LEFT
        );

        List<List<String>> rows = new ArrayList<>();
        int index = 1;
        for (ComponentDTO c : components) {
            if (c instanceof PowerSupplyDTO psu) {
                rows.add(List.of(
                        String.valueOf(index++),
                        nz(psu.name()),
                        String.format("%.2f", psu.price()),
                        String.format("%.1f", psu.grade()),
                        nz(psu.efficiency()),
                        String.valueOf(psu.wattage()),
                        nz(psu.modular()),
                        nz(psu.color())
                ));
            }
        }

        sb.append(TableRenderer.render(headers, rows, aligns));
        sb.append(sortFooter(filters.orders()));
        return sb.toString();
    }

    // ===================== Helpers =====================
    private String header(String title) {
        PresenterHelper helper = new PresenterHelper();
        String menuTitle;

        String mLine = ConsoleUI.LEFT_BORDER + ConsoleUI.BRIGHT_WHITE + helper.center("Liste des composants", 31) + ConsoleUI.RIGHT_BORDER;

        menuTitle = ConsoleUI.HEADER_PROMPT;
        menuTitle += mLine;
        menuTitle += ConsoleUI.FOOTER_LINE;
        menuTitle += ConsoleUI.RESET + "\n";

        return menuTitle;
    }

    private String sortFooter(ArrayList<QueryParams> currentSorts) {
        PresenterHelper helper = new PresenterHelper();
        String tri = "";
        if (currentSorts.isEmpty()) {
            tri = "Aucun tri appliqué";
        } else {
            tri = currentSorts.stream()
                    .map(helper::toFrenchName)
                    .collect(Collectors.joining(", "));
        }

        String fLine = ConsoleUI.LEFT_BORDER + ConsoleUI.BRIGHT_YELLOW + "f. " + ConsoleUI.BRIGHT_WHITE + "Modifier les filtres        " + ConsoleUI.RIGHT_BORDER;

        String menu = ConsoleUI.BRIGHT_MAGENTA + "Tri actuel : " + ConsoleUI.BRIGHT_WHITE + tri + "\n\n" + ConsoleUI.RESET;
        menu += ConsoleUI.HEADER_LINE;
        menu += fLine;
        menu += ConsoleUI.FOOTER_PROMPT;
        return menu;
    }

    private String renderParams(EnumSet<QueryParams> params) {
        List<String> tokens = params.stream().map(Enum::name).collect(Collectors.toList());
        return String.join(", ", tokens);
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
                widths[i] = max + 2;
            }

            StringBuilder sb = new StringBuilder();

            sb.append(ConsoleUI.RESET).append(line(widths)).append(ConsoleUI.RESET).append('\n');

            sb.append(ConsoleUI.RESET).append('|').append(ConsoleUI.RESET);
            for (int i = 0; i < widths.length; i++) {
                String cell = headers.get(i);
                int inner = widths[i] - 2;
                String content = align(cell, inner, aligns.get(i));
                sb.append(ConsoleUI.BOLD).append(ConsoleUI.BRIGHT_MAGENTA)
                        .append(' ').append(content).append(' ')
                        .append(ConsoleUI.RESET);
                sb.append(ConsoleUI.RESET).append('|').append(ConsoleUI.RESET);
            }
            sb.append('\n');

            sb.append(ConsoleUI.RESET).append(line(widths)).append(ConsoleUI.RESET).append('\n');

            for (List<String> r : safeRows) {
                sb.append(ConsoleUI.RESET).append('|').append(ConsoleUI.RESET);

                for (int i = 0; i < widths.length; i++) {
                    String cell = r.get(i);
                    int inner = widths[i] - 2;
                    String content = align(cell, inner, aligns.get(i));

                    if (i == 0) {
                        sb.append(ConsoleUI.BRIGHT_YELLOW)
                                .append(' ').append(content).append(' ')
                                .append(ConsoleUI.RESET);
                    } else {
                        sb.append(ConsoleUI.BRIGHT_WHITE)
                                .append(' ').append(content).append(' ')
                                .append(ConsoleUI.RESET);
                    }

                    sb.append(ConsoleUI.RESET).append('|').append(ConsoleUI.RESET);
                }
                sb.append('\n');
            }

            sb.append(ConsoleUI.RESET).append(line(widths)).append(ConsoleUI.RESET).append('\n');

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