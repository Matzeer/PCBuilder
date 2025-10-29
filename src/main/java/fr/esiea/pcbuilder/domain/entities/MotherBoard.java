package fr.esiea.pcbuilder.domain.entities;

import fr.esiea.pcbuilder.shared.enums.Categories;

public class MotherBoard extends Component {
    private String socket;
    private String formFactor;
    private int maxMemory;
    private int memorySlots;
    private String color;

    public MotherBoard(int id, String name, double price, double grade,
                       String socket, String formFactor, int maxMemory,
                       int memorySlots, String color) {
        super(id, name, Categories.MOTHERBOARD, price, grade);
        this.socket = socket;
        this.formFactor = formFactor;
        this.maxMemory = maxMemory;
        this.memorySlots = memorySlots;
        this.color = color;
    }

    // getter and setters

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public int getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(int maxMemory) {
        this.maxMemory = maxMemory;
    }

    public int getMemorySlots() {
        return memorySlots;
    }

    public void setMemorySlots(int memorySlots) {
        this.memorySlots = memorySlots;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
