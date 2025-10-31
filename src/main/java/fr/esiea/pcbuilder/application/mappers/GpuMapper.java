package fr.esiea.pcbuilder.application.mappers;

import fr.esiea.pcbuilder.application.dto.GpuDTO;
import fr.esiea.pcbuilder.domain.entities.Gpu;

public class GpuMapper {

    public static Gpu toEntity(GpuDTO dto) {
        if (dto == null) return null;
        return new Gpu(
                dto.id(),
                dto.name(),
                dto.price(),
                dto.grade(),
                dto.chipset(),
                dto.memory(),
                dto.coreClock(),
                dto.boostClock(),
                dto.color(),
                dto.length()
        );
    }

    public static GpuDTO toDto(Gpu entity) {
        if (entity == null) return null;
        return new GpuDTO(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getGrade(),
                entity.getChipset(),
                entity.getMemory(),
                entity.getCoreClock(),
                entity.getBoostClock(),
                entity.getColor(),
                entity.getLength()
        );
    }
}
