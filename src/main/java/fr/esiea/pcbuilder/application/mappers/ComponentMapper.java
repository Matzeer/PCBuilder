package fr.esiea.pcbuilder.application.mappers;

import fr.esiea.pcbuilder.application.dto.*;
import fr.esiea.pcbuilder.domain.entities.*;
import fr.esiea.pcbuilder.shared.enums.Categories;

public class ComponentMapper {

    public static Component toEntity(ComponentDTO dto) {
        if (dto == null) return null;

        return switch (dto.category()) {
            case CPU -> CpuMapper.toEntity((CpuDTO) dto);
            case VIDEO_CARD -> GpuMapper.toEntity((GpuDTO) dto);
            case MOTHERBOARD -> MotherBoardMapper.toEntity((MotherBoardDTO) dto);
            case MEMORY -> RamMapper.toEntity((RamDTO) dto);
            case INTERNAL_HARD_DRIVE -> StorageMapper.toEntity((StorageDTO) dto);
            case POWER_SUPPLY -> PowerSupplyMapper.toEntity((PowerSupplyDTO) dto);
            case CASE -> CaseMapper.toEntity((CaseDTO) dto);
        };
    }

    public static ComponentDTO toDto(Component entity) {
        if (entity == null) return null;

        return switch (entity.getCategory()) {
            case CPU -> CpuMapper.toDto((Cpu) entity);
            case VIDEO_CARD -> GpuMapper.toDto((Gpu) entity);
            case MOTHERBOARD -> MotherBoardMapper.toDto((MotherBoard) entity);
            case MEMORY -> RamMapper.toDto((Ram) entity);
            case INTERNAL_HARD_DRIVE -> StorageMapper.toDto((Storage) entity);
            case POWER_SUPPLY -> PowerSupplyMapper.toDto((PowerSupply) entity);
            case CASE -> CaseMapper.toDto((Case) entity);
        };
    }
}
