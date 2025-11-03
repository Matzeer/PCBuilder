package fr.esiea.pcbuilder.application.mappers;

import fr.esiea.pcbuilder.application.dto.MotherBoardDTO;
import fr.esiea.pcbuilder.domain.entities.MotherBoard;

public class MotherBoardMapper {

    public static MotherBoard toEntity(MotherBoardDTO dto) {
        if (dto == null) return null;
        return new MotherBoard(
                dto.id(),
                dto.name(),
                dto.price(),
                dto.grade(),
                dto.socket(),
                dto.formFactor(),
                dto.maxMemory(),
                dto.memorySlots(),
                dto.color()
        );
    }

    public static MotherBoardDTO toDto(MotherBoard entity) {
        if (entity == null) return null;
        return new MotherBoardDTO(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getGrade(),
                entity.getSocket(),
                entity.getFormFactor(),
                entity.getMaxMemory(),
                entity.getMemorySlots(),
                entity.getColor()
        );
    }
}
