package fr.esiea.pcbuilder.application.mappers;

import fr.esiea.pcbuilder.application.dto.PowerSupplyDTO;
import fr.esiea.pcbuilder.domain.entities.PowerSupply;

public class PowerSupplyMapper {

    public static PowerSupply toEntity(PowerSupplyDTO dto) {
        if (dto == null) return null;
        return new PowerSupply(
                dto.id(),
                dto.name(),
                dto.price(),
                dto.grade(),
                dto.efficiency(),
                dto.wattage(),
                dto.modular(),
                dto.color()
        );
    }

    public static PowerSupplyDTO toDto(PowerSupply entity) {
        if (entity == null) return null;
        return new PowerSupplyDTO(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getGrade(),
                entity.getEfficiency(),
                entity.getWattage(),
                entity.getModular(),
                entity.getColor()
        );
    }
}
