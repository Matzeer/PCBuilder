package fr.esiea.pcbuilder.domain.entities;

public class Ram extends Component {
    private int speed0;
    private int speed1;
    private int module0;
    private int module1;
    private String color;
    private int firstWordLatency;
    private int casLatency;

    public Ram(int id, String name, String type, double price, double grade,
               int speed0, int speed1, int module0, int module1,
               String color, int firstWordLatency, int casLatency) {
        super(id, name, type, price, grade);
        this.speed0 = speed0;
        this.speed1 = speed1;
        this.module0 = module0;
        this.module1 = module1;
        this.color = color;
        this.firstWordLatency = firstWordLatency;
        this.casLatency = casLatency;
    }

    public int getSpeed0() {
        return speed0;
    }

    public void setSpeed0(int speed0) {
        this.speed0 = speed0;
    }

    public int getSpeed1() {
        return speed1;
    }

    public void setSpeed1(int speed1) {
        this.speed1 = speed1;
    }

    public int getModule0() {
        return module0;
    }

    public void setModule0(int module0) {
        this.module0 = module0;
    }

    public int getModule1() {
        return module1;
    }

    public void setModule1(int module1) {
        this.module1 = module1;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getFirstWordLatency() {
        return firstWordLatency;
    }

    public void setFirstWordLatency(int firstWordLatency) {
        this.firstWordLatency = firstWordLatency;
    }

    public int getCasLatency() {
        return casLatency;
    }

    public void setCasLatency(int casLatency) {
        this.casLatency = casLatency;
    }

}
