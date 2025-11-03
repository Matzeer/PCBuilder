package fr.esiea.pcbuilder.application.dto;

import fr.esiea.pcbuilder.domain.entities.*;

public record ComputerDTO(
        CaseDTO desktopCase,
        PowerSupplyDTO powerSupply,
        RamDTO ram,
        CpuDTO cpu,
        GpuDTO gpu,
        MotherBoardDTO motherBoard,
        StorageDTO storage
) {
}