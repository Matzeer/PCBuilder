package fr.esiea.pcbuilder.application.mappers;

import fr.esiea.pcbuilder.application.dto.StorageDTO;
import fr.esiea.pcbuilder.domain.entities.Storage;

public class StorageMapper {

    public static Storage toEntity(StorageDTO dto) {
        if (dto == null) return null;
        return new Storage(
                dto.id(),
                dto.name(),
                dto.price(),
                dto.grade(),
                dto.capacity(),
                dto.pricePerGb(),
                dto.storageType(),
                dto.cache(),
                dto.formFactor(),
                dto.storageInterface()
        );
    }

    public static StorageDTO toDto(Storage entity) {
        if (entity == null) return null;
        return new StorageDTO(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getGrade(),
                entity.getCapacity(),
                entity.getPricePerGb(),
                entity.getStorageType(),
                entity.getCache(),
                entity.getFormFactor(),
                entity.getStorageInterface()
        );
    }
}
