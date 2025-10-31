package fr.esiea.pcbuilder.application.mappers;

import fr.esiea.pcbuilder.application.dto.CpuDTO;
import fr.esiea.pcbuilder.domain.entities.Cpu;

public class CpuMapper {

    public static Cpu toEntity(CpuDTO dto) {
        if (dto == null) return null;
        return new Cpu(
                dto.id(),
                dto.name(),
                dto.price(),
                dto.grade(),
                dto.coreCount(),
                dto.coreClock(),
                dto.boostClock(),
                dto.tdp(),
                dto.graphics(),
                dto.smt()
        );
    }

    public static CpuDTO toDto(Cpu entity) {
        if (entity == null) return null;
        return new CpuDTO(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getGrade(),
                entity.getCoreCount(),
                entity.getCoreClock(),
                entity.getBoostClock(),
                entity.getTdp(),
                entity.getGraphics(),
                entity.isSmt()
        );
    }
}
