package fr.esiea.pcbuilder.application.mappers;

import fr.esiea.pcbuilder.application.dto.CaseDTO;
import fr.esiea.pcbuilder.domain.entities.Case;

public class CaseMapper {

    public static Case toEntity(CaseDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Case(
                dto.id(),
                dto.name(),
                dto.price(),
                dto.grade(),
                dto.color(),
                dto.psu(),
                dto.sidePanel(),
                dto.external525Bays(),
                dto.internal35Bays()
        );
    }

    public static CaseDTO toDto(Case entity) {
        if (entity == null) {
            return null;
        }
        return new CaseDTO(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getGrade(),
                entity.getColor(),
                entity.getPsu(),
                entity.getSidePanel(),
                entity.getExternal525Bays(),
                entity.getInternal35Bays()
        );
    }
}
