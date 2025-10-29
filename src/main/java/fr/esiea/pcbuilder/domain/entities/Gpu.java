package fr.esiea.pcbuilder.domain.entities;

import fr.esiea.pcbuilder.shared.enums.Categories;

public class Gpu extends Component {
    private String chipset;
    private int memory;
    private int coreClock;
    private int boostClock;
    private String color;
    private int length;

    public Gpu(int id, String name, double price, double grade,
               String chipset, int memory, int coreClock,
               int boostClock, String color, int length) {
        super(id, name, Categories.VIDEO_CARD, price, grade);
        this.chipset = chipset;
        this.memory = memory;
        this.coreClock = coreClock;
        this.boostClock = boostClock;
        this.color = color;
        this.length = length;
    }

    // getter methods

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getCoreClock() {
        return coreClock;
    }

    public void setCoreClock(int coreClock) {
        this.coreClock = coreClock;
    }

    public int getBoostClock() {
        return boostClock;
    }

    public void setBoostClock(int boostClock) {
        this.boostClock = boostClock;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
