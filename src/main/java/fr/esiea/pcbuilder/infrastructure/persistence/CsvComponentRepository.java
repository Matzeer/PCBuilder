package fr.esiea.pcbuilder.infrastructure.persistence;

import fr.esiea.pcbuilder.application.dto.*;
import fr.esiea.pcbuilder.application.repositories.ComponentGateway;
import fr.esiea.pcbuilder.domain.entities.*;
import fr.esiea.pcbuilder.shared.enums.Categories;
import fr.esiea.pcbuilder.shared.enums.QueryParams;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class CsvComponentRepository implements ComponentGateway {

    private final Path csvPath;

    public CsvComponentRepository(String path) {
        this.csvPath = Paths.get(path);
        if (!Files.exists(csvPath) || !Files.isRegularFile(csvPath)) {
            throw new IllegalArgumentException("Chemin CSV invalide");
        }
    }


    private Comparator<ComponentDTO> buildComparator(List<QueryParams> orders) {
        if (orders == null || orders.isEmpty()) {
            return Comparator.comparing(ComponentDTO::id);
        }

        Comparator<ComponentDTO> comparator = null;

        for (QueryParams param : orders) {
            String order = param.toString();
            boolean reverse = false;
            if (order.startsWith("R_")) {
                reverse = true;
                order = order.substring(2);
            }
            Comparator<ComponentDTO> next;
            try {
                next = switch (order) {
                    case "ID" -> Comparator.comparing(ComponentDTO::id);
                    case "NAME" -> Comparator.comparing(ComponentDTO::name, String.CASE_INSENSITIVE_ORDER);
                    case "CATEGORY" -> Comparator.comparing(c -> c.category().toString());
                    case "PRICE" -> Comparator.comparing(ComponentDTO::price);
                    case "GRADE" -> Comparator.comparing(ComponentDTO::grade);
                    case "COLOR" -> Comparator.comparing(
                            c -> switch (c) {
                                case CaseDTO aCase -> aCase.color();
                                case RamDTO ram -> ram.color();
                                case MotherBoardDTO mb -> mb.color();
                                case GpuDTO gpu -> gpu.color();
                                case PowerSupplyDTO psu -> psu.color();
                                default -> throw new IllegalArgumentException("Composant sans couleur");
                            },
                            String.CASE_INSENSITIVE_ORDER
                    );
                    case "PSU" -> Comparator.comparing(
                            c -> {
                                if (c instanceof CaseDTO aCase) return aCase.psu();
                                throw new IllegalArgumentException("Composant sans PSU");
                            },
                            String.CASE_INSENSITIVE_ORDER
                    );
                    case "SIDEPANEL" -> Comparator.comparing(
                            c -> {
                                if (c instanceof CaseDTO aCase) return aCase.sidePanel();
                                throw new IllegalArgumentException("Composant sans Side Panel");
                            },
                            String.CASE_INSENSITIVE_ORDER
                    );
                    case "EXTERNAL525BAYS" -> Comparator.comparing(
                            c -> {
                                if (c instanceof CaseDTO aCase) return aCase.external525Bays();
                                throw new IllegalArgumentException("Composant sans External 5.25\" Bays");
                            }
                    );
                    case "INTERNAL35BAYS" -> Comparator.comparing(
                            c -> {
                                if (c instanceof CaseDTO aCase) return aCase.internal35Bays();
                                throw new IllegalArgumentException("Composant sans Internal 3.5\" Bays");
                            }
                    );
                    case "CORECOUNT" -> Comparator.comparing(
                            c -> {
                                if (c instanceof CpuDTO cpu) return cpu.coreCount();
                                throw new IllegalArgumentException("Composant sans Core Count");
                            }
                    );
                    case "CORECLOCK" -> Comparator.comparing(
                            c -> {
                                if (c instanceof CpuDTO cpu) return cpu.coreClock();
                                if (c instanceof GpuDTO gpu) return (double) gpu.coreClock();
                                throw new IllegalArgumentException("Composant sans Core Clock");
                            }
                    );
                    case "BOOSTCLOCK" -> Comparator.comparing(
                            c -> {
                                if (c instanceof CpuDTO cpu) return cpu.boostClock();
                                if (c instanceof GpuDTO gpu) return (double) gpu.boostClock();
                                throw new IllegalArgumentException("Composant sans Boost Clock");
                            }
                    );
                    case "TDP" -> Comparator.comparing(
                            c -> {
                                if (c instanceof CpuDTO cpu) return cpu.tdp();
                                throw new IllegalArgumentException("Composant sans TDP");
                            }
                    );
                    case "GRAPHICS" -> Comparator.comparing(
                            c -> {
                                if (c instanceof CpuDTO cpu) return cpu.graphics();
                                throw new IllegalArgumentException("Composant sans Graphics");
                            },
                            String.CASE_INSENSITIVE_ORDER
                    );
                    case "SMT" -> Comparator.comparing(
                            c -> {
                                if (c instanceof CpuDTO cpu) return cpu.smt();
                                throw new IllegalArgumentException("Composant sans SMT");
                            }
                    );
                    case "CHIPSET" -> Comparator.comparing(
                            c -> {
                                if (c instanceof GpuDTO gpu) return gpu.chipset();
                                throw new IllegalArgumentException("Composant sans Chipset");
                            },
                            String.CASE_INSENSITIVE_ORDER
                    );
                    case "MEMORY" -> Comparator.comparing(
                            c -> {
                                if (c instanceof GpuDTO gpu) return gpu.memory();
                                throw new IllegalArgumentException("Composant sans Memory");
                            }
                    );
                    case "LENGTH" -> Comparator.comparing(
                            c -> {
                                if (c instanceof GpuDTO gpu) return gpu.length();
                                throw new IllegalArgumentException("Composant sans Length");
                            }
                    );
                    case "SOCKET" -> Comparator.comparing(
                            c -> {
                                if (c instanceof MotherBoardDTO mb) return mb.socket();
                                throw new IllegalArgumentException("Composant sans Socket");
                            },
                            String.CASE_INSENSITIVE_ORDER
                    );
                    case "FORMFACTOR" -> Comparator.comparing(
                            c -> {
                                if (c instanceof MotherBoardDTO mb) return mb.formFactor();
                                throw new IllegalArgumentException("Composant sans Form Factor");
                            },
                            String.CASE_INSENSITIVE_ORDER
                    );
                    case "MAXMEMORY" -> Comparator.comparing(
                            c -> {
                                if (c instanceof MotherBoardDTO mb) return mb.maxMemory();
                                throw new IllegalArgumentException("Composant sans Max Memory");
                            }
                    );
                    case "MEMORYSLOTS" -> Comparator.comparing(
                            c -> {
                                if (c instanceof MotherBoardDTO mb) return mb.memorySlots();
                                throw new IllegalArgumentException("Composant sans Memory Slots");
                            }
                    );
                    case "EFFICIENCY" -> Comparator.comparing(
                            c -> {
                                if (c instanceof PowerSupplyDTO psu) return psu.efficiency();
                                throw new IllegalArgumentException("Composant sans Efficiency");
                            },
                            String.CASE_INSENSITIVE_ORDER
                    );
                    case "WATTAGE" -> Comparator.comparing(
                            c -> {
                                if (c instanceof PowerSupplyDTO psu) return psu.wattage();
                                throw new IllegalArgumentException("Composant sans Wattage");
                            }
                    );
                    case "MODULAR" -> Comparator.comparing(
                            c -> {
                                if (c instanceof PowerSupplyDTO psu) return psu.modular();
                                throw new IllegalArgumentException("Composant sans Modular");
                            },
                            String.CASE_INSENSITIVE_ORDER
                    );
                    case "SPEED0" -> Comparator.comparing(
                            c -> {
                                if (c instanceof RamDTO ram) return ram.speed0();
                                throw new IllegalArgumentException("Composant sans Speed 0");
                            }
                    );
                    case "SPEED1" -> Comparator.comparing(
                            c -> {
                                if (c instanceof RamDTO ram) return ram.speed1();
                                throw new IllegalArgumentException("Composant sans Speed 1");
                            }
                    );
                    case "MODULE0" -> Comparator.comparing(
                            c -> {
                                if (c instanceof RamDTO ram) return ram.module0();
                                throw new IllegalArgumentException("Composant sans Module 0");
                            }
                    );
                    case "MODULE1" -> Comparator.comparing(
                            c -> {
                                if (c instanceof RamDTO ram) return ram.module1();
                                throw new IllegalArgumentException("Composant sans Module 1");
                            }
                    );
                    case "FIRSTWORDLATENCY" -> Comparator.comparing(
                            c -> {
                                if (c instanceof RamDTO ram) return ram.firstWordLatency();
                                throw new IllegalArgumentException("Composant sans First Word Latency");
                            }
                    );
                    case "CASLATENCY" -> Comparator.comparing(
                            c -> {
                                if (c instanceof RamDTO ram) return ram.casLatency();
                                throw new IllegalArgumentException("Composant sans CAS Latency");
                            }
                    );
                    case "PRICEPERGB" -> Comparator.comparing(
                            c -> {
                                if (c instanceof RamDTO ram) return ram.pricePerGb();
                                throw new IllegalArgumentException("Composant sans Price Per GB");
                            }
                    );
                    case "CAPACITY" -> Comparator.comparing(
                            c -> {
                                if (c instanceof StorageDTO st) return st.capacity();
                                throw new IllegalArgumentException("Composant sans Capacity");
                            }
                    );
                    case "STORAGETYPE" -> Comparator.comparing(
                            c -> {
                                if (c instanceof StorageDTO st) return st.storageType();
                                throw new IllegalArgumentException("Composant sans Storage Type");
                            },
                            String.CASE_INSENSITIVE_ORDER
                    );
                    case "CACHE" -> Comparator.comparing(
                            c -> {
                                if (c instanceof StorageDTO st) return st.cache();
                                throw new IllegalArgumentException("Composant sans Cache");
                            }
                    );
                    case "STORAGEINTERFACE" -> Comparator.comparing(
                            c -> {
                                if (c instanceof StorageDTO st) return st.storageInterface();
                                throw new IllegalArgumentException("Composant sans Storage Interface");
                            },
                            String.CASE_INSENSITIVE_ORDER
                    );
                    default -> throw new IllegalArgumentException("Paramètre de tri inconnu");
                };
            } catch (Exception e) {
                throw new IllegalArgumentException("Impossible d’appliquer le tri pour ce type de composant");
            }
            if (reverse && next != null) {
                next = next.reversed();
            }
            if (next != null) {
                comparator = (comparator == null) ? next : comparator.thenComparing(next);
            }
        }

        return comparator != null ? comparator : Comparator.comparing(ComponentDTO::id);
    }


    private ComponentDTO convertLinetoComponent(CSVRecord record) {
        try {
            switch (record.get("category").toLowerCase()) {
                case "cpu" -> {
                    return new CpuDTO(
                            Integer.parseInt(record.get("id")),
                            record.get("name"),
                            Double.parseDouble(record.get("price")),
                            Double.parseDouble(record.get("grade")),
                            Integer.parseInt(record.get("core_count")),
                            Double.parseDouble(record.get("core_clock")),
                            Double.parseDouble(record.get("boost_clock")),
                            Integer.parseInt(record.get("tdp")),
                            record.get("graphics"),
                            Boolean.parseBoolean(record.get("smt"))
                    );
                }
                case "case" -> {
                    return new CaseDTO(
                            Integer.parseInt(record.get("id")),
                            record.get("name"),
                            Double.parseDouble(record.get("price")),
                            Double.parseDouble(record.get("grade")),
                            record.get("color"),
                            record.get("psu"),
                            record.get("side_panel"),
                            Integer.parseInt(record.get("external_525_bays")),
                            Integer.parseInt(record.get("internal_35_bays"))
                    );
                }
                case "motherboard" -> {
                    return new MotherBoardDTO(
                            Integer.parseInt(record.get("id")),
                            record.get("name"),
                            Double.parseDouble(record.get("price")),
                            Double.parseDouble(record.get("grade")),
                            record.get("socket"),
                            record.get("form_factor"),
                            Integer.parseInt(record.get("max_memory")),
                            Integer.parseInt(record.get("memory_slots")),
                            record.get("color")
                    );
                }
                case "video-card" -> {
                    return new GpuDTO(
                            Integer.parseInt(record.get("id")),
                            record.get("name"),
                            Double.parseDouble(record.get("price")),
                            Double.parseDouble(record.get("grade")),
                            record.get("chipset"),
                            Integer.parseInt(record.get("memory")),
                            Integer.parseInt(record.get("core_clock")),
                            Integer.parseInt(record.get("boost_clock")),
                            record.get("color"),
                            Integer.parseInt(record.get("length"))
                    );
                }
                case "power-supply" -> {
                    return new PowerSupplyDTO(
                            Integer.parseInt(record.get("id")),
                            record.get("name"),
                            Double.parseDouble(record.get("price")),
                            Double.parseDouble(record.get("grade")),
                            record.get("efficiency"),
                            Integer.parseInt(record.get("wattage")),
                            record.get("modular"),
                            record.get("color")
                    );
                }
                case "memory" -> {
                    return new RamDTO(
                            Integer.parseInt(record.get("id")),
                            record.get("name"),
                            Double.parseDouble(record.get("price")),
                            Double.parseDouble(record.get("grade")),
                            Integer.parseInt(record.get("speed_0")),
                            Integer.parseInt(record.get("speed_1")),
                            Integer.parseInt(record.get("modules_0")),
                            Integer.parseInt(record.get("modules_1")),
                            Integer.parseInt(record.get("price_per_gb")),
                            record.get("color"),
                            Integer.parseInt(record.get("first_word_latency")),
                            Integer.parseInt(record.get("cas_latency"))
                    );
                }
                case "internal-hard-drive" -> {
                    return new StorageDTO(
                            Integer.parseInt(record.get("id")),
                            record.get("name"),
                            Double.parseDouble(record.get("price")),
                            Double.parseDouble(record.get("grade")),
                            Integer.parseInt(record.get("capacity")),
                            Double.parseDouble(record.get("price_per_gb")),
                            record.get("storage_type"),
                            Integer.parseInt(record.get("cache")),
                            record.get("form_factor"),
                            record.get("storage_interface")
                    );
                }
                default -> {
                    return null;
                }
            }
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public ArrayList<ComponentDTO> getComponentListFilteredOrdered(Categories category, ArrayList<QueryParams> orders, int limit) {
        try (
                Reader reader = Files.newBufferedReader(csvPath);
                CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())
        ) {

            return parser.getRecords().stream()
                    .map(this::convertLinetoComponent)
                    .filter(Objects::nonNull)
                    .filter(obj -> obj.category() == category)
                    .sorted(buildComparator(orders))
                    .limit(limit)
                    .collect(Collectors.toCollection(ArrayList::new));

        } catch (IOException e) {
            throw new RuntimeException("Erreur lecture CSV", e);
        }

    }
}
