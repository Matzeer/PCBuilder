package fr.esiea.pcbuilder.domain.entities;

public class Computer {
    private static int nextId = 0;
    private final int id;
    private Case desktopCase;
    private PowerSupply powerSupply;
    private Ram ram;
    private Cpu cpu;
    private Gpu gpu;
    private MotherBoard motherBoard;
    private Storage storage;

    public Computer() {
        this.id = nextId++;
    }

    public Computer(Case desktopCase, PowerSupply powerSupply, Ram ram,
                    Cpu cpu, Gpu gpu, MotherBoard motherBoard, Storage storage) {
        this.id = nextId++;
        this.desktopCase = desktopCase;
        this.powerSupply = powerSupply;
        this.ram = ram;
        this.cpu = cpu;
        this.gpu = gpu;
        this.motherBoard = motherBoard;
        this.storage = storage;
    }

    public int getId() {
        return id;
    }

    public Case getDesktopCase() {
        return desktopCase;
    }

    public void setDesktopCase(Case desktopCase) {
        this.desktopCase = desktopCase;
    }

    public PowerSupply getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(PowerSupply powerSupply) {
        this.powerSupply = powerSupply;
    }

    public Ram getRam() {
        return ram;
    }

    public void setRam(Ram ram) {
        this.ram = ram;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public Gpu getGpu() {
        return gpu;
    }

    public void setGpu(Gpu gpu) {
        this.gpu = gpu;
    }

    public MotherBoard getMotherBoard() {
        return motherBoard;
    }

    public void setMotherBoard(MotherBoard motherBoard) {
        this.motherBoard = motherBoard;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }


}
