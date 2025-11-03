package fr.esiea.pcbuilder.application.mappers;

import fr.esiea.pcbuilder.application.dto.ComputerDTO;
import fr.esiea.pcbuilder.application.repositories.ComputerGateway;
import fr.esiea.pcbuilder.domain.entities.Computer;

import java.util.Optional;

public class ComputerMapper {

    public static Computer toEntity(ComputerDTO dto, ComputerGateway computerGateway) {
        if (dto == null) return null;
        System.out.println(computerGateway.getComputers());
        System.out.println(dto.id());

        Optional<Computer> optionalComputer = computerGateway.getComputers().stream()
                .filter(c -> c.getId() == dto.id())
                .findFirst();

        if (optionalComputer.isEmpty()) return null;

        Computer computer = optionalComputer.get();
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
                entity.getId(),
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
