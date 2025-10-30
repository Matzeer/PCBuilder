package fr.esiea.pcbuilder.application.repositories;

import fr.esiea.pcbuilder.domain.entities.Computer;
import java.util.List;

public interface ComputerGateway {
    void addComputer(Computer computer);
    List<Computer> getComputers();
}
