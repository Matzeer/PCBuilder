package fr.esiea.pcbuilder.infrastructure.persistence;

import fr.esiea.pcbuilder.application.repositories.ComputerGateway;
import fr.esiea.pcbuilder.domain.entities.Computer;

import java.util.ArrayList;

public class InMemoryRepository implements ComputerGateway {
    private ArrayList<Computer> computers = new ArrayList<>();
    public InMemoryRepository() {
    }

    public void setComputers(ArrayList<Computer> computers) {
        this.computers = computers;
    }
    @Override
    public void addComputer(Computer computer) {
        this.computers.add(computer);
    }
    @Override
    public ArrayList<Computer> getComputers() {
        return computers;
    }
}
