package fr.esiea.pcbuilder.application.dto;

public record ComputerDTO(
        int id,
        CaseDTO desktopCase,
        PowerSupplyDTO powerSupply,
        RamDTO ram,
        CpuDTO cpu,
        GpuDTO gpu,
        MotherBoardDTO motherBoard,
        StorageDTO storage
) {
}