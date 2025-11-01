package fr.esiea.pcbuilder.application.mappers;

import fr.esiea.pcbuilder.application.dto.*;
import fr.esiea.pcbuilder.domain.entities.Computer;

public class ComputerMapper {

    public static Computer toEntity(ComputerDTO dto) {
        if (dto == null) return null;

        Computer computer = new Computer();
        computer.setDesktopCase(CaseMapper.toEntity(dto.desktopCase()));
        computer.setPowerSupply(PowerSupplyMapper.toEntity(dto.powerSupply()));
        computer.setRam(RamMapper.toEntity(dto.ram()));
        computer.setCpu(CpuMapper.toEntity(dto.cpu()));
        computer.setGpu(GpuMapper.toEntity(dto.gpu()));
        computer.setMotherBoard(MotherBoardMapper.toEntity(dto.motherBoard()));
        computer.setStorage(StorageMapper.toEntity(dto.storage()));
        return computer;
    }

    public static ComputerDTO toDto(Computer entity) {
        if (entity == null) return null;

        return new ComputerDTO(
                CaseMapper.toDto(entity.getDesktopCase()),
                PowerSupplyMapper.toDto(entity.getPowerSupply()),
                RamMapper.toDto(entity.getRam()),
                CpuMapper.toDto(entity.getCpu()),
                GpuMapper.toDto(entity.getGpu()),
                MotherBoardMapper.toDto(entity.getMotherBoard()),
                StorageMapper.toDto(entity.getStorage())
        );
    }
}
