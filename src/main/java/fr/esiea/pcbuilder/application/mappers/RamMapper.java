package fr.esiea.pcbuilder.application.mappers;

import fr.esiea.pcbuilder.application.dto.RamDTO;
import fr.esiea.pcbuilder.domain.entities.Ram;

public class RamMapper {

    public static Ram toEntity(RamDTO dto) {
        if (dto == null) return null;
        return new Ram(
                dto.id(),
                dto.name(),
                dto.price(),
                dto.grade(),
                dto.speed0(),
                dto.speed1(),
                dto.module0(),
                dto.module1(),
                dto.pricePerGb(),
                dto.color(),
                dto.firstWordLatency(),
                dto.casLatency()
        );
    }

    public static RamDTO toDto(Ram entity) {
        if (entity == null) return null;
        return new RamDTO(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getGrade(),
                entity.getSpeed0(),
                entity.getSpeed1(),
                entity.getModule0(),
                entity.getModule1(),
                entity.getPricePerGb(),
                entity.getColor(),
                entity.getFirstWordLatency(),
                entity.getCasLatency()
        );
    }
}
