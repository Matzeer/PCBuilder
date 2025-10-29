package fr.esiea.pcbuilder.domain.entities;

public class PowerSupply extends Component {
    private String efficiency;
    private int wattage;
    private String modular;
    private String color;

    public PowerSupply(int id, String name, String type, double price, double grade,
                       String efficiency, int wattage, String modular, String color) {
        super(id, name, type, price, grade);
        this.efficiency = efficiency;
        this.wattage = wattage;
        this.modular = modular;
        this.color = color;
    }

    public String getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }

    public int getWattage() {
        return wattage;
    }

    public void setWattage(int wattage) {
        this.wattage = wattage;
    }

    public String getModular() {
        return modular;
    }

    public void setModular(String modular) {
        this.modular = modular;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
